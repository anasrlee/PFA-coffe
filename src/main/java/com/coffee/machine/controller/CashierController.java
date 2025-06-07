package com.coffee.machine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CashierController {

    @GetMapping("/cashier/transactions")
    public String cashierInterface() {
        return "cashier"; // cashier.html
    }
}