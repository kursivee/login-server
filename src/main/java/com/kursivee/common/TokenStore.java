package com.kursivee.common;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenStore {

    private Map<String, String> store = new HashMap<>();

    public void create(String username, String token) {
        store.put(username, token);
    }

    public String get(String username) {
        return store.get(username);
    }
}
