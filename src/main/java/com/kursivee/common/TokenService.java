package com.kursivee.common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public static final String HEADER = "Token";
    private static final String SECRET_KEY = "SECRET";
    private static final String ISSUER = "myserver";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final JWTVerifier VERIFIER;

    static {
        VERIFIER = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
    }

    public String createToken(String role) {
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim("Role", role)
                .sign(ALGORITHM);
        return token;
    }

    public DecodedJWT validate(String token) {
        try {
            return VERIFIER.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
