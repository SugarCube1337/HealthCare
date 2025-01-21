package se.ifmo.healthcare.mappers;


import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.dto.CandidateDTO;

public class CandidateMapper {

    public static CandidateDTO toDTO(Candidate candidate) {
        if (candidate == null) return null;
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setPerson(PersonMapper.toDTO(candidate.getPerson()));
        dto.setWantPosition(candidate.getWantPosition());
        dto.setFillingDate(candidate.getFillingDate());
        dto.setQualification(candidate.getQualification());
        dto.setExperience(candidate.getExperience());
        return dto;
    }

    public static Candidate toEntity(CandidateDTO dto) {
        if (dto == null) return null;
        Candidate candidate = new Candidate();
        candidate.setId(dto.getId());
        candidate.setPerson(PersonMapper.toEntity(dto.getPerson()));
        candidate.setWantPosition(dto.getWantPosition());
        candidate.setQualification(dto.getQualification());
        candidate.setFillingDate(dto.getFillingDate());
        candidate.setExperience(dto.getExperience());
        return candidate;
    }
}
