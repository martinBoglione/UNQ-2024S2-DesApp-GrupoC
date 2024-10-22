package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.model.User;
import ar.edu.unq.desapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Users", description = "Users APIs")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve all users in database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "User with ID # not found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) }) })
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

    /* delete all should not be part of the API
    @PostMapping("/deleteAll")
    public void deleteAllUsers() {
        this.userService.deleteAll();
    }*/

    @Operation(summary = "Delete user", description = "Delete one user by ID")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }
}
