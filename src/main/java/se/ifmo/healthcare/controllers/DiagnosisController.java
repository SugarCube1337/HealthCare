package se.ifmo.healthcare.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.*;
import se.ifmo.healthcare.services.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping
    public ResponseEntity<String> createDiagnosis(@RequestBody DiagnosisDTO dto) {
        diagnosisService.createDiagnosis(dto);
        return ResponseEntity.ok("Diagnosis created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosisDTO> getDiagnosisById(@PathVariable Long id) {
        DiagnosisDTO dto = diagnosisService.getDiagnosisById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<DiagnosisDTO> getAllDiagnoses() {
        return diagnosisService.getAllDiagnoses();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiagnosis(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
        return ResponseEntity.ok("Diagnosis deleted successfully");
    }
}