package com.profiler.server.profilerAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender mailSender;

    public boolean register(String username, String password, String email) {
        if (userRepo.findByUsername(username) != null) return false;
        String hashed = passwordEncoder.encode(password);
        User newUser = new User(username, hashed, email);
        userRepo.save(newUser);
        return true;
    }

    public boolean authenticate(String username, String password) {
        User user = userRepo.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public boolean initiatePasswordReset(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) return false;

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepo.save(user);

        sendPasswordResetEmail(user.getEmail(), token);

        return true;
    }

    public boolean resetPassword(String token, String newPassword) {
        Optional<User> userOptional = userRepo.findAll()
                .stream()
                .filter(u -> token.equals(u.getResetToken()))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    private void sendPasswordResetEmail(String email, String token) {
        String resetLink = "http://localhost:4200/reset-password?token=" + token; // adjust frontend URL

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Click the following link to reset your password:\n" + resetLink);

        mailSender.send(message);
    }
}
