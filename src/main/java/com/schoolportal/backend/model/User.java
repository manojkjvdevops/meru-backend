package com.schoolportal.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    
    @Column(unique = true, nullable = false)
    private String email; // This will serve as the unique login credential identifier!
    
    private String password; // NEW: Holds the BCrypt cryptographically hashed verification key

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, TEACHER, HR, STUDENT

    private boolean active;
}