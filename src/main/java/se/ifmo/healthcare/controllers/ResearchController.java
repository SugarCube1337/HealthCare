package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.MedicalReportDTO;
import se.ifmo.healthcare.dto.ResearchDTO;
import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.services.MedicalReportService;
import se.ifmo.healthcare.services.ResearchService;

import java.util.List;

@Controller
@RequestMapping("/researches")
public class ResearchController {
    @Autowired
    private ResearchService researchService;

    @PostMapping
    public ResponseEntity<String> createResearch(@RequestBody ResearchDTO dto) {
        researchService.createResearch(dto);
        return ResponseEntity.ok("Research created successfully");
    }
    @GetMapping("/all")
    public String showPage(Model model){
        List<ResearchDTO> researchDTOS = researchService.getAllResearches();
        model.addAttribute("researches", researchDTOS);
        return "researches";
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResearchDTO> getResearchById(@PathVariable Long id) {
        ResearchDTO dto = researchService.getResearchById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<ResearchDTO> getAllResearches() {
        return researchService.getAllResearches();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResearch(@PathVariable Long id) {
        researchService.deleteResearch(id);
        return ResponseEntity.ok("Research deleted successfully");
    }
}
