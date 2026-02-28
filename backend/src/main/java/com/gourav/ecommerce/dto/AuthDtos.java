package com.gourav.ecommerce.dto;

import com.gourav.ecommerce.model.Role;

public class AuthDtos {
    public record RegisterRequest(String email, String password, String fullName, Role role) {}
    public record LoginRequest(String email, String password) {}
    public record AuthResponse(String token, String email, String fullName, Role role) {}
}
