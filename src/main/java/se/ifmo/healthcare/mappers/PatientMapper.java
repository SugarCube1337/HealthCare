package se.ifmo.healthcare.mappers;


import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.models.Patient;

public class PatientMapper {
    public static PatientDTO toDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setPerson(PersonMapper.toDTO(patient.getPerson()));
        return dto;
    }

    public static Patient toEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setPerson(PersonMapper.toEntity(dto.getPerson()));
        return patient;
    }
}
