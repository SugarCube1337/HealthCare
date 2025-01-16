package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.ifmo.healthcare.services.VacancyService;

@Controller
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/vacancies")
    public String getAllVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getAllVacancies());
        return "vacancies";
    }

}
