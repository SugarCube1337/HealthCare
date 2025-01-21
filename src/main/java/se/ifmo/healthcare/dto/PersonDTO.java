package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private String contactInfo;
    private String birthDate;
    private String role;

}
