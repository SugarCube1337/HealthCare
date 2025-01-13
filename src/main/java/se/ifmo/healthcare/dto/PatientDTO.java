package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private PersonDTO person;
}
