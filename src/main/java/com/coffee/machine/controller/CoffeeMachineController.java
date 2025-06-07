package com.coffee.machine.controller;

import com.coffee.machine.model.CoffeMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeMachineController {

    private final CoffeMachine coffeeMachine = new CoffeMachine(10);  // Exemple de 10 tasses disponibles

    @GetMapping("/brew")
    public String brewCoffee() {
        coffeeMachine.brewCoffee();
        return "Café préparé!";
    }

    @GetMapping("/status")
    public String status() {
        return coffeeMachine.isOn() ? "Machine en marche, " + coffeeMachine.getCupsAvailable() + " tasses disponibles." : "Machine éteinte.";
    }
}
