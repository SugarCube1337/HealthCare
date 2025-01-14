package se.ifmo.healthcare.dto;

import lombok.Data;

@Data

public class VacancyDTO {
    public Long id;
    public String position;
    public String requirements;
    public String openingDate;
    public Integer minSalary;
    public String status;
    public String typeOfEmployment;
}
