package com.donauswap.donauswap.model;

import java.time.LocalDateTime;

// Das ist ein DTO=Data Transfer Object- Es war nötig, damit die Datenübertragung zwischen Angular und Springboot funktioniert
public class TradeData {

    private Double price; //  Price in Donaucoin
    private Long buyerId; //  User Id des "Käufers" (derjenige der das Tauschgeschäft abschließt und Ware oder Dienstleistung erhält
    private Long sellerId; //  User Id des Tausch - Anbieters
    private Long itemOrServiceId; //  Id der Dienstleistung bzw. der Tauschware
    private Long mainPictureId; //  Id des Hauptbildes
    private LocalDateTime timeStamp; //  Datum und Uhrzeit des abgewickelten Tauschs

    // Konstruktor
    public TradeData() {
    }

    // Getter und Setter
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
