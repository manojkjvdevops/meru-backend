package com.schoolportal.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // A secure 256-bit secret key generated explicitly for signature verification
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // Token expiration window: 24 Hours
    private static final long EXPIRATION_MS = 86400000;

    // Generates a cryptographically signed token populated with user payload context
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(KEY)
                .compact();
    }

    // Extracts the verified subject email address from the token string
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validates if the token signature is pristine and within expiration boundaries
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}