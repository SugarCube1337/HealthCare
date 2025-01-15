package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.MedicalReportDTO;
import se.ifmo.healthcare.services.MedicalReportService;

import java.util.List;

@RestController
@RequestMapping("/medical-reports")
public class MedicalReportController {
    @Autowired
    private MedicalReportService medicalReportService;

    @PostMapping
    public ResponseEntity<String> createMedicalReport(@RequestBody MedicalReportDTO dto) {
        medicalReportService.createMedicalReport(dto);
        return ResponseEntity.ok("MedicalReport created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalReportDTO> getMedicalReportById(@PathVariable Long id) {
        MedicalReportDTO dto = medicalReportService.getMedicalReportById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<MedicalReportDTO> getAllReports() {
        return medicalReportService.getAllReports();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalReport(@PathVariable Long id) {
        medicalReportService.deleteMedicalReport(id);
        return ResponseEntity.ok("MedicalReport deleted successfully");
    }
}
