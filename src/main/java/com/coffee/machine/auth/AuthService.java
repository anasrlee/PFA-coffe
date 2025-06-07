package com.coffee.machine.auth;

import com.coffee.machine.model.User;
import com.coffee.machine.repository.UserRepository;
import com.coffee.machine.util.PasswordUtil;
import com.coffee.machine.auth.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) throws InvalidCredentialsException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Utilisateur non trouvé"));

        if (!PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException("Mot de passe incorrect");
        }

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Compte désactivé");
        }

        return user; // Retourne l'utilisateur complet plutôt que juste le rôle
    }
}