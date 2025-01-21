package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String createResearchRegistration(@ModelAttribute ResearchRegistrationDTO dto, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        dto.setPatientId(id);
        researchRegistrationService.createResearchRegistration(dto);
        try {
            redirectAttributes.addFlashAttribute("successMessage", "Research registration created successfully!");
            return "redirect:/staff-members/staff_dashboard";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/research-registrations/apply/{id}";
        }
        //return ResponseEntity.ok("ResearchRegistration created successfully");
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
