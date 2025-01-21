package se.ifmo.healthcare.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.auth.JWTUtil;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.dto.VacancyDTO;
import se.ifmo.healthcare.services.VacancyService;
import se.ifmo.healthcare.services.CandidateService;
import se.ifmo.healthcare.services.StaffMemberService;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private StaffMemberService staffMemberService;


    @Autowired
    private JWTUtil jwtUtil;

    // Страница регистрации вакансий
    @GetMapping("/register_vacancy")
    public String showRegisterVacancyPage(Model model) {
        model.addAttribute("vacancy", new VacancyDTO());
        return "register_vacancy";
    }

    // Регистрация новой вакансии
    @PostMapping("/register_vacancy")
    public String registerVacancy(@ModelAttribute VacancyDTO vacancyDTO) {
        vacancyService.createVacancy(vacancyDTO);
        return "redirect:/vacancies";
    }


    @GetMapping
    public String getAllVacancies(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/auth/login";
        }
        Claims claims = jwtUtil.extractAllClaims(token);
        Long personId = claims.get("id", Long.class);

        StaffMemberDTO staffMember = staffMemberService.getStaffMemberByPersonId(personId);

        if (staffMember == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("staffMember", staffMember);
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

