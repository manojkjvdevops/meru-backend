package com.schoolportal.backend.controller;

import com.schoolportal.backend.dto.StudentDashboardDto;
import com.schoolportal.backend.model.*;
import com.schoolportal.backend.repository.*; // 👈 THIS IS THE CRUCIAL LINE COVERING YOUR REPOSITORIES

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private NutritionRepository nutritionRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<StudentDashboardDto> getStudentDashboard(@RequestParam String email) {
        List<Attendance> attendance = attendanceRepository.findByStudentEmail(email);
        Transport transport = transportRepository.findByStudentEmail(email).orElse(null);
        List<Nutrition> nutrition = nutritionRepository.findByStudentEmail(email);

        return ResponseEntity.ok(new StudentDashboardDto(attendance, transport, nutrition));
    }
}