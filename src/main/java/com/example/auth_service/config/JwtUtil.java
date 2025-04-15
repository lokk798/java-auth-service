package com.example.auth_service.config;

import com.example.auth_service.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private int expirationMs;

    // create cryptographic signing key
    private Key getSigninKey(){
        byte[] keyBytes =secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user){
        Map claims =  new HashMap<>(); // claim data field inside the token
        claims.put("roles", user.getRoles());

        return Jwts.builder().setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();

    }

}
