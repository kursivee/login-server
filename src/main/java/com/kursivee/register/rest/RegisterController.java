package com.kursivee.register.rest;

import com.kursivee.data.User;
import com.kursivee.data.UserRepository;
import com.kursivee.register.model.RegisterRequest;
import com.kursivee.register.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        if(userRepository.findByUsername(registerRequest.getUsername()) == null) {
            userRepository.insert(new User(registerRequest.getUsername(), registerRequest.getPassword()));
            return new RegisterResponse(true);
        }
        return new RegisterResponse(false);
    }
}
