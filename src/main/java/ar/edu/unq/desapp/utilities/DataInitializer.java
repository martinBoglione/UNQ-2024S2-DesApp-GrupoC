package ar.edu.unq.desapp.utilities;

import ar.edu.unq.desapp.model.User;
import ar.edu.unq.desapp.repositories.UserRepository;
import ar.edu.unq.desapp.service.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

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

        String genericUserPass = "Maradona10!";

        User user1 = User.builder().name("Hanzo").surname("Hasashi").email("scorpion@mortalkombat.com").address("Midway Games")
                .password(passwordEncoder.encode(genericUserPass)).cvu("1234567890123456789012").walletAddress("0xw9y2di").operationsQuantity(0).reputation(0).build();

        User user2 = User.builder().name("Liu").surname("Kang").email("liukang@mortalkombat.com").address("Midway Games")
                .password(passwordEncoder.encode(genericUserPass)).cvu("1234567890123456789012").walletAddress("0xm2y9wi").operationsQuantity(0).reputation(0).build();

        User user3 = User.builder().name("Johnny").surname("Cage").email("johnnycage@mortalkombat.com").address("Midway Games")
                .password(passwordEncoder.encode(genericUserPass)).cvu("1234567890123456789012").walletAddress("0xi9w2yd").operationsQuantity(0).reputation(0).build();

        User user4 = User.builder().name("Shang").surname("Tsung").email("shangtsung@mortalkombat.com").address("Midway Games")
                .password(passwordEncoder.encode(genericUserPass)).cvu("1234567890123456789012").walletAddress("0xd9y2iw").operationsQuantity(0).reputation(0).build();

        User user5 = User.builder().name("Noob").surname("Saibot").email("noobsaibot@mortalkombat.com").address("Midway Games")
                .password(passwordEncoder.encode(genericUserPass)).cvu("1234567890123456789012").walletAddress("0x2y9wdi").operationsQuantity(0).reputation(0).build();

        /* Sonya Blade, Kung Lao, Jax Briggs, Shao Kahn, Sub Zero */
        /* 0x9w2ymi  0xm1y9dw   0xi2y9wd   0xd1y9mw   0x2w9ymi */

        userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
    }

    private void generateQuotes() {
        log.info("Generando cotizaciones...");
        /* TODO completar */
    }

    private void generateOrders() {
        log.info("Generando órdenes...");
        /* TODO completar */
    }

    private void generateTransactions() {
        log.info("Generando transacciones...");
        /* TODO completar */
    }
}
