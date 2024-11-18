package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.model.User;
import ar.edu.unq.desapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@Tag(name = "Users", description = "Users APIs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Retrieve all users in database")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") })
    @ApiResponse(responseCode = "400", description = "User with ID # not found", content = { @Content(schema = @Schema()) })
    @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) })
    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @Operation(summary = "Get user", description = "Retrieve one user by ID")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @Operation(summary = "Register user", description = "Register one user in database")
    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @Operation(summary = "Delete user", description = "Delete one user by ID")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }

    @Operation(summary = "Operated volume between dates range for user")
    @GetMapping("/users/report")
    public ResponseEntity<String> reportOperatedVolume(@RequestParam String email, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
        /* TODO
        *    7. Dado un usuario,  Informar el volumen operado de cripto activos entre dos fechas.
            Dia y hora de solicitud
            Valor total operado en dólares
            Valor total operado en pesos ARG
            Activos:
            Criptoactivo
            Cantidad nominal del Cripto Activo
            Cotización actual del Cripto Activo
            Monto de la cotización en pesos ARG
            */
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "Users list with reputation")
    @GetMapping("/users/all-with-reputation")
    public ResponseEntity<String> getAllUsersWithReputation() {
        /* TODO
        *   Listado de usuarios de la plataforma
            Nombre, Apellido, Cantidad Operaciones, Reputación
            */
        return ResponseEntity.ok("ok");
    }

}
