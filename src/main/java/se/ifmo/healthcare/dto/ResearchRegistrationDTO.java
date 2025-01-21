package se.ifmo.healthcare.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ResearchRegistrationDTO {
    private Long id;
    private ResearchDTO research;
    private Long patientId;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;

}
