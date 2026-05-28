package com.schoolportal.backend.controller;

import com.schoolportal.backend.model.AiTool;
import com.schoolportal.backend.repository.AiToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aitools")
@CrossOrigin(origins = "http://localhost:5173")
public class AiToolController {

    private final AiToolRepository aiToolRepository;

    @Autowired
    public AiToolController(AiToolRepository aiToolRepository) {
        this.aiToolRepository = aiToolRepository;
    }

    @GetMapping
    public ResponseEntity<List<AiTool>> getAllTools() {
        List<AiTool> dbTools = aiToolRepository.findAll();
        
        // Smart fallback: If the database table is fresh and empty, seed our baseline items automatically
        if (dbTools.isEmpty()) {
            return ResponseEntity.ok(List.of(
                new AiTool(1L, "ChatGPT Edu Suite", "Enterprise academic tier for automated resource mapping and structured lesson planning.", "Active", "sparkles"),
                new AiTool(2L, "Quizizz AI Core", "Generative test bank processing framework utilizing continuous student assessment metadata.", "Active", "brain"),
                new AiTool(3L, "Canva Design Agent", "Media generation engine supporting unified branding blueprints across portals.", "Maintenance", "wrench")
            ));
        }
        
        return ResponseEntity.ok(dbTools);
    }
}