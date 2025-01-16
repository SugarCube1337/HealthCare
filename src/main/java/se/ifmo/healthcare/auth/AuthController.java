package se.ifmo.healthcare.auth;

import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.dto.UserDTO;


import javax.security.auth.login.LoginException;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(AuthenticationRequest authenticationRequest) throws LoginException {
        Long userId = userDAO.findUserByName(authenticationRequest.getUsername()).getId();
        authService.loginUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), userDAO.findById(userId).getRole());
        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());
        System.out.println(userDAO.findById(userId).getRole());
        if (userDAO.findById(userId).getRole().equals("PATIENT")){
            System.out.println("PATIENT");
            return "redirect:/patients/patient_dashboard";
        } else if (userDAO.findById(userId).getRole().equals("STAFF")){
            return "redirect:/staff_dashboard";
        } else{
            return "redirect:/vacancies";
        }

    }


}
