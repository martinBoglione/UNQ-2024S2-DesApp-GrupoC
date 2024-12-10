package ar.edu.unq.desapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;
import java.util.regex.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@Slf4j
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

    @Column(unique=true, nullable = false)
    @Email(message = "Invalid email format.")
    private String email;

    @Column(nullable = false)
    @Size(min=10, max=30)
    private String address;

    @Column(nullable = false)
    @Size(min=6)
    private String password;

    @Column(nullable = false)
    @Size(min=22, max=22)
    private String cvu;

    @Column(nullable = false)
    @Size(min=8, max=8)
    private String walletAddress;

    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = false;

    @Column(nullable = false)
    @Builder.Default
    private Integer operationsQuantity = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer reputation = 0;

    private void thrwoBadRequestError(String message) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
    }

    public void validateUser() {
        if(!isValidName(this.name,this.surname))  { thrwoBadRequestError("The user name and surname length must be between 3 and 30 characters"); }
        if (!isValidEmail(this.email)) { thrwoBadRequestError("The email is not valid."); }
        if (!isValidAddress(this.address)) { thrwoBadRequestError("The address  length must be between 10 and 30 characters"); }
        if (!isValidPassword(this.password)) { thrwoBadRequestError("Password must have a minimum 6 characters long and contain 1 uppercase and lowercase letter and a special character"); }
        if (!isValidCVU(this.cvu)) { thrwoBadRequestError("The CVU length must be 22 characters"); }
        if (!isValidWallet(this.walletAddress)) { thrwoBadRequestError("The Wallet length must be 8 characters"); }
    }

    public boolean isValidName(String name, String surname) {
        return (name.length() >= 3 && name.length()<= 30) && (surname.length() >= 3 && surname.length() <= 30);
    }

    public boolean isValidEmail(String email) {
        String validationRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";
        return Pattern.compile(validationRegex).matcher(email).matches();
    }

    public boolean isValidAddress(String address) {
        return address.length() >= 10 && address.length() <= 30;
    }

    public boolean isValidPassword(String password) {
        String validationRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,}$";
        return Pattern.compile(validationRegex).matcher(password).matches();
    }

    public boolean isValidCVU(String cvu) {
        return cvu.length() == 22;
    }

    public boolean isValidWallet(String walletAddress) {
        return walletAddress.length() == 8;
    }
}
