package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.WorkWithBiomaterialsDTO;
import se.ifmo.healthcare.services.WorkWithBiomaterialsService;

import java.util.List;

@RestController
@RequestMapping("/api/works-with-biomaterials")
public class WorkWithBiomaterialsController {
    @Autowired
    private WorkWithBiomaterialsService workWithBiomaterialsService;

    @PostMapping
    public ResponseEntity<String> createWorkWithBiomaterials(@RequestBody WorkWithBiomaterialsDTO dto) {
        workWithBiomaterialsService.createWorkWithBiomaterials(dto);
        return ResponseEntity.ok("WorkWithBiomaterials created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkWithBiomaterialsDTO> getWorkWithBiomaterialsById(@PathVariable Long id) {
        WorkWithBiomaterialsDTO dto = workWithBiomaterialsService.getWorkWithBiomaterialsById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<WorkWithBiomaterialsDTO> getAllWorkWithBiomaterials() {
        return workWithBiomaterialsService.getAllWorkWithBiomaterials();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkWithBiomaterials(@PathVariable Long id) {
        workWithBiomaterialsService.deleteWorkWithBiomaterials(id);
        return ResponseEntity.ok("WorkWithBiomaterials deleted successfully");
    }
}
