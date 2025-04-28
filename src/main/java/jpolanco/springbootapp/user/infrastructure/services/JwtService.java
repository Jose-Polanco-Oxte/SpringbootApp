package jpolanco.springbootapp.user.infrastructure.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jpolanco.springbootapp.user.domain.model.User;
import jpolanco.springbootapp.user.domain.model.valueobjects.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.expiration.refresh}")
    private long refreshExpirationTime;

    public String generateToken(final User user) {
        return buildToken(user, expirationTime);
    }

    public String generateRefreshToken(final User user) {
        return buildToken(user, refreshExpirationTime);
    }

    private String buildToken(final User user, final long expirationTime) {
        return Jwts.builder()
                .id(user.getId())
                .claims(Map.of("Roles", user
                        .getRoles()
                        .stream()
                        .map(Role::getValue)
                        .toList()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        System.out.println("Secret Key: " + secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        System.out.println("Key Bytes Length: " + keyBytes.length);
        System.out.println("Key Bytes: " + Arrays.toString(keyBytes));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token, String email) {
        final var username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
