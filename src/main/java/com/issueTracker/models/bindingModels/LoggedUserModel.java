package com.issueTracker.models.bindingModels;

import com.issueTracker.entities.roles.Role;

public class LoggedUserModel {

    private String username;

    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
