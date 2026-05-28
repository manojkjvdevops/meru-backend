package com.schoolportal.backend.dto;

import com.schoolportal.backend.model.Attendance;
import com.schoolportal.backend.model.Transport;
import com.schoolportal.backend.model.Nutrition;
import java.util.List;

public class StudentDashboardDto {
    private List<Attendance> attendanceRecords;
    private Transport transportDetails;
    private List<Nutrition> nutritionLogs;

    public StudentDashboardDto(List<Attendance> attendanceRecords, Transport transportDetails, List<Nutrition> nutritionLogs) {
        this.attendanceRecords = attendanceRecords;
        this.transportDetails = transportDetails;
        this.nutritionLogs = nutritionLogs;
    }

    // Getters and Setters
    public List<Attendance> getAttendanceRecords() { return attendanceRecords; }
    public Transport getTransportDetails() { return transportDetails; }
    public List<Nutrition> getNutritionLogs() { return nutritionLogs; }
}