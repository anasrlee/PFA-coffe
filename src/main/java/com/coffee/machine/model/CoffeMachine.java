package com.coffee.machine.model;

public class CoffeMachine {
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

    // Getters et setters
    public int getCupsAvailable() {
        return cupsAvailable;
    }

    public void setCupsAvailable(int cupsAvailable) {
        this.cupsAvailable = cupsAvailable;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}

