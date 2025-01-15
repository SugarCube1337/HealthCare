package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.ResearchRegistrationDAO;
import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.mappers.ResearchRegistrationMapper;
import se.ifmo.healthcare.models.ResearchRegistration;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResearchRegistrationService {
    @Autowired
    private ResearchRegistrationDAO researchRegistrationDAO;

    public void createResearchRegistration(ResearchRegistrationDTO dto) {
        ResearchRegistration researchRegistration = ResearchRegistrationMapper.toEntity(dto);
        researchRegistrationDAO.save(researchRegistration);
    }

    public ResearchRegistrationDTO getResearchRegistrationById(Long id) {
        return ResearchRegistrationMapper.toDTO(researchRegistrationDAO.findById(id));
    }

    public List<ResearchRegistrationDTO> getAllResearchRegistrations() {
        return researchRegistrationDAO.findAll().stream()
                .map(ResearchRegistrationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteResearchRegistration(Long id) {
        researchRegistrationDAO.delete(id);
    }

}
