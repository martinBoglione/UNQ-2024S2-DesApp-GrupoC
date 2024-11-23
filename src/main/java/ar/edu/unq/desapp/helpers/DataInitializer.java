package ar.edu.unq.desapp.helpers;

import ar.edu.unq.desapp.repositories.UserRepository;
import ar.edu.unq.desapp.service.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CryptoService cryptoService;

    @Override
    public void run(String... args) {
        log.info("==> INICIO Generando datos en la BD");

        this.generateUsers();
        this.generateQuotes();
        this.generateOrders();
        this.generateTransactions();

        log.info("==> FIN Generando datos en la BD");
    }

    private void generateUsers() {
        log.info("Generando usuarios...");
    }

    private void generateQuotes() {
        log.info("Generando cotizaciones...");
    }

    private void generateOrders() {
        log.info("Generando órdenes...");
    }

    private void generateTransactions() {
        log.info("Generando transacciones...");
    }
}
