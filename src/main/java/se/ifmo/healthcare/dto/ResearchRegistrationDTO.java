package se.ifmo.healthcare.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ResearchRegistrationDTO {
    private Long id;
    private Long researchId;
    private Long patientId;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;

}
