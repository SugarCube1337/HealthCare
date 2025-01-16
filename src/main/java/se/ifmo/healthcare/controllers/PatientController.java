package se.ifmo.healthcare.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.auth.JWTUtil;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.services.PatientService;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/register_patient")
    public String showRegisterPage(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "register_patient";
    }

    @PostMapping("/register_patient")
    public String registerPatient(@ModelAttribute PatientDTO patientDTO) {
        patientService.createPatient(patientDTO);
        return "patient_dashboard";
    }
    @GetMapping("/patient_dashboard")
    public String showPatientDashboard(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        System.out.println(token);
        if (token == null) {
            return "redirect:/auth/login";
        }

        Claims claims = jwtUtil.extractAllClaims(token);
        System.out.println(claims);
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);
        Long id = claims.get("id", Long.class);

        System.out.println(id);

        if (!"PATIENT".equals(role)) {
            return "redirect:/auth/login";
        }

        PatientDTO patientDTO = patientService.getPatientById(id);
        if (patientDTO == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("patient", patientDTO);


//        model.addAttribute("patient", new PatientDTO());
        return "patient_dashboard";
    }

    @PostMapping
    public ResponseEntity<String> createPatient(@RequestBody PatientDTO patientDTO) {
        patientService.createPatient(patientDTO);
        return ResponseEntity.ok("Patient created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        if (patientDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PutMapping
    public ResponseEntity<String> updatePatient(@RequestBody PatientDTO patientDTO) {
        patientService.updatePatient(patientDTO);
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}
