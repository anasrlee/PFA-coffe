package com.coffee.machine.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double currentQuantity;
    private String unit;
    private double thresholdLevel;

    public String monitorLevel() {
        if (currentQuantity <= thresholdLevel) {
            return "CRITICAL - Niveau bas (" + currentQuantity + " " + unit + ")";
        } else if (currentQuantity <= thresholdLevel * 2) {
            return "WARNING - Niveau moyen (" + currentQuantity + " " + unit + ")";
        }
        return "OK - Niveau bon (" + currentQuantity + " " + unit + ")";
    }
}
