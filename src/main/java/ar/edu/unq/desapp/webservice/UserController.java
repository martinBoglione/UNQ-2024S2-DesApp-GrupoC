package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import ar.edu.unq.desapp.model.Crypto;
import ar.edu.unq.desapp.model.DTOs.UserDTO;
import ar.edu.unq.desapp.model.Order;
import ar.edu.unq.desapp.model.User;
import ar.edu.unq.desapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "Users", description = "Users APIs")
public class UserController {

    private final UserService userService;
    private final TransactionsController transactionsController;
    private final QuotesController quotesController;

    public UserController(UserService userService, TransactionsController transactionsController, QuotesController quotesController) {
        this.userService = userService;
        this.transactionsController = transactionsController;
        this.quotesController = quotesController;
    }

    @LogExecutionTime
    @Operation(summary = "Get all users", description = "Retrieve all users in database")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") })
    @ApiResponse(responseCode = "400", description = "User with ID # not found", content = { @Content(schema = @Schema()) })
    @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) })
    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @LogExecutionTime
    @Operation(summary = "Get user", description = "Retrieve one user by ID")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @LogExecutionTime
    @Operation(summary = "Register user", description = "Register one user in database")
    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @LogExecutionTime
    @Operation(summary = "Delete user", description = "Delete one user by ID")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }

    @LogExecutionTime
    @Operation(summary = "Operated volume between dates range for user")
    @GetMapping("/users/report")
    public ResponseEntity<String> reportOperatedVolume(@RequestParam Long id, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {

        List<Order> userOrders = this.transactionsController.getOrdersByUserAndDateRange(id, fromDate, toDate);

        if (userOrders.isEmpty()) {
            return ResponseEntity.ok("No se encontraron órdenes en el rango de fechas especificado.");
        }

        // Valor del dólar (esto debería ser configurable o traído de un servicio)
        final double dollarValue = 1300;

        double totalVolumeInDollars = 0.0;
        double totalVolumeInPesos = 0.0;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Reporte de volumen operado\n")
                .append("Usuario ID: ").append(id).append("\n")
                .append("Desde: ").append(fromDate).append("\n")
                .append("Hasta: ").append(toDate).append("\n")
                .append("Detalles por orden:\n");

        for (Order order : userOrders) {
            Crypto crypto = quotesController.getCryptoCurrencyValue(order.getAsset().toString()).getBody();
            //TODO: Revisar cuentas para amounts y totales
            double currentCryptoValue = crypto.getPrice();
            double orderValueInPesos =  order.getAmountArs();
            double orderValueInDollars = order.getAmountArs() / dollarValue;

            totalVolumeInPesos += orderValueInPesos;
            totalVolumeInDollars += orderValueInDollars;

            // Añadir detalles de la orden al reporte
            reportBuilder.append("Criptoactivo: ").append(order.getAsset()).append("\n")
                    .append("Cantidad nominal: ").append(order.getQuantity()).append("\n")
                    .append("Cotización actual (USD): ").append(currentCryptoValue).append("\n")
                    .append("Monto en ARS: ").append(orderValueInPesos).append("\n")
                    .append("---------------------------\n");
        }

        reportBuilder.append("Volumen total operado:\n")
                .append("En USD: ").append(totalVolumeInDollars).append("\n")
                .append("En ARS: ").append(totalVolumeInPesos).append("\n");

        return ResponseEntity.ok(reportBuilder.toString());
    }

    @LogExecutionTime
    @Operation(summary = "Users list with reputation")
    @GetMapping("/users/all-with-reputation")
    public List<UserDTO> getAllUsersWithReputation() {
        List<User> activeUsers = this.userService.getAllUsers();

        return activeUsers.stream()
                .map(user -> new UserDTO(
                        user.getName(),
                        user.getSurname(),
                        user.getOperationsQuantity(),
                        user.getReputation()
                ))
                .collect(Collectors.toList());
    }

}
