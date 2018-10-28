package com.kursivee.resource.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/guest/hello")
    public String helloGuest() {
        return "Hello Guest";
    }
}
