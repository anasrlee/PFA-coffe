package com.coffee.machine.controller;


import com.coffee.machine.auth.exceptions.InvalidCredentialsException;
import com.coffee.machine.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", getErrorMessage(error));
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {
        try {
            var authResponse = authService.authenticate(username, password);
            session.setAttribute("user", authResponse.user());
            session.setAttribute("token", authResponse.token());

            return "redirect:/redirect-by-role";
        } catch (InvalidCredentialsException e) {
            return "redirect:/login?error=invalid_credentials";
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    private String getErrorMessage(String errorCode) {
        return switch (errorCode) {
            case "invalid_credentials" -> "Identifiants incorrects";
            case "logout" -> "Déconnexion réussie";
            default -> "Erreur d'authentification";
        };
    }
}
