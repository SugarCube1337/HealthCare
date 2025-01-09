package se.ifmo.healthcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcomePage() {
        return "welcome"; // Указывает на файл welcome.html
    }
}
