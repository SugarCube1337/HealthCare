package se.ifmo.healthcare.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.auth.JWTUtil;
import se.ifmo.healthcare.services.VacancyService;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping
    public String getAllVacancies(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token != null) {
            String role = jwtUtil.extractAllClaims(token).get("role", String.class);
            model.addAttribute("userRole", role);
        }
        model.addAttribute("vacancies", vacancyService.getAllVacancies());
        return "vacancies";
    }

    @DeleteMapping("/{id}/close")
    @ResponseBody
    public ResponseEntity<String> closeVacancy(@PathVariable Long id, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return ResponseEntity.status(403).body("Access denied");
        }

        String role = jwtUtil.extractAllClaims(token).get("role", String.class);
        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403).body("Access denied");
        }

        vacancyService.closeVacancy(id);
        return ResponseEntity.ok("Vacancy closed successfully");
    }
}

