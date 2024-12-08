package ar.edu.unq.desapp.model;

import ar.edu.unq.desapp.model.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

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
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("The user name and surname length must be between 3 and 30 characters", exception.getStatusText());

        user.setName("Martin");
        user.setSurname("B");
        exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("The user name and surname length must be between 3 and 30 characters", exception.getStatusText());
    }

    @Test
    void testInvalidEmailThrowsException() {
        user.setEmail("invalid-email");
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("The email is not valid.", exception.getStatusText());
    }

    @Test
    void testInvalidAddressThrowsException() {
        user.setAddress("Short");
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("The address  length must be between 10 and 30 characters", exception.getStatusText());
    }

    @Test
    void testInvalidPasswordThrowsException() {
        user.setPassword("weak"); // No uppercase or special characters
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("Password must have a minimum 6 characters long and contain 1 uppercase and lowercase letter and a special character", exception.getStatusText());

        user.setPassword("StrongPass"); // Missing special character
        exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("Password must have a minimum 6 characters long and contain 1 uppercase and lowercase letter and a special character", exception.getStatusText());
    }

    @Test
    void testInvalidCVUThrowsException() {
        user.setCvu("12345678901234567890"); // Less than 22 characters
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("The CVU length must be 22 characters", exception.getStatusText());
    }

    @Test
    void testInvalidWalletAddressThrowsException() {
        user.setWalletAddress("1234567"); // Less than 8 characters
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, user::validateUser);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("The Wallet length must be 8 characters", exception.getStatusText());
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
