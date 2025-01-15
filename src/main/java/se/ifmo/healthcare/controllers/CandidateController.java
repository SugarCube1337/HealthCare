package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.CandidateDTO;
import se.ifmo.healthcare.services.CandidateService;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity<String> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        candidateService.createCandidate(candidateDTO);
        return ResponseEntity.ok("Candidate created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Long id) {
        CandidateDTO candidateDTO = candidateService.getCandidateById(id);
        if (candidateDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(candidateDTO);
    }

    @GetMapping
    public List<CandidateDTO> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @PutMapping
    public ResponseEntity<String> updateCandidate(@RequestBody CandidateDTO candidateDTO) {
        candidateService.updateCandidate(candidateDTO);
        return ResponseEntity.ok("Candidate updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok("Candidate deleted successfully");
    }
}
