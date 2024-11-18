package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.repositories.QuoteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class BinancePricesService {

    private static final Logger logger = Logger.getLogger(BinancePricesService.class.getName());
    private final QuoteRepository quoteRepository;

    public BinancePricesService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Scheduled(fixedDelayString="${cryptoexchange.quotes.refreshDelay:600}000", initialDelay = 2000)
    public void fetchQuotes() {
        logger.info("Trayendo cotizaciones");
    }
}
