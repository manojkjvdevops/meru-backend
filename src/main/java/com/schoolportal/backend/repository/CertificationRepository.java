package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Certification;
import com.schoolportal.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    
    // Dynamic query signature to aggregate rows filtered by selected view role
    long countByTargetRole(Role targetRole);
}