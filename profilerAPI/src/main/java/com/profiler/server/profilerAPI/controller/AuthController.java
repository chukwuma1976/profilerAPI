package com.profiler.server.profilerAPI.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.profiler.server.profilerAPI.model.User;
import com.profiler.server.profilerAPI.service.AuthService;
import com.profiler.server.profilerAPI.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/profiler/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User body) {
        boolean success = authService.register(body.getUsername(), body.getPassword(), body.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("status", success ? "success" : "error");
        response.put("message", success ? "User registered" : "User already exists");
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User body, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        if (authService.authenticate(body.getUsername(), body.getPassword())) {
            // Destroy any old session and create a new one
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true); // ✅ always create a new session
            request.changeSessionId(); // protection against session fixation
            session.setAttribute("username", body.getUsername());

            response.put("status", "success");
            response.put("username", body.getUsername());
            response.put("message", "Login successful");

            System.out.println("Login session ID: " + session.getId());
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
        }
        return response;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // ✅ only if exists
        Map<String, Object> response = new HashMap<>();

        if (session != null) {
            session.invalidate();
            response.put("status", "success");
            response.put("message", "Logged out successfully");
            System.out.println("Session invalidated.");
        } else {
            response.put("status", "error");
            response.put("message", "No active session");
        }
        return response;
    }

    @GetMapping("/check")
    public Map<String, Object> checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // ✅ don’t create if missing
        Map<String, Object> response = new HashMap<>();

        if (session != null && session.getAttribute("username") != null) {
            response.put("loggedIn", true);
            response.put("username", session.getAttribute("username"));
            System.out.println("Check session ID: " + session.getId());
        } else {
            response.put("loggedIn", false);
            System.out.println("Check: No session or username attribute.");
        }

        return response;
    }

    @PostMapping("/forgot-password")
    public Map<String, Object> forgotPassword(@RequestBody User body) {
        Map<String, Object> response = new HashMap<>();
        boolean success = authService.initiatePasswordReset(body.getUsername());

        if (success) {
            response.put("status", "success");
            response.put("message", "Password reset instructions sent to your email.");
        } else {
            response.put("status", "error");
            response.put("message", "User not found");
        }
        return response;
    }

    @PostMapping("/reset-password")
    public Map<String, Object> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword");

        boolean success = userService.resetPassword(token, newPassword);

        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("status", "success");
            response.put("message", "Password successfully reset");
        } else {
            response.put("status", "error");
            response.put("message", "Invalid or expired token");
        }
        return response;
    }
}
