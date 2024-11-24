package ar.edu.unq.desapp.model;

import ar.edu.unq.desapp.model.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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

    @Column(nullable = false)
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
    private boolean deleted = false;

    /*
    TODO: El usaurio deberia tener la cantidad de operaciones
     */
//    @Column(nullable = false)
//    private Integer operationsQuantity = 0;

    /*
    TODO: El usaurio deberia tener la reputacion
            Se calcula como cantidad de puntos otorgados / cantidad de operaciones.
            Si la persona no ha operado se muestra “Sin operaciones”
     */
//    @Column(nullable = false)
//    private Integer reputation = 0;


    public void validateUser() {
        log.info("Validating user with password length: " + password.length());

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
