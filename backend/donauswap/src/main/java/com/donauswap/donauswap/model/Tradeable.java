package com.donauswap.donauswap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;

// Eltern-Klasse für Handelsprodukte und Dienstleistungen (vieles wird von beiden Klassen benötigt und kann vererbt werden:)
@Entity
// modeliert Vererbung in der Datenbank
@Inheritance(strategy = InheritanceType.JOINED)
public class Tradeable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //  fortlaufende id

    private String name; //  vom tauschenden User eingegebener Produkt oder Dienstleistungsname
    private String description; //  vom tauschenden User eingegebene Beschreibung
    private Double priceDcoin;  //  vom tauschenden User eingegebener Preis in Donaucoin (=Token)
    private String tradeType; //  vom tauschenden User zugeordnete Kind-Klasse TItem oder TService (Tandlerspot-Warentausch oder Tagwerkhub - Dienstleistungstausch)
    private String rateUnit; //  vom tauschenden User ausgewählte Einheit für die der Preis gilt
    @Transient
    private String mainImagePath; //  Pfad des Hauptbildes (s. Image) keine Datenbankspalte benötigt, da diese durchgehend Null wäre, weil der Inhalt in Image ist. Die Klasse hier in tradeable macht die Codestruktur für Frontenddarstellung vom Hauptbild und anderen Tradeable Komponenten einfacher.

    // Verknüpfung  tradeable mit category
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    // Verknüpfung  tradeable mit user
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Standardkonstruktor
    public Tradeable() {
    }

    // Konstruktor mit Parameter
    public Tradeable(String name, String description, Double priceDcoin, String tradeType, Category category, User user) {
        this.name = name;
        this.description = description;
        this.priceDcoin = priceDcoin;
        this.tradeType = tradeType;
        this.category = category;
        this.user = user;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceDcoin() {
        return priceDcoin;
    }

    public void setPriceDcoin(Double priceDcoin) {
        this.priceDcoin = priceDcoin;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMainImagePath() {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        this.mainImagePath = mainImagePath;
    }

    // Optional: toString-Methode
    @Override
    public String toString() {
        return "Tradeable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priceDcoin=" + priceDcoin +
                ", tradeType='" + tradeType + '\'' +
                ", category=" + category +
                ", user=" + user +
                ", mainImagePath='" + mainImagePath + '\'' +
                '}';
    }
}
