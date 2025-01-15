package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.*;
import se.ifmo.healthcare.dto.*;

public class DiagnosisMapper {
    public static DiagnosisDTO toDTO(Diagnosis diagnosis) {
        DiagnosisDTO dto = new DiagnosisDTO();
        dto.diagnosisId = diagnosis.getDiagnosisId();
        dto.diagnosisName = diagnosis.getDiagnosisName();
        dto.icdCode = diagnosis.getIcdCode();
        return dto;
    }

    public static Diagnosis toEntity(DiagnosisDTO dto) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisId(dto.diagnosisId);
        diagnosis.setDiagnosisName(dto.diagnosisName);
        diagnosis.setIcdCode(dto.icdCode);
        return diagnosis;
    }
}