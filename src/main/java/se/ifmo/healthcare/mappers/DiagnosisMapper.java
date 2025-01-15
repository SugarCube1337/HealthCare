package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.*;
import se.ifmo.healthcare.dto.*;

public class DiagnosisMapper {
    public static DiagnosisDTO toDTO(Diagnosis diagnosis) {
        DiagnosisDTO dto = new DiagnosisDTO();
        dto.setDiagnosisId(diagnosis.getDiagnosisId());
        dto.setDiagnosisName(diagnosis.getDiagnosisName());
        dto.setIcdCode(diagnosis.getIcdCode());
        return dto;
    }

    public static Diagnosis toEntity(DiagnosisDTO dto) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisId(dto.getDiagnosisId());
        diagnosis.setDiagnosisName(dto.getDiagnosisName());
        diagnosis.setIcdCode(dto.getIcdCode());
        return diagnosis;
    }
}