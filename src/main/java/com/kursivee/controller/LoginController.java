package com.kursivee.controller;

import com.kursivee.controller.order.CustomException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/test")
    public String test() {
        return "Hello";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws CustomException {
        System.out.println(loginRequest);

        if(loginRequest.getUsername().equals("user") && loginRequest.getPassword().equals("password")) {
            return new LoginResponse("aoiweaAJWIOEFJLAJ234RFo9230ijweoif");
        }
        throw new CustomException();
    }
}
