package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class CandidateDTO {
    public Long id;
    private PersonDTO person;

    public String wantPosition;
    public String fillingDate;
    public String gender;
    public String qualification;
    public Integer experience;
}
