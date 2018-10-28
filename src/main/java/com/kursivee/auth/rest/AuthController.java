package com.kursivee.auth.rest;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kursivee.auth.model.User;
import com.kursivee.auth.service.AuthenticationService;
import com.kursivee.common.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth")
    public RefreshResponse auth(@RequestBody AuthRequest authRequest) throws AccessDeniedException {
        User user = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if(null != user) {
            return new RefreshResponse(tokenService.createToken(user.getRole().name()),
                    tokenService.createRefreshToken(user.getUsername(), user.getRole().name()));
        }
        throw new AccessDeniedException("Invalid credentials");
    }

    @PostMapping("/refresh")
    public RefreshResponse refresh(@RequestBody RefreshRequest refreshRequest) throws AccessDeniedException {
        DecodedJWT token = tokenService.verifyRefreshToken(refreshRequest.getToken());
        if(null != token) {
            String role = token.getClaim("Role").asString();
            String username = token.getClaim("User").asString();
            return new RefreshResponse(tokenService.createToken(role),
                    tokenService.createRefreshToken(username, role));
        }
        throw new AccessDeniedException("Invalid token");
    }
}
