package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.services.ResearchRegistrationService;

import java.util.List;

@RestController
@RequestMapping("/research-registrations")
public class ResearchRegistrationController {
    @Autowired
    private ResearchRegistrationService researchRegistrationService;

    @PostMapping
    public ResponseEntity<String> createResearchRegistration(@RequestBody ResearchRegistrationDTO dto) {
        researchRegistrationService.createResearchRegistration(dto);
        return ResponseEntity.ok("ResearchRegistration created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchRegistrationDTO> getResearchRegistrationById(@PathVariable Long id) {
        ResearchRegistrationDTO dto = researchRegistrationService.getResearchRegistrationById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<ResearchRegistrationDTO> getAllResearchRegistrations() {
        return researchRegistrationService.getAllResearchRegistrations();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResearchRegistration(@PathVariable Long id) {
        researchRegistrationService.deleteResearchRegistration(id);
        return ResponseEntity.ok("ResearchRegistration deleted successfully");
    }

}
