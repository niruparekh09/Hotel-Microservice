package com.nrv.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * This class handles key management, token validation and claim extraction using JWTs.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component
public class JwtUtil {

    @Value("${SECRET}")
    public String SECRET;

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    // Method to extract role from the JWT token
    public String extractRole(final String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);  // Extracting the "role" claim
    }

    // Private helper method to extract all claims
    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
