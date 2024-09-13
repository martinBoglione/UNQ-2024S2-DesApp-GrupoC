package ar.edu.unq.DevApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min=3, max=30)
    @JsonProperty("name")
    private String name;

    @Column
    @Size(min=3, max=30)
    private String surname;

    @Column
    @Email(message = "El email debe tener un formato válido.")
    private String email;

    @Column
    @Size(min=10, max=30)
    private String address;

    @Column
    @Size(min=6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$", message = "La contraseña debe tener al menos una minúscula, una mayúscula, un carácter especial y tener una longitud mínima de 6 caracteres.")
    private String password;

    @Column
    @Size(min=22, max=22)
    private String cvu;

    @Column
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
