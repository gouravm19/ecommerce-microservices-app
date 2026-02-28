package com.gourav.ecommerce.controller;

import com.gourav.ecommerce.dto.AuthDtos;
import com.gourav.ecommerce.model.User;
import com.gourav.ecommerce.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){this.authService=authService;}
    @PostMapping("/register") public AuthDtos.AuthResponse register(@RequestBody AuthDtos.RegisterRequest request){ return authService.register(request); }
    @PostMapping("/login") public AuthDtos.AuthResponse login(@RequestBody AuthDtos.LoginRequest request){ return authService.login(request); }
    @GetMapping("/profile") public User profile(Authentication authentication){ return authService.me(authentication.getName()); }
}
