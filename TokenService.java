package com.project.back_end.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // key cho signing JWT
    private final long expirationMillis = 24 * 60 * 60 * 1000; // 1 ngày

    // Tạo token dựa trên email
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    // Lấy signing key (nếu cần cho validation ở chỗ khác)
    public Key getSigningKey() {
        return key;
    }
}
