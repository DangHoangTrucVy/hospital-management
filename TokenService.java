package com.project.backend.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private Map<String, String> tokenStore = new HashMap<>();

    public String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, username);
        return token;
    }

    public boolean validateToken(String token) {
        return tokenStore.containsKey(token);
    }

    public String getUsernameFromToken(String token) {
        return tokenStore.get(token);
    }

    public void revokeToken(String token) {
        tokenStore.remove(token);
    }
}
