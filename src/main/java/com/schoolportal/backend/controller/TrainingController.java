package com.schoolportal.backend.controller;

import com.schoolportal.backend.model.Training;
import com.schoolportal.backend.model.Role;
import com.schoolportal.backend.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainings")
@CrossOrigin(origins = "http://localhost:5173")
public class TrainingController {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping
    public ResponseEntity<List<Training>> getTrainingsByRole(@RequestParam Role role) {
        List<Training> dbTrainings = trainingRepository.findByTargetRole(role);
        
        // Smart fallback: If database is fresh, supply baseline tracking cards automatically
        if (dbTrainings.isEmpty()) {
            if (role == Role.TEACHER) {
                return ResponseEntity.ok(List.of(
                    new Training(1L, "AI-Assisted Lesson Design", "Mastering prompt engineering pipelines using ChatGPT Edu to structure course maps.", "3 hours", "Completed", Role.TEACHER),
                    new Training(2L, "Interactive Formative Assessment", "Utilizing Quizizz AI metadata structures to optimize student feedback cycles.", "5 hours", "In Progress", Role.TEACHER)
                ));
            } else {
                return ResponseEntity.ok(List.of(
                    new Training(3L, "Enterprise Compliance Training", "Standard onboarding safety, information data security, and workspace compliance.", "2 hours", "Completed", role)
                ));
            }
        }
        
        return ResponseEntity.ok(dbTrainings);
    }
}