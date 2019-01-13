package com.kursivee.register.model;

public class RegisterResponse {
    private boolean success;

    public RegisterResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
