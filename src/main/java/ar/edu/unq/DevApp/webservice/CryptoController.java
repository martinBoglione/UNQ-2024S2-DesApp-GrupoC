package ar.edu.unq.DevApp.webservice;

import ar.edu.unq.DevApp.service.CryptoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
@Tag(name = "Cryptos", description = "Cryptos APIs")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;
}
