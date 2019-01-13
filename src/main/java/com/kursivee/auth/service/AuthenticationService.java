package com.kursivee.auth.service;

import com.kursivee.auth.model.User;
import com.kursivee.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        com.kursivee.data.User user = userRepository.findByUsername(username);
        if(user == null || !password.equals(user.getPassword())) {
            return null;
        }
        if(username.equals("ADMIN")) {
            return new User(username, Role.ADMIN);
        } else {
            return new User(username, Role.GUEST);
        }
    }
}
