package se.ifmo.healthcare.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.services.PatientService;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/register_patient")
    public String showRegisterPage(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "register_patient";
    }

    @PostMapping("/register_patient")
    public String registerPatient(@ModelAttribute PatientDTO patientDTO, HttpSession session) {
        patientService.createPatient(patientDTO);

        // Если нужно, можно установить patientId в сессию:
        session.setAttribute("patientId", patientDTO.getId()); // Предполагается, что пациент получает id после регистрации

        return "redirect:/patients/patient_dashboard";
    }
    @GetMapping("/patient_dashboard")
    public String showPatientDashboard(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if (id == null) {
            return "redirect:/404";
        }
        PatientDTO patientDTO = patientService.getPatientById(id);
        model.addAttribute("patient", patientDTO);
        return "patient_dashboard";
    }

    @PostMapping("/set_patient")
    public String setPatientIdInSession(@RequestParam Long id, HttpSession session) {
        session.setAttribute("id", id);
        return "redirect:/patient_dashboard";
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
