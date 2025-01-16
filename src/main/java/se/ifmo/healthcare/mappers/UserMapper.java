package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.PersonDTO;
import se.ifmo.healthcare.dto.UserDTO;
import se.ifmo.healthcare.models.Person;
import se.ifmo.healthcare.models.User;

import java.time.LocalDate;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
}
