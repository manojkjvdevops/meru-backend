package com.schoolportal.backend.controller;

import com.schoolportal.backend.model.User;
import com.schoolportal.backend.model.AiTool;
import com.schoolportal.backend.model.Training;
import com.schoolportal.backend.model.Certification;
import com.schoolportal.backend.repository.UserRepository;
import com.schoolportal.backend.repository.AiToolRepository;
import com.schoolportal.backend.repository.TrainingRepository;
import com.schoolportal.backend.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Imported crypto package
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final UserRepository userRepository;
    private final AiToolRepository aiToolRepository;
    private final TrainingRepository trainingRepository;
    private final CertificationRepository certificationRepository;
    private final PasswordEncoder passwordEncoder; // Added encoder instance field

    @Autowired
    public AdminController(UserRepository userRepository, 
                           AiToolRepository aiToolRepository, 
                           TrainingRepository trainingRepository,
                           CertificationRepository certificationRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.aiToolRepository = aiToolRepository;
        this.trainingRepository = trainingRepository;
        this.certificationRepository = certificationRepository;
        this.passwordEncoder = passwordEncoder; // Injected secure encoder bean
    }

    // Endpoint A: Securely create a user with hashed password credentials
    @PostMapping("/users")
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        try {
            // Enforce email duplication validation safeguards
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("A user profile with this email address already exists.");
            }

            // Standardize fallback passwords if none are sent, then hash cryptographically!
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode("Welcome@MIS2026"));
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to commit user to cluster.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint B: Commit a new AI Tool pipeline configuration
    @PostMapping("/aitools")
    public ResponseEntity<AiTool> deployNewAiTool(@RequestBody AiTool aiTool) {
        try {
            AiTool savedTool = aiToolRepository.save(aiTool);
            return new ResponseEntity<>(savedTool, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint C: Commit a new Professional Training Module record
    @PostMapping("/trainings")
    public ResponseEntity<Training> createNewTraining(@RequestBody Training training) {
        try {
            Training savedTraining = trainingRepository.save(training);
            return new ResponseEntity<>(savedTraining, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint D: Commit a new Professional Certification record
    @PostMapping("/certifications")
    public ResponseEntity<Certification> createNewCertification(@RequestBody Certification certification) {
        try {
            Certification savedCert = certificationRepository.save(certification);
            return new ResponseEntity<>(savedCert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}