package com.profiler.server.profilerAPI.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.profiler.server.profilerAPI.model.User;
import com.profiler.server.profilerAPI.service.AuthService;

@RestController
@RequestMapping("api/v1/profiler/users/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User body) {
        String username = body.getUsername();
        String password = body.getPassword();
        String email = body.getEmail();
        boolean success = authService.register(username, password, email);
        return success ? "User registered" : "User already exists";
    }

    @PostMapping("/login")
    public String login(@RequestBody User body, HttpSession session) {
        String username = body.getUsername();
        String password = body.getPassword();

        if (authService.authenticate(username, password)) {
            session.setAttribute("username", username);
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }

    @GetMapping("/check")
    public String checkSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return username != null ? "Logged in as " + username : "Not logged in";
    }
    
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody User body) {
        String token = authService.initiatePasswordReset(body.getUsername());
        return token != null
            ? "Password reset token (simulate email): " + token
            : "User not found.";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody User body) {
        boolean result = authService.resetPassword(body.getResetToken(), body.getPassword());
        return result ? "Password reset successfully." : "Invalid or expired token.";
    }
}
