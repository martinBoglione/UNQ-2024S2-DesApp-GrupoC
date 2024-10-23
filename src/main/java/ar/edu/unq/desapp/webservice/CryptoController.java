package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.model.Crypto;
import ar.edu.unq.desapp.model.CryptoList;
import ar.edu.unq.desapp.service.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Crypto", description = "Crypto APIs")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @Operation(summary = "Get a cryptocurrency price")
    @GetMapping("/crypto/{symbol}")
    public ResponseEntity<Crypto> getCryptoCurrencyValue(
            @Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true) @PathVariable String symbol) {
        Crypto entity = cryptoService.getCryptoValue(symbol);
        return ResponseEntity.ok().body(entity);
    }

    @Operation(summary = "Get all cryptocurrency prices")
    @GetMapping("/crypto/all")
    public ResponseEntity<CryptoList> getAllCryptoCurrencyPrices() {
        CryptoList list = cryptoService.getAllCryptoPrices();
        return ResponseEntity.ok().body(list);
    }
}
