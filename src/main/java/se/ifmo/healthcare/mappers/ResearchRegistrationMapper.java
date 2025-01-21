package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.models.ResearchRegistration;

public class ResearchRegistrationMapper {

    public static ResearchRegistrationDTO toDTO(ResearchRegistration researchRegistration) {
        ResearchRegistrationDTO dto = new ResearchRegistrationDTO();
        dto.setId(researchRegistration.getId());
        dto.setResearch(ResearchMapper.toDTO(researchRegistration.getResearch()));
        dto.setPatientId(researchRegistration.getPatientId());
        dto.setDate(researchRegistration.getDate());
        dto.setTimeStart(researchRegistration.getTimeStart());
        dto.setTimeEnd(researchRegistration.getTimeEnd());
        return dto;
    }

    public static ResearchRegistration toEntity(ResearchRegistrationDTO dto) {
        ResearchRegistration researchRegistration = new ResearchRegistration();
        researchRegistration.setId(dto.getId());
        researchRegistration.setResearch(ResearchMapper.toEntity(dto.getResearch()));
        researchRegistration.setPatientId(dto.getPatientId());
        researchRegistration.setDate(dto.getDate());
        researchRegistration.setTimeStart(dto.getTimeStart());
        researchRegistration.setTimeEnd(dto.getTimeEnd());
        return researchRegistration;
    }
}
