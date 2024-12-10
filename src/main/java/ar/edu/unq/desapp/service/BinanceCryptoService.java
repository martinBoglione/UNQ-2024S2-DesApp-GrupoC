package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.helpers.CurrentTime;
import ar.edu.unq.desapp.model.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class BinanceCryptoService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${integration.binance.api.url:NONE}")
    private String binanceApiURL;

    public Crypto getCryptoCurrencyValue(String symbol) {
        return restTemplate.getForObject(binanceApiURL + "ticker/price?symbol=" + symbol, Crypto.class);
    }

    public List<Crypto> getCryptoClosingPricesLast24Hours(String symbol) {
        String url = binanceApiURL + "klines?symbol=" + symbol + "&interval=1h&limit=24";

        // Binance API returns a List of Lists; process to extract closing prices
        List<List<Object>> response = restTemplate.getForObject(url, List.class);
        List<Crypto> closingPrices = new ArrayList<>();

        if (response != null) {
            for (List<Object> candle : response) {
                Crypto crypto = mapCandleToCrypto(candle, symbol);
                closingPrices.add(crypto);
            }
        }

        return closingPrices;
    }

    private Crypto mapCandleToCrypto(List<Object> candle, String symbol) {
        Float closingPrice = Float.parseFloat(candle.get(4).toString()); // Closing price
        long timestampMillis = Long.parseLong(candle.get(0).toString()); // Timestamp in milliseconds

        // Convert timestamp to formatted date string
        Date timestampDate = new Date(timestampMillis);
        String formattedTimestamp = CurrentTime.getNewDateFormatter().format(timestampDate);

        return new Crypto(symbol, closingPrice, formattedTimestamp);
    }
}
