package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.AiTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiToolRepository extends JpaRepository<AiTool, Long> {
    // inherits standard findAll() list retrieval queries out-of-the-box!
}