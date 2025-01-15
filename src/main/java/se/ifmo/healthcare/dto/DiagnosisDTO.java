package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class DiagnosisDTO {
    private Long diagnosisId;
    private String diagnosisName;
    private String icdCode;
}
