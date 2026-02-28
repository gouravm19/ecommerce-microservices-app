package com.gourav.ecommerce.service;

import com.gourav.ecommerce.dto.AuthDtos;
import com.gourav.ecommerce.model.Role;
import com.gourav.ecommerce.model.User;
import com.gourav.ecommerce.repository.UserRepository;
import com.gourav.ecommerce.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository; private final PasswordEncoder passwordEncoder; private final AuthenticationManager authenticationManager; private final JwtService jwtService;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService){this.userRepository=userRepository;this.passwordEncoder=passwordEncoder;this.authenticationManager=authenticationManager;this.jwtService=jwtService;}
    public AuthDtos.AuthResponse register(AuthDtos.RegisterRequest request) {
        Role role = request.role() == null ? Role.CUSTOMER : request.role();
        User user = new User(null, request.email(), passwordEncoder.encode(request.password()), request.fullName(), role);
        userRepository.save(user);
        return new AuthDtos.AuthResponse(jwtService.generateToken(user), user.getEmail(), user.getFullName(), user.getRole());
    }
    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        return new AuthDtos.AuthResponse(jwtService.generateToken(user), user.getEmail(), user.getFullName(), user.getRole());
    }
    public User me(String email) { return userRepository.findByEmail(email).orElseThrow(); }
}
