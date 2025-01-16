package se.ifmo.healthcare.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import java.security.Key;
import java.util.Date;

@ApplicationScoped
public class JWTUtil {

    private final Key secretKey = Keys.hmacShaKeyFor("dofhhvncdoshaov832239cbcdnkja12338291bhjb".getBytes());
    private final long expirationTime = 3600000;

    public String generateToken(String username, String password, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("password", password)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
