package com.ifsp.scrumProjectLDSD5.enumeration;

public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}