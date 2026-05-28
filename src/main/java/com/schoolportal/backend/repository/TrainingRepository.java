package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Training;
import com.schoolportal.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    
    // Filter tracks so users only see modules matching their selected role view
    List<Training> findByTargetRole(Role targetRole);
}