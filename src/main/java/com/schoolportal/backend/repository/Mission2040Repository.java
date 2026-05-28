package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Mission2040Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Mission2040Repository extends JpaRepository<Mission2040Profile, Long> {
    
    // Looks up the specific child's analytics metrics based on parent/student login email context
    Optional<Mission2040Profile> findByStudentEmail(String studentEmail);
}