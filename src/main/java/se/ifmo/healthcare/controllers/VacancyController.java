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
import se.ifmo.healthcare.models.Vacancy;
import se.ifmo.healthcare.services.VacancyService;
import se.ifmo.healthcare.services.CandidateService;
import se.ifmo.healthcare.services.StaffMemberService;
import se.ifmo.healthcare.dto.CandidateDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{vacancyId}/candidates")
    public String getCandidatesForVacancy(@PathVariable Long vacancyId, Model model) {
        Vacancy vacancy = vacancyService.findById(vacancyId);
        List<CandidateDTO> candidates = candidateService.getCandidatesForVacancy(vacancyId)
                .stream()
                .filter(candidate -> candidate.getWantPosition().equals(vacancy.getPosition()))
                .collect(Collectors.toList());
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("candidates", candidates);
        return "candidates_for_vacancy";
    }


    @PostMapping("/{vacancyId}/candidates/{candidateId}/approve")
    public ResponseEntity<String> approveCandidate(@PathVariable Long vacancyId, @PathVariable Long candidateId) {
        candidateService.approveCandidate(candidateId, vacancyId);
        return ResponseEntity.ok("Candidate approved");
    }

    @PostMapping("/{vacancyId}/candidates/{candidateId}/reject")
    public ResponseEntity<String> rejectCandidate(@PathVariable Long vacancyId, @PathVariable Long candidateId) {
        candidateService.rejectCandidate(candidateId, vacancyId);
        return ResponseEntity.ok("Candidate rejected");
    }

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

        // Получаем staffMember или candidate по personId
        StaffMemberDTO staffMember = staffMemberService.getStaffMemberByPersonId(personId);
        CandidateDTO candidate = null;

        if (staffMember != null) {
            model.addAttribute("staffMember", staffMember);
        } else {
            candidate = candidateService.getCandidateByPersonId(personId);
            if (candidate == null) {
                return "redirect:/auth/login";  // Если кандидат не найден, редирект на логин
            }
            model.addAttribute("candidate", candidate);
        }

        // Получаем все вакансии
        List<VacancyDTO> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies", vacancies);

        // Логирование для отладки
        System.out.println("User type: " + (staffMember != null ? "StaffMember" : "Candidate"));
        System.out.println("Vacancies retrieved: " + vacancies.size());

        return "vacancies";
    }






    @DeleteMapping("/{id}/close")
    @ResponseBody
    public ResponseEntity<String> closeVacancy(@PathVariable Long id, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return ResponseEntity.status(403).body("Access denied");
        }

        vacancyService.closeVacancy(id);
        return ResponseEntity.ok("Vacancy closed successfully");
    }

}

