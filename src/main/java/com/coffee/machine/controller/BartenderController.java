package com.coffee.machine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BartenderController {

    @GetMapping("/bartender/orders")
    public String baristaInterface() {
        return "bartender";
    }
}