package com.schoolportal.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "trainings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(length = 500)
    private String description;
    
    private String duration; // e.g., "4 hours", "2 weeks"
    private String progressStatus; // e.g., "Completed", "In Progress", "Not Started"

    @Enumerated(EnumType.STRING)
    private Role targetRole; // Restricts visibility to specific roles (TEACHER, HR, TRAINER)
}