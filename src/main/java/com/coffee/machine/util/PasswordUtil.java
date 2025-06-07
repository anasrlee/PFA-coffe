package com.coffee.machine.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
}

