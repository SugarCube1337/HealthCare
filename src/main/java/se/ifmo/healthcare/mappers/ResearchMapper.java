package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.ResearchDTO;
import se.ifmo.healthcare.dto.WorkWithBiomaterialsDTO;
import se.ifmo.healthcare.models.Research;


import java.time.LocalDate;

public class ResearchMapper {
    public static ResearchDTO toDTO(Research research) {
        ResearchDTO dto = new ResearchDTO();
        dto.setResearchId(research.getResearchId());
        dto.setDate(research.getDate().toString());
        dto.setResearchMethod(research.getResearchMethod());
        dto.setTechnology(research.getTechnology());
        dto.setWorkWithBiomaterial(WorkWithBiomaterialsMapper.toDTO(research.getWorkWithBiomaterial()));
        return dto;
    }

    public static Research toEntity(ResearchDTO dto) {
        Research research = new Research();
        research.setResearchId(dto.getResearchId());
        research.setResearchMethod(dto.getResearchMethod());
        research.setDate(LocalDate.parse(dto.getDate()));
        research.setTechnology(dto.getTechnology());
        research.setWorkWithBiomaterial(WorkWithBiomaterialsMapper.toEntity(dto.getWorkWithBiomaterial()));
        return research;
    }
}
