package com.anish.flowstate.dto;

import com.anish.flowstate.model.Role;

public class RoleUpdateRequest {
    private Role role;

    public RoleUpdateRequest() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
