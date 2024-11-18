package ar.edu.unq.desapp.model;

import ar.edu.unq.desapp.model.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min=3, max=30)
    private String name;

    @Column(nullable = false)
    @Size(min=3, max=30)
    private String surname;

    @Column(nullable = false)
    @Email(message = "Invalid email format.")
    private String email;

    @Column(nullable = false)
    @Size(min=10, max=30)
    private String address;

    @Column(nullable = false)
    @Size(min=6)
    private String password;
    private static final Logger logger = Logger.getLogger(User.class.getName());

    @Column(nullable = false)
    @Size(min=22, max=22)
    private String cvu;

    @Column(nullable = false)
    @Size(min=8, max=8)
    private String walletAddress;

    @Column(nullable = false)
    private boolean deleted = false;

    public void validateUser() {
        logger.info("Validating user with password length: " + password.length());
        if(!isValidName(this.name,this.surname))  {throw new InvalidNameException();}

        if (!isValidEmail(this.email)) {throw new InvalidEmailException();}

        if (!isValidAddress(this.address)) {throw new InvalidAddressException();}

        if (!isValidPassword(this.password)) {throw new InvalidPasswordException();}

        if (!isValidCVU(this.cvu)) {throw new InvalidCVUException();}

        if (!isValidWallet(this.walletAddress)) {throw new InvalidWalletException();}
    }

    public boolean isValidName(String name, String surname) {
        return (name.length() >= 3 && name.length()<= 30) && (surname.length() >= 3 && surname.length() <= 30);
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidAddress(String address) {
        return address.length() >= 10 && address.length() <= 30;
    }

    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidCVU(String cvu) {
        return cvu.length() == 22;
    }

    public boolean isValidWallet(String walletAddress) {
        return walletAddress.length() == 8;
    }
}
