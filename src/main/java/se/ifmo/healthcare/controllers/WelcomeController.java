package se.ifmo.healthcare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }



//    @GetMapping("/register/candidate")
//    public String registerCandidate() {
//        return "register_candidate";
//    }
//
//    @GetMapping("/register/patient")
//    public String registerPatient() {
//        return "register_patient";
//    }
//
//    @GetMapping("/register/doctor")
//    public String registerDoctor() {
//        return "register_doctor";
//    }
}
