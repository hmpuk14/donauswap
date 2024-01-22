package com.donauswap.donauswap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Kind-Klasse der DIENSTLEISTUNGEN (Unterscheidung Ware - Dienstleistung)
@Entity
@Table(name = "t_service") // Datenbanktabellennamen nochmal direkt angegeben, damit der Namens-Unterschied zur Java Klasse nicht zu Fehlern führt
// TService erbt von Tradeable
public class TService extends Tradeable {

    private String serviceCondition; // die Zustandsbezeichnung der Dienstleistungen (unterscheidet sich in den Begriffen wesentlich von jener der Waren, kann also nicht geerbt werden)

    // Standardkonstruktor
    public TService() {
    }

    // Konstruktor mit Parameter
    public TService(String serviceCondition) {
        this.serviceCondition = serviceCondition;
    }

    public String getServiceCondition() {
        return serviceCondition;
    }

    public void setServiceCondition(String serviceCondition) {
        this.serviceCondition = serviceCondition;
    }
}
