package se.ifmo.healthcare.dto;

import jakarta.jws.soap.SOAPBinding;
import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private PersonDTO person;
    private UserDTO user;
}
