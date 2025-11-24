package com.example.r505.User.controller;

import com.example.r505.User.model.Role;

public class UserRequest {
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return Enum.valueOf(Role.class, role);
    }
    public void setRole(String role) {
        this.role = role.toUpperCase();
        // if role is not valid, set to null
        if (Enum.valueOf(Role.class, this.role) == null) {
            this.role = null;
        }
    }
}