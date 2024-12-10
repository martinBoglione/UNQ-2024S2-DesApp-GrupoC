package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import ar.edu.unq.desapp.model.*;
import ar.edu.unq.desapp.model.dto.*;
import ar.edu.unq.desapp.model.exceptions.PriceVariationException;
import ar.edu.unq.desapp.repositories.UserRepository;
import ar.edu.unq.desapp.service.CryptoService;
import ar.edu.unq.desapp.service.UsdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ar.edu.unq.desapp.service.TransactionsService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Transactions", description = "Orders (intentions) & Operations APIs")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final CryptoService cryptoService;
    private final UserRepository userRepository;
    private final UsdService usdService;

    public TransactionsController(TransactionsService transactionsService, CryptoService cryptoService, UserRepository userRepository, UsdService usdService) {
        this.transactionsService = transactionsService;
        this.cryptoService = cryptoService;
        this.userRepository = userRepository;
        this.usdService = usdService;
    }

    @LogExecutionTime
    @Operation(summary = "Get active orders", description = "Retrieve all orders in database")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully", content = {@Content(schema = @Schema(implementation = Order.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "User with ID # not found", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content(schema = @Schema())})
    @GetMapping("/orders")
    public List<OrderSummaryDTO> getActiveOrders() {

        List<Order> activeOrders = this.transactionsService.getAllOrders();

        return activeOrders.stream()
                .map(order -> new OrderSummaryDTO(
                        order.getTimestamp(),
                        order.getAsset(),
                        order.getQuantity(),
                        order.getPrice(),
                        order.getAmountArs(),
                        new UserDTO(
                                order.getUser().getName(),
                                order.getUser().getSurname(),
                                order.getUser().getReputation(),
                                order.getUser().getOperationsQuantity()
                        )
                ))
                .toList();
    }

    @LogExecutionTime
    @Operation(summary = "Register order")
    @PostMapping("/orders/create")
    public Object createOrder(@RequestBody OrderRequestDTO orderRequest) {

        //Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        //Get Crpyto price
        Crypto crypto = cryptoService.getCryptoValue(orderRequest.getAsset().toString());
        Float cryptoPrice = crypto.getPrice();
        Double priceDifferencePercentage = Math.abs((cryptoPrice - orderRequest.getPrice()) / cryptoPrice) * 100;

        if(priceDifferencePercentage > 5){
            throw new PriceVariationException("The provided price has a variation of more than ±5% from the current crypto price.");
        }

        //Get USD value in ARS
        final double dollarValue = usdService.getDolarValue();

        //Get amount in ARS
        double amountArs = orderRequest.getQuantity() * orderRequest.getPrice() * dollarValue;

        Order order = Order.builder()
                .asset(orderRequest.getAsset())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .amountArs(amountArs)
                .user(user)
                .operationType(OperationType.valueOf(orderRequest.getOperationType()))
                .build();

        Order savedOrder = this.transactionsService.createOrder(order);

        return new OrderResponseDTO(
            savedOrder.getAsset().toString(),
            savedOrder.getQuantity(),
            savedOrder.getPrice(),
            savedOrder.getAmountArs(),
            savedOrder.getOperationType().toString(),
            user.getName(),
            user.getSurname()
            //user.getWalletAddress()
        );

    }

    @LogExecutionTime
    @Operation(summary = "Cancel order (forced by user)")
    @PatchMapping("/orders/cancel/user/{id}")
    public void userCancelOrder(@PathVariable Long id) {

        //Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        //Get logged user ID
        Long userID = user.getId();

        Order order = transactionsService.getOrderById(id);
        //Get order user ID
        Long orderUserID = order.getUser().getId();

        if(userID != orderUserID) {
            throw new PriceVariationException("Can't cancel another user's order");
        } else {
            user.setReputation(user.getReputation() - 20);
            userRepository.save(user);

            transactionsService.userCancelOrder(id);
        }
    }

    @LogExecutionTime
    @Operation(summary = "Cancel order (forced by user)")
    @PatchMapping("/orders/cancel/system/{id}")
    public void systemCancelOrder(@PathVariable Long id) {
        transactionsService.systemCancelOrder(id);
    }

    @LogExecutionTime
    @Operation(summary = "User (counterparty) wants to fill an order (transact)")
    @PostMapping("/orders/fill/{id}")
    public void fillOrder(@PathVariable Long id, @RequestBody OrderFillDto orderFillDTO) {

        //Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        //Get logged user ID
        Long userID = user.getId();

        Order order = transactionsService.getOrderById(id);
        //Get order amount in ARS
        Double amountArs = order.getAmountArs();
        //Get order user ID
        Long orderUserID = order.getUser().getId();

        //Get counterparty amount in ARS
        Double amountArsCounterParty = orderFillDTO.getAmountArs();

        Double priceDifferencePercentage = Math.abs((amountArsCounterParty - amountArs) / amountArsCounterParty) * 100;

        if (userID == orderUserID) {
            throw new PriceVariationException("A user can't fill an order created by themselves");
        }
        else if (order.getStatus() != OrderStatus.ACTIVE) {
            throw new PriceVariationException("Order must be ACTIVE");
        }
        else if(priceDifferencePercentage > 5){
            transactionsService.systemCancelOrder(id);
            throw new PriceVariationException("The provided price has a variation of more than ±5% from the current crypto price.");
        } else {
            transactionsService.fillOrder(id);
        }

    }

    @LogExecutionTime
    @Operation(summary = "User (issuer) confirms transaction completed")
    @PatchMapping("/orders/confirm/{id}")
    public void transactionCompleted(@PathVariable Long id) {
        /* TODO
            Se busca la Transaction con {id} y se confirma junto con la orden asociada.
            Agregar logica
        * */

        //Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        //Get logged user ID
        Long userID = user.getId();

        Order order = transactionsService.getOrderById(id);
        //Get order user ID
        Long orderUserID = order.getUser().getId();


        if (userID != orderUserID) {
            throw new PriceVariationException("User must be issuer of order");
        }
        else if (order.getStatus() != OrderStatus.INPROCESS) {
            throw new PriceVariationException("Order must be INPROCESS");
        } else {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime orderTimestamp = order.getTimestamp(); // Assumes order.getTimestamp() returns a LocalDateTime

            Duration duration = Duration.between(orderTimestamp, now);
            long minutes = duration.toMinutes();

            if (minutes <= 30) {
                user.setReputation(user.getReputation() + 10);
            } else {
                user.setReputation(user.getReputation() + 5);
            }
            user.setOperationsQuantity((user.getOperationsQuantity() + 1));
            userRepository.save(user);

            transactionsService.confirmOrder(id);
        }
    }

    public List<Order> getOrdersByUserAndDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        return this.transactionsService.getOrdersByUserAndDateRange(userId, fromDate, toDate);
    }
}
