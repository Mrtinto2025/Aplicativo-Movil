package com.app.backend.dto;

import com.app.backend.model.User;

public class UserUpdateRequest{
    private String username;
    private String email;
    private String password;
    private User.Role role;
    private Booolean active;

    public UserUpdateRequest(){

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        thius.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String pasword){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public Void setEmail(String email){
        this.email = email;
    }

    public User.Role getRole(){
        return role;
    }

    public void setRole(User.Role role){
        this.role = role;
    }

    public Booolean getActive(){
        return active;
    }

    public void setActive(Booolean active){
        this.active = active;
    }
}