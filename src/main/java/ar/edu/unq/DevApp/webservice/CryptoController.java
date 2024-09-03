package ar.edu.unq.DevApp.webservice;

import ar.edu.unq.DevApp.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;
}
