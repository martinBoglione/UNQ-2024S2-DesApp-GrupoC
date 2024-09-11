package ar.edu.unq.DevApp.webservice;

import ar.edu.unq.DevApp.service.CryptoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Cryptos", description = "Cryptos APIs")
@RestController
@RequestMapping("/api")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @GetMapping("/users")
    public ResponseEntity<HttpStatus> getAllUsers() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
