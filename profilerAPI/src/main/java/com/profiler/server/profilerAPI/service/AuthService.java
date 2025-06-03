package com.profiler.server.profilerAPI.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.model.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean register(String username, String password, String email) {
        if (users.containsKey(username)) return false;
        String hashed = passwordEncoder.encode(password);
        users.put(username, new User(username, hashed, email));
        return true;
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
    
    public String initiatePasswordReset(String username) {
        User user = users.get(username);
        if (user == null) return null;
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        return token; // Normally you'd email this token
    }

    public boolean resetPassword(String token, String newPassword) {
        for (User user : users.values()) {
            if (token.equals(user.getResetToken())) {
                String newHashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                user.setPassword(newHashed);
                user.setResetToken(null); // Invalidate token
                return true;
            }
        }
        return false;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
	
}
