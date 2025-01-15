package se.ifmo.healthcare.dto;

import lombok.Data;

@Data

public class VacancyDTO {
    private Long id;
    private String position;
    private String requirements;
    private String openingDate;
    private Integer minSalary;
    private String status;
    private String typeOfEmployment;
}
