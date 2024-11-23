package com.hms.anikdv.code.payloads;

public class JwtAuthResponse {
    private String token;
    private UserPayload currentUser;

    public JwtAuthResponse() {
    }

    public JwtAuthResponse(String token, UserPayload currentUser) {
        this.token = token;
        this.currentUser = currentUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserPayload getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserPayload currentUser) {
        this.currentUser = currentUser;
    }
}