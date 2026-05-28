package com.schoolportal.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ai_tools")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(length = 500)
    private String description;
    
    private String status; // e.g., 'Active' or 'Maintenance'
    private String iconType; // e.g., 'sparkles', 'brain', 'wrench'
}