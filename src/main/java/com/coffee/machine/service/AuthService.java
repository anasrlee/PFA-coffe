package com.coffee.machine.service;



import com.coffee.machine.auth.exceptions.InvalidCredentialsException;
import com.coffee.machine.model.User;
import com.coffee.machine.repository.UserRepository;
import com.coffee.machine.util.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public AuthResponse authenticate(String username, String password)
            throws InvalidCredentialsException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException("Mot de passe incorrect");
        }

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Compte désactivé");
        }

        String token = jwtTokenUtil.generateToken(user);
        return new AuthResponse(user, token);
    }

    public record AuthResponse(User user, String token) {}
}