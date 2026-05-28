package com.schoolportal.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "mission_2040_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission2040Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentEmail;

    // AI Academic Analytics
    @Column(length = 500)
    private String aiStrengthsSummary;
    @Column(length = 500)
    private String aiActionableFocus;

    // Core Metrics Tracking from the Prototype Blueprint (Updated to Integer Objects)
    private Integer learningHoursThisWeek;
    private Integer pendingAssignmentsCount;
    private Integer badgesEarnedCount;
    private String currentGradeSection;

    // Subject Performance Tracking Indices (Updated to Integer Objects)
    private Integer scienceProgress;
    private Integer mathProgress;
    private Integer englishProgress;
    private Integer computerProgress;

    // Next-Gen 2040 Capabilities (Updated to Integer Objects)
    private Integer typingSpeedWpm;
    private Integer publicSpeakingScore;
    private Integer creativeWritingScore;

    // Virtual Labs Experiment Tracks
    private String volcanoLabStatus;  
    private String circuitLabStatus;
    private String cellLabStatus;

    @Column(length = 500)
    private String teacherRemarks;
}