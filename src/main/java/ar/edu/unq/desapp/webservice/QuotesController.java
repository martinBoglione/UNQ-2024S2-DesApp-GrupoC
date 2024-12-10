package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import ar.edu.unq.desapp.model.Crypto;
import ar.edu.unq.desapp.model.CryptoList;
import ar.edu.unq.desapp.service.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Quotes", description = "Crypto Quotes APIs")
public class QuotesController {

    private final CryptoService cryptoService;

    public QuotesController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @LogExecutionTime
    @Operation(summary = "Get last quote for a given crypto")
    @GetMapping("/quotes/{symbol}")
    public ResponseEntity<Crypto> getCryptoCurrencyValue(@Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true) @PathVariable String symbol) {
        Crypto entity = cryptoService.getCryptoValue(symbol.toUpperCase());
        return ResponseEntity.ok().body(entity);
    }

    @LogExecutionTime
    @Operation(summary = "Get last quotes for all known cryptocurrencies")
    @GetMapping("/quotes/latest")
    public ResponseEntity<CryptoList> getLastPersistedCryptoCurrencyPrices() {
        CryptoList list = cryptoService.getAllCryptoPrices();
        return ResponseEntity.ok().body(list);
    }

    @LogExecutionTime
    @Operation(summary = "Get cached quotes (fast response)")
    @GetMapping("/quotes")
    public ResponseEntity<CryptoList> getAllCryptoCurrencyPrices() {
        CryptoList list = cryptoService.getAllCachedCryptoPrices();
        return ResponseEntity.ok().body(list);
    }

    @LogExecutionTime
    @Operation(summary = "Get 24H quotes for a given crypto")
    @GetMapping("/quotes/24h/{symbol}")
    public ResponseEntity<List<Crypto>> get24HoursQuotes(@Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true) @PathVariable String symbol) {

        List<Crypto> list = cryptoService.getCryptoClosingPricesLast24Hours(symbol);
        return ResponseEntity.ok().body(list);
    }
}
