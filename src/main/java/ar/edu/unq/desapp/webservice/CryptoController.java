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
@Tag(name = "Quotes", description = "Crypto Quotes APIs")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @Operation(summary = "Get last quote for a given crypto")
    @GetMapping("/quotes/{symbol}")
    public ResponseEntity<Crypto> getCryptoCurrencyValue(@Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true) @PathVariable String symbol) {
        Crypto entity = cryptoService.getCryptoValue(symbol.toUpperCase());
        return ResponseEntity.ok().body(entity);
    }

    @Operation(summary = "Get last quotes for all known cryptocurrencies")
    @GetMapping("/quotes")
    public ResponseEntity<CryptoList> getAllCryptoCurrencyPrices() {
        CryptoList list = cryptoService.getAllCryptoPrices();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Get 24H quotes for a given crypto")
    @GetMapping("/quotes/24h/{symbol}")
    public ResponseEntity get24HoursQuotes(@Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true) @PathVariable String symbol) {
        /*
        TODO
        * 3. Mostrar las cotizaciones de las últimas 24hs para un cripto activo dado.(consolidado por hora)
        Criptoactivo
        Dia y Hora de cotización
        Cotización del Cripto Activo
    */
        return ResponseEntity.ok().body("completar!!");
    }
}
