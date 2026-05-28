package com.schoolportal.backend.controller;

import com.schoolportal.backend.dto.LoginRequest;
import com.schoolportal.backend.dto.AuthResponse;
import com.schoolportal.backend.model.User;
import com.schoolportal.backend.repository.UserRepository;
import com.schoolportal.backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserRepository userRepository, 
                          PasswordEncoder passwordEncoder, 
                          JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 1. Check if the user exists in the database
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email credentials provided.");
        }

        User user = userOptional.get();

        // 2. Compute cryptographic verification checks against the hashed password field
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password verification match.");
        }

        // 3. Generate a signed secure JWT token string
        String generatedToken = jwtUtils.generateToken(user.getEmail(), user.getRole().name());

        // 4. Return the credential payload packet back to the client
        return ResponseEntity.ok(new AuthResponse(
                generatedToken, 
                user.getEmail(), 
                user.getRole().name()
        ));
    }

    // Secure Registration Endpoint to seed hashed database users
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // 1. Enforce safety checks to ensure emails aren't duplicated inside the cluster
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email profile address is already registered inside this system.");
        }

        // 2. CRYPTOGRAPHIC ENCRYPTION: intercept the raw string and hash it using BCrypt
        String plainTextPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(plainTextPassword));

        // 3. Save the newly encrypted entity back to the PostgreSQL database
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}