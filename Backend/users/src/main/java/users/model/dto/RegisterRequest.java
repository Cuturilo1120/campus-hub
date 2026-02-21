package users.model.dto;

import users.model.entity.Role;

public record RegisterRequest(String firstName, String lastName, String username, String password, Role role) {}
