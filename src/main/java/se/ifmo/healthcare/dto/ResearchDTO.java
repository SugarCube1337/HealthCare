package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class ResearchDTO {
    private Long researchId;
    private WorkWithBiomaterialsDTO workWithBiomaterial;
    private String researchMethod;
    private String technology;
    private String date;
}