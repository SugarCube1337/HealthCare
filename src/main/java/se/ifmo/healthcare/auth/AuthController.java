package se.ifmo.healthcare.auth;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.PostMapping;
import se.ifmo.healthcare.dao.UserDAO;

import javax.security.auth.login.LoginException;


@RequestScoped
@Path("/auth")
public class AuthController {

    @Inject
    private AuthService authService;


    @Inject
    private UserDAO userDAO;


    @PostMapping
    @Path("/login")
    public Response loginUser(AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse response = authService.loginUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), userDAO.findByUsername(authenticationRequest.getUsername()).get().getRole());
            return Response.ok(response).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @PostMapping
    public String login(){
        return "redirect:/login";
    }



}
