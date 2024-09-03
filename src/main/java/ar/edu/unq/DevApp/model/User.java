package ar.edu.unq.DevApp.model;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String cvu;

    @Column(nullable = false)
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

    public User() {

    }
}
