package com.example.demo.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // 🔑 Khóa bí mật (được tạo 1 lần, bạn có thể lấy từ application.properties hoặc Secret Manager)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ⏱️ Thời hạn Access Token: 15 phút
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;

    // ⏱️ Thời hạn Refresh Token: 7 ngày
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    // Tạo Access Token
    public String generateToken(String email) {
        return buildToken(email, ACCESS_TOKEN_EXPIRATION);
    }

    // Tạo Refresh Token
    public String generateRefreshToken(String email) {
        return buildToken(email, REFRESH_TOKEN_EXPIRATION);
    }

    // Hàm build token chung
    private String buildToken(String email, long expiration) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Kiểm tra token hợp lệ
    public boolean validateToken(String token, String email) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String subject = claims.getSubject();
            return (subject.equals(email) && !claims.getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Lấy email từ token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
