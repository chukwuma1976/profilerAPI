package com.profiler.server.profilerAPI.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.model.User;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public boolean register(String username, String password, String email) {
        if (users.containsKey(username)) return false;
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(username, new User(username, hashed, email));
        return true;
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && BCrypt.checkpw(password, user.getPassword());
    }
	
}
