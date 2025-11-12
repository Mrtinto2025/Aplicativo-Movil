package com.app.backend.dto;

import com.app.backend.model.User;

public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String usernamer;
    private String email;
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(String token, User user) {
        this.token = token;
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getRole();
        this.role = user.getRole();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    puclic void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole(){
        return role;
    }

    puclic void setRole(String role) {
        this.role = role;
    }   
}