package ar.edu.unq.DevApp.model;

import ar.edu.unq.DevApp.model.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    //@Column(nullable = false, unique=true)
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
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$", message = "La contraseña debe tener al menos una minúscula, una mayúscula, un carácter especial y tener una longitud mínima de 6 caracteres.")
    private String password;

    @Column(nullable = false)
    @Size(min=22, max=22)
    private String cvu;

    @Column(nullable = false)
    @Size(min=8, max=8)
    private String walletAddress;

    public User(String name, String surname, String email, String address, String password, String cvu, String walletAddress) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.walletAddress = walletAddress;
    }

    public User() {}

    public void validateUser() {
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


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
