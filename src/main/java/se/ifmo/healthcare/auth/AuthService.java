package se.ifmo.healthcare.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.models.User;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
@ApplicationScoped
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTUtil jwtUtil;


    private static final int MIN_PASSWORD_LENGTH = 8;

    public boolean authenticate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }

    @Transactional
    public boolean registerUser(@NotNull String username, @NotNull String password, @NotNull String role) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }

        Optional<User> existingUser = userDAO.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password));
        user.setRole(role);
        userDAO.save(user);
        return true;
    }


    public AuthenticationResponse loginUser(String username, String password, String role, Long userId, HttpSession session) throws LoginException {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new LoginException("Invalid credentials"));

        if (!user.getPassword().equals(password)) {
            throw new LoginException("Invalid credentials");
        }
        System.out.println(userId);
        String token = jwtUtil.generateToken(username, role, userId);
        session.setAttribute("jwtToken", token);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private String hashPassword(String password) {
        return org.apache.commons.codec.digest.DigestUtils.sha512Hex(password);
    }

    public String getRoleFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            String role = decodedJWT.getClaim("role").asString();

            if (role == null) {
                throw new NotAuthorizedException("Role not found in token");
            }

            return role;
        } catch (JWTDecodeException e) {
            throw new NotAuthorizedException("Invalid token format");
        }
    }

}
