package com.coffee.machine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerController {

    @GetMapping("/server/tables")
    public String serverInterface() {
        return "server"; // server.html
    }
}
