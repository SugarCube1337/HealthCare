package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.dto.ResearchDTO;
import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.services.PatientService;
import se.ifmo.healthcare.services.ResearchRegistrationService;

import java.util.List;

@Controller
@RequestMapping("/research-registrations")
public class ResearchRegistrationController {
    @Autowired
    private ResearchRegistrationService researchRegistrationService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/apply/{id}")
    public String showRegisterPage(Model model, @PathVariable Long id) {
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("researchRegistration", new ResearchRegistrationDTO());
        return "apply_for_research";
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> createResearchRegistration(@ModelAttribute ResearchRegistrationDTO dto, @PathVariable Long id) {
        dto.setPatientId(id);
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
