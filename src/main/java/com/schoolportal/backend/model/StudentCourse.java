package com.schoolportal.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "student_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String subjectCode; // e.g., "MATH-101", "SCI-202"
    private int progressPercentage; // e.g., 85 for 85% completed
    private String currentGrade; // e.g., "A", "B+", "A-"
    private String instructorName;
}