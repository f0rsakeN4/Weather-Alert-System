package com.f0rsakeN.weatheralert.Utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 *
 * @Author f0rsakeN
 * @Date 2024/10/30
 */

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    // 生成JWT
    public String generateToken(Long userId) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
    }


    // 验证JWT
    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 从JWT中获取用户ID
    public Long getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return Long.valueOf(decodedJWT.getSubject());
    }
}
