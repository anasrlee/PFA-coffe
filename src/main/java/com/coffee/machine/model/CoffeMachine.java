package com.coffee.machine.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoffeMachine {
    // Getters et setters
    private int cupsAvailable;
    private boolean isOn;

    public CoffeMachine(int cupsAvailable) {
        this.cupsAvailable = cupsAvailable;
        this.isOn = false;
    }

    public void brewCoffee() {
        if (isOn && cupsAvailable > 0) {
            cupsAvailable--;
            System.out.println("Café en cours de préparation...");
        } else {
            System.out.println("Machine éteinte ou pas assez de tasses.");
        }
    }

}

