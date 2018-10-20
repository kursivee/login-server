package com.kursivee.controller;

public class LoginResponse {
    private String sessionToken;

    public LoginResponse(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
