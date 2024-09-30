package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BinancePricesService {
    @Autowired
    QuoteRepository quoteRepository;

    @Scheduled(fixedDelayString="${cryptoexchange.quotes.refreshDelay:600}000", initialDelay = 2000)
    public void fetchQuotes() {
        System.out.println("trayendo cotizaciones");
    }
}
