package com.coffee.machine.service;

import com.coffee.machine.model.CoffeMachine;

public class CoffeeMachineService {
    private final CoffeMachine coffeeMachine;

    public CoffeeMachineService(CoffeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    // Ajoute une méthode pour gérer la logique métier si besoin
    public void refillCups(int amount) {
        coffeeMachine.setCupsAvailable(coffeeMachine.getCupsAvailable() + amount);
    }
}

