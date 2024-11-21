package ar.edu.unq.desapp.model;

import ar.edu.unq.desapp.model.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .name("Martin")
                .surname("Boglione")
                .email("martinboglione97@gmail.com")
                .address("Segurola y Habana 4310")
                .password("Maradona10!")
                .cvu("1234567890123456789012")
                .walletAddress("12345678")
                .build();
    }

    @Test
    void testValidUser() {
        Assertions.assertDoesNotThrow(() -> user.validateUser());
    }

    @Test
    void testInvalidNameThrowsException() {
        user.setName("Ma");
        Exception exception = Assertions.assertThrows(InvalidNameException.class, user::validateUser);
        Assertions.assertEquals(InvalidNameException.class, exception.getClass());

        user.setName("Martin");
        user.setSurname("B");
        exception = Assertions.assertThrows(InvalidNameException.class, user::validateUser);
        Assertions.assertEquals(InvalidNameException.class, exception.getClass());
    }

    @Test
    void testInvalidEmailThrowsException() {
        user.setEmail("invalid-email");
        Exception exception = Assertions.assertThrows(InvalidEmailException.class, user::validateUser);
        Assertions.assertEquals(InvalidEmailException.class, exception.getClass());
    }

    @Test
    void testInvalidAddressThrowsException() {
        user.setAddress("Short");
        Exception exception = Assertions.assertThrows(InvalidAddressException.class, user::validateUser);
        Assertions.assertEquals(InvalidAddressException.class, exception.getClass());
    }

    @Test
    void testInvalidPasswordThrowsException() {
        user.setPassword("weak"); // No uppercase or special characters
        Exception exception = Assertions.assertThrows(InvalidPasswordException.class, user::validateUser);
        Assertions.assertEquals(InvalidPasswordException.class, exception.getClass());

        user.setPassword("StrongPass"); // Missing special character
        exception = Assertions.assertThrows(InvalidPasswordException.class, user::validateUser);
        Assertions.assertEquals(InvalidPasswordException.class, exception.getClass());
    }

    @Test
    void testInvalidCVUThrowsException() {
        user.setCvu("12345678901234567890"); // Less than 22 characters
        Exception exception = Assertions.assertThrows(InvalidCVUException.class, user::validateUser);
        Assertions.assertEquals(InvalidCVUException.class, exception.getClass());
    }

    @Test
    void testInvalidWalletAddressThrowsException() {
        user.setWalletAddress("1234567"); // Less than 8 characters
        Exception exception = Assertions.assertThrows(InvalidWalletException.class, user::validateUser);
        Assertions.assertEquals(InvalidWalletException.class, exception.getClass());
    }

    @Test
    void testIsValidName() {
        Assertions.assertEquals(true, user.isValidName("Martin", "Boglione"));
        Assertions.assertEquals(false, user.isValidName("Ma", "Boglione"));
        Assertions.assertEquals(false, user.isValidName("Martin", "B"));
    }

    @Test
    void testIsValidEmail() {
        Assertions.assertEquals(true, user.isValidEmail("martinboglione97@gmail.com"));
        Assertions.assertEquals(false, user.isValidEmail("invalid-email"));
    }

    @Test
    void testIsValidAddress() {
        Assertions.assertEquals(true, user.isValidAddress("Segurola y Habana 4310"));
        Assertions.assertEquals(false, user.isValidAddress("Short"));
    }

    @Test
    void testIsValidPassword() {
        Assertions.assertEquals(true, user.isValidPassword("Maradona10!"));
        Assertions.assertEquals(false, user.isValidPassword("weak"));
        Assertions.assertEquals(false, user.isValidPassword("StrongPass"));
    }

    @Test
    void testIsValidCVU() {
        Assertions.assertEquals(true, user.isValidCVU("1234567890123456789012"));
        Assertions.assertEquals(false, user.isValidCVU("12345678901234567890"));
    }

    @Test
    void testIsValidWalletAddress() {
        Assertions.assertEquals(true, user.isValidWallet("12345678"));
        Assertions.assertEquals(false, user.isValidWallet("1234567"));
    }
}
