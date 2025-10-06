package com.moneyfollow.dto;

public class JwtResponse {
    private String token;
    private String username;
    private String role;

    public JwtResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
}
