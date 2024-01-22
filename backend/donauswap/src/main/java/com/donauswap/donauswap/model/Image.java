package com.donauswap.donauswap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

// Speicherung und Darstellung von Bildern zu den  Dienstleistungs- und Warenangebote
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // fortlaufende id
    private String picturePath; //  Pfad des Bildes - Ablageort außerhalb vom Projektordner in uploaded_images
    private boolean isMain; //  mit boolean true oder false wird das Hauptbild (boolean: true) gekennzeichnet

    // Verknüpfung mit tradeable über die ID
    @ManyToOne
    @JoinColumn(name = "tradeable_id", referencedColumnName = "id")
    private Tradeable tradeable;

    // Standardkonstruktor
    public Image() {
    }

    public void setTradeableId(Long tradeableId) {
        if (this.tradeable == null) {
            throw new IllegalStateException("Tradeable-Objekt muss zuerst zugewiesen werden, bevor die ID gesetzt werden kann.");
        }
        this.tradeable.setId(tradeableId);
    }

    // Konstruktor mit Parametern
    public Image(String picturePath, boolean isMain) {
        this.picturePath = picturePath;
        this.isMain = isMain;
    }

    // Getter- und Setter-Methoden
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public Tradeable getTradeable() {
        return tradeable;
    }

    public void setTradeable(Tradeable tradeable) {
        this.tradeable = tradeable;
    }
}
