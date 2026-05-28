package com.schoolportal.backend.controller;

import com.schoolportal.backend.model.Announcement;
import com.schoolportal.backend.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcements")
@CrossOrigin(origins = "http://localhost:5173")
public class AnnouncementController {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    // Endpoint 1: Fetches the chronological live message stream for Student/Parent views
    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> notices = announcementRepository.findAllByOrderByCreatedAtDesc();
        
        // Smart Presentation Fallback Seed: Ensures Bharathi's board view always has crisp, realistic initial notices!
        if (notices.isEmpty()) {
            Announcement seed1 = new Announcement(null, "Mathematics Focus: Calculus Limits", "Please complete questions 1-5 on the adaptive learning module sandbox tonight. Bring any logic edge-case queries to tomorrow's lab.", "STUDENT", "Mrs. Bharathi", null);
            Announcement seed2 = new Announcement(null, "Robotics Prototype Submissions", "All teams must upload their Mars Rover sensory arrays mapping data to the hub repository by Friday afternoon.", "STUDENT", "Mrs. Bharathi", null);
            announcementRepository.save(seed1);
            announcementRepository.save(seed2);
            notices = announcementRepository.findAllByOrderByCreatedAtDesc();
        }
        
        return ResponseEntity.ok(notices);
    }

    // Endpoint 2: Allows Teachers to post brand new daily updates straight into the cluster live!
    @PostMapping
    public ResponseEntity<Announcement> postAnnouncement(@RequestBody Announcement announcement) {
        try {
            if (announcement.getAuthorName() == null || announcement.getAuthorName().isEmpty()) {
                announcement.setAuthorName("Mrs. Bharathi");
            }
            announcement.setTargetRole("STUDENT");
            Announcement savedNotice = announcementRepository.save(announcement);
            return new ResponseEntity<>(savedNotice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}