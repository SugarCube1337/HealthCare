package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class ResearchDTO {
    public Long researchId;
    public WorkWithBiomaterialsDTO workWithBiomaterial;
    public String researchMethod;
    public String technology;
    public String date;
}