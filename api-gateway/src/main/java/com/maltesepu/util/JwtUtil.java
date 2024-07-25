package com.maltesepu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class JwtUtil {


//    public static final String SECRET = generateSecretKey();
//
//    private static String generateSecretKey() {
//        byte[] key = new byte[32];
//        new SecureRandom().nextBytes(key);
//        return Base64.getEncoder().encodeToString(key);
//    }

    public static final String SECRET = "249857204875209834572938476928347592375492743593549384753945734957";

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String validateToken(String token) {
        final Claims claims = extractAllClaims(token);
        return "Token is Valid!";
    }
}
