package com.kursivee.common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    public static final String HEADER = "Token";
    private static final String SECRET_KEY = "SECRET";
    private static final String ISSUER = "myserver";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final JWTVerifier VERIFIER;
    private static final int ACCESS_TOKEN_EXPIRY = 15;
    private static final int REFRESH_TOKEN_EXPIRY = 60;

    static {
        VERIFIER = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
    }

    @Autowired
    private TokenStore tokenStore;

    public String createToken(String role) {
        Date expiration = DateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRY).toDate();
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim("Role", role)
                .withExpiresAt(expiration)
                .sign(ALGORITHM);
        return token;
    }

    public String createRefreshToken(String username, String role) {
        Date expiration = DateTime.now().plusMinutes(REFRESH_TOKEN_EXPIRY).toDate();
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim("Refresh", true)
                .withClaim("User", username)
                .withClaim("Role", role)
                .withExpiresAt(expiration)
                .sign(ALGORITHM);
        tokenStore.create(username, token);
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        try {
            return VERIFIER.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public DecodedJWT verifyRefreshToken(String token) {
        DecodedJWT refreshToken = verifyToken(token);
        String username = refreshToken.getClaim("User").asString();
        String storedToken = tokenStore.get(username);
        if(null != storedToken && storedToken.equals(token)) {
            if(null != refreshToken && refreshToken.getClaim("Refresh").asBoolean()) {
                return refreshToken;
            }
        }
        return null;
    }
}
