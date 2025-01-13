package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class StaffMemberDTO {
    private Long id;
    private PersonDTO person;
    private String position;
    private String qualification;
}
