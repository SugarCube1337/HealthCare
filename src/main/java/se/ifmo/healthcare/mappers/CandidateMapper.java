package se.ifmo.healthcare.mappers;


import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.dto.CandidateDTO;

import java.time.LocalDate;

public class CandidateMapper {

    public static CandidateDTO toDTO(Candidate candidate) {
        if (candidate == null) return null;
        CandidateDTO dto = new CandidateDTO();
        dto.id = candidate.getId();
        dto.wantPosition = candidate.getWantPosition();
        dto.fillingDate = candidate.getFillingDate().toString();
        dto.gender = candidate.getGender();
        dto.qualification = candidate.getQualification();
        dto.experience = candidate.getExperience();
        return dto;
    }

    public static Candidate toEntity(CandidateDTO dto) {
        if (dto == null) return null;
        Candidate candidate = new Candidate();
        candidate.setId(dto.id);
        candidate.setWantPosition(dto.wantPosition);
        candidate.setFillingDate(LocalDate.parse(dto.fillingDate));
        candidate.setGender(dto.gender);
        candidate.setQualification(dto.qualification);
        candidate.setExperience(dto.experience);
        return candidate;
    }
}
