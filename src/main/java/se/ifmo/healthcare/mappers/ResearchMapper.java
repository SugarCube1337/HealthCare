package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.ResearchDTO;
import se.ifmo.healthcare.dto.WorkWithBiomaterialsDTO;
import se.ifmo.healthcare.models.Research;


import java.time.LocalDate;

public class ResearchMapper {
    public static ResearchDTO toDTO(Research research) {
        ResearchDTO dto = new ResearchDTO();
        dto.researchId = research.getResearchId();
        dto.date = research.getDate().toString();
        dto.researchMethod = research.getResearchMethod();
        dto.technology = research.getTechnology();
        dto.workWithBiomaterial = WorkWithBiomaterialsMapper.toDTO(research.getWorkWithBiomaterial());
        return dto;
    }

    public static Research toEntity(ResearchDTO dto) {
        Research research = new Research();
        research.setResearchId(dto.researchId);
        research.setResearchMethod(dto.researchMethod);
        research.setDate(LocalDate.parse(dto.date));
        research.setTechnology(dto.technology);
        research.setWorkWithBiomaterial(WorkWithBiomaterialsMapper.toEntity(dto.workWithBiomaterial));
        return research;
    }
}
