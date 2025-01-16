package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.dto.CandidateDTO;
import se.ifmo.healthcare.dto.UserDTO;
import se.ifmo.healthcare.mappers.CandidateMapper;
import se.ifmo.healthcare.mappers.UserMapper;
import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.models.User;


import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        userDAO.save(user);
    }

    public User getUser(Integer id) {
        return userDAO.findById(id);
    }

    public User getUserByName(String name){
        return userDAO.findUserByName(name);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(Integer id) {
        userDAO.delete(id);
    }


    private boolean isPasswordUnique(String password) {
        return userDAO.findAll().stream()
                .noneMatch(user -> user.getPassword().equals(password));
    }



}