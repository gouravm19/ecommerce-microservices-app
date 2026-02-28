package com.gourav.ecommerce.security;

import com.gourav.ecommerce.model.User;
import com.gourav.ecommerce.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService; private final UserRepository userRepository;
    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository){this.jwtService=jwtService;this.userRepository=userRepository;}
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            try { Claims claims = jwtService.parse(header.substring(7)); User user = userRepository.findByEmail(claims.getSubject()).orElse(null); if (user != null) { var auth = new UsernamePasswordAuthenticationToken(user.getEmail(), null,List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))); SecurityContextHolder.getContext().setAuthentication(auth);} } catch (Exception ignored) {}
        }
        filterChain.doFilter(request, response);
    }
}
