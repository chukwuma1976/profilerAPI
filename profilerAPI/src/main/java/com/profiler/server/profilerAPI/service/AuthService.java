package com.profiler.server.profilerAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.model.User;
import com.profiler.server.profilerAPI.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ✅ Register a new user
    public boolean register(String username, String password, String email) {
        if (userRepo.findByUsername(username) != null) {
            return false; // username already exists
        }

        String hashed = passwordEncoder.encode(password);
        User newUser = new User(username, hashed, email);
        userRepo.save(newUser);

        return true;
    }

    // ✅ Authenticate a user (login)
    public boolean authenticate(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null) return false;
        return passwordEncoder.matches(password, user.getPassword());
    }

    // ✅ Start reset flow (generate token)
    public String initiatePasswordReset(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) return null;

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepo.save(user); // persist token

        return token; // normally email this
    }

    // ✅ Reset password with token
    public boolean resetPassword(String token, String newPassword) {
        User user = userRepo.findByResetToken(token);
        if (user == null) return false;

        String newHashed = passwordEncoder.encode(newPassword);
        user.setPassword(newHashed);
        user.setResetToken(null); // invalidate token
        userRepo.save(user);

        return true;
    }

    // ✅ Check if user exists
    public boolean userExists(String username) {
        return userRepo.findByUsername(username) != null;
    }
}
