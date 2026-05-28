package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransportRepository extends JpaRepository<Transport, Long> {
    // Returns an Optional since a student might not use the school bus service
    Optional<Transport> findByStudentEmail(String studentEmail);
}