package com.schoolportal.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "certifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // e.g., "Microsoft Certified: Azure Fundamentals"
    private String authority; // e.g., "Microsoft", "Oracle", "Cambridge"
    private String licenseNumber;
    
    @Enumerated(EnumType.STRING)
    private Role targetRole; // Restricts matching metric visibility metrics to ADMIN, TEACHER, HR, STUDENT
}