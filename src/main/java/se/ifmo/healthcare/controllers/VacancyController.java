package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.VacancyDTO;
import se.ifmo.healthcare.services.VacancyService;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<String> createVacancy(@RequestBody VacancyDTO vacancyDTO) {
        vacancyService.createVacancy(vacancyDTO);
        return ResponseEntity.ok("Vacancy created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyDTO> getVacancyById(@PathVariable Long id) {
        VacancyDTO vacancyDTO = vacancyService.getVacancyById(id);
        if (vacancyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vacancyDTO);
    }

    @GetMapping
    public List<VacancyDTO> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }

    @PutMapping
    public ResponseEntity<String> updateVacancy(@RequestBody VacancyDTO vacancyDTO) {
        vacancyService.updateVacancy(vacancyDTO);
        return ResponseEntity.ok("Vacancy updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.ok("Vacancy deleted successfully");
    }
}
