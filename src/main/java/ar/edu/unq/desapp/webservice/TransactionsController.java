package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import ar.edu.unq.desapp.model.*;
import ar.edu.unq.desapp.model.dto.OrderRequestDTO;
import ar.edu.unq.desapp.model.dto.OrderResponseDTO;
import ar.edu.unq.desapp.model.dto.OrderSummaryDTO;
import ar.edu.unq.desapp.model.dto.UserDTO;
import ar.edu.unq.desapp.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ar.edu.unq.desapp.service.TransactionsService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "Transactions", description = "Orders (intentions) & Operations APIs")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final UserRepository userRepository;

    public TransactionsController(TransactionsService transactionsService, UserRepository userRepository) {
        this.transactionsService = transactionsService;
        this.userRepository = userRepository;
    }

    @LogExecutionTime
    @Operation(summary = "Get active orders", description = "Retrieve all orders in database")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully", content = { @Content(schema = @Schema(implementation = Order.class), mediaType = "application/json") })
    @ApiResponse(responseCode = "400", description = "User with ID # not found", content = { @Content(schema = @Schema()) })
    @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) })
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
                .collect(Collectors.toList());
    }

    @LogExecutionTime
    @Operation(summary = "Register order")
    @PostMapping("/orders/create")
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequest) {

        /* TODO
            Direccion de envio: Depende del orderRequest.getOperationType()?
            Si la operación es venta, debe mostrar un CVU para que el usuario 2 haga la transferencia
            Si la operación es compra,  debe mostrar la dirección de la billetera de CriptoActivos
         */

        User user = userRepository.findActiveUserById(orderRequest.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .asset(orderRequest.getAsset())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .amountArs(orderRequest.getAmountArs())
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
        );
    }

    @LogExecutionTime
    @Operation(summary = "Cancel order (forced by user)")
    @PatchMapping("/orders/cancel/{id}")
    public void cancelOrder(@PathVariable Long id) {
        /* TODO
            Agregar logica de resta o suma de reputacion
            Agregar logica de cancel by system
        * */
        transactionsService.cancelOrder(id);
    }

    @LogExecutionTime
    @Operation(summary = "User (counterparty) wants to fill an order (transact)")
    @PostMapping("/orders/fill/{id}")
    public void fillOrder(@PathVariable Long id) {
        /* TODO
            Se busca la orden {id}, si pasa los controles se crea una Transaction, y se cambia el estado de la orden.
            Agregar logica
        * */

        transactionsService.fillOrder(id);
    }

    @LogExecutionTime
    @Operation(summary = "User (issuer) confirms transaction completed")
    @PatchMapping("/orders/confirm/{id}")
    public void transactionCompleted(@PathVariable Long id) {
        /* TODO
            Se busca la Transaction con {id} y se confirma junto con la orden asociada.
            Agregar logica
        * */

        transactionsService.confirmOrder(id);
    }

    public List<Order> getOrdersByUserAndDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        return this.transactionsService.getOrdersByUserAndDateRange(userId, fromDate, toDate);
    }
}
