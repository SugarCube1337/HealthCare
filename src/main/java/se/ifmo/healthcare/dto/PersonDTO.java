package se.ifmo.healthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDTO {
    public Long id;
    public String name;
    public String surname;
    public String gender;
    public String contactInfo;

}
