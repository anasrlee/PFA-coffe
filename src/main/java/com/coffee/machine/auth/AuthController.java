package com.coffee.machine.auth;

import com.coffee.machine.auth.exceptions.InvalidCredentialsException;
import com.coffee.machine.model.User;
import com.coffee.machine.model.UserRole;
import com.coffee.machine.auth.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {
        try {
            User user = authService.authenticate(username, password);
            session.setAttribute("user", user);

            return switch (user.getRole()) {
                case ADMIN -> "redirect:/admin/dashboard";
                case BARISTA -> "redirect:/barista/orders";
                case CASHIER -> "redirect:/cashier/transactions";
                case SERVER -> "redirect:/server/tables";
                default -> "redirect:/login?error=invalid_role";
            };
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
            case "invalid_credentials" -> "Nom d'utilisateur ou mot de passe incorrect";
            case "access_denied" -> "Accès refusé. Vous n'avez pas les droits nécessaires";
            case "session_expired" -> "Session expirée. Veuillez vous reconnecter";
            case "logout" -> "Vous avez été déconnecté avec succès";
            default -> "Erreur lors de la connexion";
        };
    }
}