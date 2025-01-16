//package se.ifmo.healthcare.controllers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import se.ifmo.healthcare.dto.CandidateDTO;
//import se.ifmo.healthcare.dto.VacancyDTO;
//import se.ifmo.healthcare.services.CandidateService;
//import se.ifmo.healthcare.services.VacancyService;
//
//import java.util.List;
//
//@Controller
////public class WebController {
//
//    @Autowired
//    private CandidateService candidateService;
//
//    @Autowired
//    private VacancyService vacancyService;
//
//    @GetMapping("/")
//    public String homePage() {
//        return "welcome";
//    }
//
//    @GetMapping("/register")
//    public String registerCandidatePage(Model model) {
//        model.addAttribute("candidate", new CandidateDTO());
//        return "candidate-register";
//    }
//
//    @PostMapping("/register")
//    public String registerCandidate(@ModelAttribute CandidateDTO candidate) {
//        candidateService.createCandidate(candidate);
//        return "redirect:/vacancies";
//    }
//
//    @GetMapping("/vacancies")
//    public String listVacancies(Model model) {
//        List<VacancyDTO> vacancies = vacancyService.getAllVacancies();
//        model.addAttribute("vacancies", vacancies);
//        return "vacancies";
//    }
//
//    @GetMapping("/vacancies/{id}")
//    public String vacancyDetails(@PathVariable Long id, Model model) {
//        VacancyDTO vacancy = vacancyService.getVacancyById(id);
//        model.addAttribute("vacancy", vacancy);
//        return "vacancy-details";
//    }
//}
