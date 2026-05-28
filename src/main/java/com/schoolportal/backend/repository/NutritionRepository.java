package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    // Fetches all meal history logs for a specific student profile
    List<Nutrition> findByStudentEmail(String studentEmail);
}