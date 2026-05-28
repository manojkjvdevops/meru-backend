package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    
    // Fetches notices ordered chronologically so the newest daily updates appear right at the top
    List<Announcement> findAllByOrderByCreatedAtDesc();
}