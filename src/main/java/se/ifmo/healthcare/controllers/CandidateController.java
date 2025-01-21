package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.CandidateDTO;
import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.services.CandidateService;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/{candidateId}/apply")
    @ResponseBody
    public ResponseEntity<?> applyForVacancy(@PathVariable Long candidateId, @RequestBody Map<String, String> requestBody) {
        String wantPosition = requestBody.get("wantPosition");
        if (wantPosition == null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Missing 'wantPosition'\"}");
        }
        try {
            candidateService.updateWantPosition(candidateId, wantPosition);
            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"Failed to update position\"}");
        }
    }



    @GetMapping("/register_candidate")
    public String registerCandidatePage(Model model) {
        model.addAttribute("candidate", new CandidateDTO());
        return "register_candidate";
    }

    @PostMapping("/register_candidate")
    public String registerCandidate(@ModelAttribute CandidateDTO candidate) {
        candidateService.createCandidate(candidate);
        return "redirect:/vacancies";
    }

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
