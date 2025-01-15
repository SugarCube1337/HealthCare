package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class DiagnosisDTO {
    public Long diagnosisId;
    public String diagnosisName;
    public String icdCode;
}
