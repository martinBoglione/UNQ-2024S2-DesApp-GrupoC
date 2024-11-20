package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.repositories.QuoteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BinancePricesService {

    private final QuoteRepository quoteRepository;

    public BinancePricesService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Scheduled(fixedDelayString="${cryptoexchange.quotes.refreshDelay:600}000", initialDelay = 2000)
    public void fetchQuotes() {
        log.info("Trayendo cotizaciones");
    }
}
