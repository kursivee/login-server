package com.kursivee.auth.rest;

import com.kursivee.auth.model.User;
import com.kursivee.auth.service.AuthenticationService;
import com.kursivee.common.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;


@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws AccessDeniedException {
        User user = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if(null != user) {
            return new LoginResponse(tokenService.createToken(user.getRole().name()));
        }
        throw new AccessDeniedException("Invalid credentials");
    }
}
