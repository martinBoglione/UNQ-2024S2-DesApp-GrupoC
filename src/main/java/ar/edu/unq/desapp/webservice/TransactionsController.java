package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import ar.edu.unq.desapp.model.*;
import ar.edu.unq.desapp.model.DTOs.OrderRequestDTO;
import ar.edu.unq.desapp.model.DTOs.OrderResponseDTO;
import ar.edu.unq.desapp.model.DTOs.OrderSummaryDTO;
import ar.edu.unq.desapp.model.DTOs.UserDTO;
import ar.edu.unq.desapp.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.edu.unq.desapp.service.TransactionsService;

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
                                // TODO: Cambiar reputation y operationsQuantity para que no esten hardcodeados
                                0,
                                0
                        )
                ))
                .collect(Collectors.toList());
    }

    @LogExecutionTime
    @Operation(summary = "Register order")
    @PostMapping("/orders/create")
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequest) {

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
          Revisar que sea el método HTTP correcto.
          Cancela una orden activa
        * */
        return;
    }

    @LogExecutionTime
    @Operation(summary = "User (counterparty) wants to fill an order (transact)")
    @PostMapping("/orders/fill/{id}")
    public ResponseEntity<String> fillOrder(@PathVariable Long id) {
        /* TODO
            Se busca la orden {id}, si pasa los controles se crea una Transaction, y se cambia el estado de la orden.

        	6. Procesar la transacción informada por un usuario
            Definir bien qué campos deberían estar en el body (lo que dice el punto 6 está mal).
        * */

        return ResponseEntity.ok("ok");
    }

    @LogExecutionTime
    @Operation(summary = "User (issuer) confirms transaction completed")
    @PatchMapping("/orders/confirm/{id}")
    public ResponseEntity<String> transactionCompleted(@PathVariable Long id) {
        /* TODO
            Se busca la Transaction con {id} y se confirma junto con la orden asociada.

        	6. Procesar la transacción informada por un usuario
            Definir bien qué campos deberían estar en el body (lo que dice el punto 6 está mal).
        * */

        return ResponseEntity.ok("ok");
    }

}
