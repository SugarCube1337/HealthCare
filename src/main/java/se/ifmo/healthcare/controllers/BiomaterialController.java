package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.BiomaterialDTO;
import se.ifmo.healthcare.dto.DiagnosisDTO;
import se.ifmo.healthcare.services.BiomaterialService;
import se.ifmo.healthcare.services.DiagnosisService;

import java.util.List;

@RestController
@RequestMapping("/api/biomaterials")
public class BiomaterialController {
    @Autowired
    private BiomaterialService biomaterialService;

    @PostMapping
    public ResponseEntity<String> createBiomaterial(@RequestBody BiomaterialDTO dto) {
        biomaterialService.createBiomaterial(dto);
        return ResponseEntity.ok("Biomaterial created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BiomaterialDTO> getBiomaterialById(@PathVariable Long id) {
        BiomaterialDTO dto = biomaterialService.getBiomaterialById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<BiomaterialDTO> getAllDiagnoses() {
        return biomaterialService.getAllDiagnoses();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiomaterial(@PathVariable Long id) {
        biomaterialService.deleteBiomaterial(id);
        return ResponseEntity.ok("Biomaterial deleted successfully");
    }
}
