package com.donauswap.donauswap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import java.util.Set;

// Kategorie um Angebote besser zu strukturieren - der Kategoriename ist ein übergeordneter Ordnungsbegriff für Dienstleistungs- oder Warenangebote
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; //  fortlaufende id
    private String name; // Kategoriebezeichnung
    private String description; //  Kategorie Beschreibung
    private String picturePath; //  Pfad des Kategoriebildes - Ablageort außerhalb vom Projektordner in categories

    // Verknüpfung category mit tradeable
    @OneToMany(mappedBy = "category")
    private Set<Tradeable> tradeables;

    // Standardkonstruktor
    public Category() {

    }

    // Konstruktor mit Parameter
    public Category(String name, String description, String picturePath) {
        this.name = name;
        this.description = description;
        this.picturePath = picturePath;
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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
