package com.schoolportal.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardMetricsDto {
    private int totalTeachers;
    private int activeTeachers;
    private int trainingsCompleted;
    private int certificationsEarned;
    
    // Progress chart statistics from image 1
    private int completionRate;
    private int inProgressRate;
    private int notStartedRate;
}