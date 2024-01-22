package com.donauswap.donauswap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.persistence.OneToMany;
import java.util.Set;

// Klasse zur Authentifizierung
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //  fortlaufende id

    @Column(unique = true)
    @NotEmpty
    private String email; //  E-Mail Adresse mit der sich der User registriert

    @NotEmpty
    private String password; //  Passwort mit dem sich der User registriert

    @NotEmpty
    private String firstName; // Vorname den der User bei der Registrierung angiebt

    @NotEmpty
    private String lastName; // Nachname den der User bei der Registrierung angiebt

    @NotEmpty
    private String street; // Straße den der User bei der Registrierung angiebt

    @NotNull
    @Min(0)
    private Integer postalCode; // PLZ die der User bei der Registrierung angiebt

    @NotEmpty
    private String city; // Ort den der User bei der Registrierung angiebt

    @Column(name = "dcoin_saldo")
    private Double dcoinSaldo; // Der Saldo an Donaucoins wird in der Datenbank bei der Registrierung Null gesetzt

    // Verknüpfung user mit tradeable
    @OneToMany(mappedBy = "user")
    private Set<Tradeable> tradeables;

    // Standardkonstruktor
    public User() {
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getDcoinSaldo() {
        return dcoinSaldo;
    }

    public void setDcoinSaldo(Double dcoinSaldo) {
        this.dcoinSaldo = dcoinSaldo;
    }
}
