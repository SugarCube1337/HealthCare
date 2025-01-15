package se.ifmo.healthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateDTO {
    public Long id;
    private PersonDTO person;
    public String wantPosition;
    public LocalDate fillingDate;
    public String qualification;
    public Integer experience;
}
