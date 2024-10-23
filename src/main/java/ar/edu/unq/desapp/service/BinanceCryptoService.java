package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.model.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinanceCryptoService {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${integration.binance.api.url:NONE}")
    private String binanceApiURL;

    public Crypto getCryptoCurrencyValue(String symbol) {
        Crypto crypto = restTemplate.getForObject(binanceApiURL + "ticker/price?symbol=" + symbol, Crypto.class);
        return crypto;
    }
}
