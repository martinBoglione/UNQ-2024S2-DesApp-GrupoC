package ar.edu.unq.DevApp.webservice;

import ar.edu.unq.DevApp.model.User;
import ar.edu.unq.DevApp.service.CryptoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Cryptos", description = "Cryptos APIs")
@RestController
@RequestMapping("/api")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return this.cryptoService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        return this.cryptoService.getUserById(id);
    }

    @PostMapping("/registerUser")
    public User createUser(@RequestBody User user) {
        return this.cryptoService.createUser(user);
    }

    @PostMapping("/deleteAll")
    public void deleteAllUsers() {
        this.cryptoService.deleteAll();
    }

    @PostMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.cryptoService.deleteUser(id);
    }
}
