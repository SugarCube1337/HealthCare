package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.BiomaterialDAO;
import se.ifmo.healthcare.dao.ResearchRegistrationDAO;
import se.ifmo.healthcare.dao.StaffMemberDAO;
import se.ifmo.healthcare.dto.ResearchDTO;
import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.mappers.ResearchRegistrationMapper;
import se.ifmo.healthcare.mappers.StaffMemberMapper;
import se.ifmo.healthcare.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResearchRegistrationService {
    @Autowired
    private ResearchRegistrationDAO researchRegistrationDAO;

    @Autowired
     private BiomaterialDAO biomaterialDAO;

    @Autowired
    private StaffMemberDAO staffMemberDAO;

    public void createResearchRegistration(ResearchRegistrationDTO dto) {
        if (dto.getResearch().getWorkWithBiomaterial().getBiomaterial().getBiomaterialId() == null) {
            throw new IllegalArgumentException("Biomaterial ID is required.");
        }
        Long biomaterialId = dto.getResearch().getWorkWithBiomaterial().getBiomaterial().getBiomaterialId();
        Biomaterial biomaterial = biomaterialDAO.findById(biomaterialId);
        WorkWithBiomaterials workWithBiomaterials = new WorkWithBiomaterials();
        workWithBiomaterials.setBiomaterial(biomaterial);
        workWithBiomaterials.setBeginTime(LocalDate.parse(dto.getResearch().getWorkWithBiomaterial().getBeginTime()));
        Long staffMemberId = dto.getResearch().getWorkWithBiomaterial().getStaffMember().getId();
        StaffMember staffMember = staffMemberDAO.findById(staffMemberId);
        if (staffMember == null){
            System.out.println("Staff Member not found");
        }
        System.out.println(staffMemberId);
        workWithBiomaterials.setStaffMember(staffMember);
        System.out.println(workWithBiomaterials.getStaffMember());

        Research research = new Research();
        research.setResearchMethod(dto.getResearch().getResearchMethod());
        research.setTechnology(dto.getResearch().getTechnology());
        research.setDate(dto.getDate());
        research.setWorkWithBiomaterial(workWithBiomaterials);

        ResearchRegistration registration = new ResearchRegistration();
        registration.setPatientId(dto.getPatientId());
        registration.setDate(dto.getDate());
        registration.setTimeStart(dto.getTimeStart());
        registration.setTimeEnd(dto.getTimeEnd());
        registration.setResearch(research);

        researchRegistrationDAO.save(registration);
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
