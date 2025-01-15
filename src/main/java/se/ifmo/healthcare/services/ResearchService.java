package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.ResearchDAO;
import se.ifmo.healthcare.dto.ResearchDTO;
import se.ifmo.healthcare.mappers.ResearchMapper;
import se.ifmo.healthcare.models.Research;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResearchService {
    @Autowired
    private ResearchDAO researchDAO;

    public void createResearch(ResearchDTO dto) {
        Research research = ResearchMapper.toEntity(dto);
        researchDAO.save(research);
    }

    public ResearchDTO getResearchById(Long id) {
        return ResearchMapper.toDTO(researchDAO.findById(id));
    }

    public List<ResearchDTO> getAllResearches() {
        return researchDAO.findAll().stream()
                .map(ResearchMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteResearch(Long id) {
        researchDAO.delete(id);
    }
}
