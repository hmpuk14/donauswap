package com.donauswap.donauswap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


// Kind-Klasse der WAREN (Unterscheidung Ware - Dienstleistung)
@Entity
@Table(name = "t_item")    // Datenbanktabellennamen nochmal direkt angegeben, damit der Namens-Unterschied zur Java Klasse nicht zu Fehlern führt

// TItem erbt von Tradeable
public class TItem extends Tradeable {

    private String itemCondition; // die Zustandsbezeichnung von Waren (unterscheidet sich in den Begriffen wesentlich von Dienstleistungen, kann also nicht geerbt werden)

    // Standardkonstruktor
    public TItem() {
    }

    // Konstruktor mit Parameter
    public TItem(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    // Getter und Setter
    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }
}
