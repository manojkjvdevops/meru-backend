package com.schoolportal.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // 1. Intercept the incoming Authorization header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        // 2. Extract the bearer token value strings cleanly
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            if (jwtUtils.validateToken(token)) {
                email = jwtUtils.getEmailFromToken(token);
            }
        }

        // 3. If a valid user email is found, establish active Spring Security context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // For now, we will dynamically inject the generic authenticated credentials block
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    email, null, List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            // Lock the validation token straight into the global execution context thread!
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // 4. Pass control off down the standard servlet execution loop filters
        filterChain.doFilter(request, response);
    }
}