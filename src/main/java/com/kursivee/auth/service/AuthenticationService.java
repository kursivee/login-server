package com.kursivee.auth.service;

import com.kursivee.auth.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public User authenticate(String username, String password) {
        if(username.equals("user") && password.equals("password")) {
            return new User(username, Role.ADMIN);
        }
        if(username.equals("guest") && password.equals("password")) {
            return new User(username, Role.GUEST);
        }
        return null;
    }
}
