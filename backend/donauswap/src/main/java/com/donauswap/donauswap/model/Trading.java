package com.donauswap.donauswap.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Klasse die Informationen über einen erfolgten Tausch zusammenführt
@Entity
public class Trading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // fortlaufende id

    private Double price; //  Price in Donaucoin

    @Column(name = "buyer_id")
    private Long buyerId; //  User Id des "Käufers" (derjenige der das Tauschgeschäft abschließt und Ware oder Dienstleistung erhält

    @Column(name = "seller_id")
    private Long sellerId; //  User Id des Tausch - Anbieters

    @Column(name = "item_or_service_id")
    private Long itemOrServiceId; //  Id der Dienstleistung bzw. der Tauschware

    @Column(name = "main_picture_id")
    private Long mainPictureId; //  Id des Hauptbildes

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp; //  Datum und Uhrzeit des abgewickelten Tauschs

    // Standardkonstruktor
    public Trading() {
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getItemOrServiceId() {
        return itemOrServiceId;
    }

    public void setItemOrServiceId(Long itemOrServiceId) {
        this.itemOrServiceId = itemOrServiceId;
    }

    public Long getMainPictureId() {
        return mainPictureId;
    }

    public void setMainPictureId(Long mainPictureId) {
        this.mainPictureId = mainPictureId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
