package com.anish.flowstate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expiration;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
        this.expiration = expiration;
    }
    public String generateToken(
            UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + expiration
                        )
                )
                .signWith(key)
                .compact();
    }
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token) {

        return extractAllClaims(token)
                .getSubject();
    }
    private Date extractExpiration(String token) {

        return extractAllClaims(token)
                .getExpiration();
    }
    private boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        return extractUsername(token)
                .equals(userDetails.getUsername())
                &&
                !isTokenExpired(token);
    }

}
