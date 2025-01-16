package se.ifmo.healthcare.auth;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.dto.UserDTO;

import javax.security.auth.login.LoginException;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new UserDTO());
        return "login";
    }


    @PostMapping("/login")
    public Response loginUser(AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse response = authService.loginUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), userDAO.findByUsername(authenticationRequest.getUsername()).get().getRole());
            return Response.ok(response).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }





}
