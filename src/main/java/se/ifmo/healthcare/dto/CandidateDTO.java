package se.ifmo.healthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateDTO {
    private Long id;
    private PersonDTO person;
    private String wantPosition;
    private LocalDate fillingDate;
    private String qualification;
    private Integer experience;
}
