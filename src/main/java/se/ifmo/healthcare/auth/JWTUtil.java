package se.ifmo.healthcare.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;


import java.security.Key;
import java.util.Date;

@Repository
@ApplicationScope
public class JWTUtil {

    private final Key secretKey = Keys.hmacShaKeyFor("dofhhvncdoshaov832239cbcdnkja12338291bhjb".getBytes());
    private final long expirationTime = 3600000;

    public String generateToken(String username, String role, Long id) {
        return Jwts.builder()
                .setSubject(username)
                .claim("id", id)
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
    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JWT token");
        }
    }

}
