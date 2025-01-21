package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.MedicalReportDAO;
import se.ifmo.healthcare.dao.StaffMemberDAO;
import se.ifmo.healthcare.dto.MedicalReportDTO;
import se.ifmo.healthcare.mappers.MedicalReportMapper;
import se.ifmo.healthcare.mappers.ResearchMapper;
import se.ifmo.healthcare.mappers.StaffMemberMapper;
import se.ifmo.healthcare.models.MedicalReport;
import se.ifmo.healthcare.models.StaffMember;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalReportService {
    @Autowired
    private MedicalReportDAO medicalReportDAO;

    @Autowired
    private StaffMemberDAO staffMemberDAO;

    public void createMedicalReport(MedicalReportDTO dto) {
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setResult(dto.getResult());
        medicalReport.setResearch(ResearchMapper.toEntity(dto.getResearch()));
        medicalReport.setStaffMember(StaffMemberMapper.toEntity(dto.getStaffMember()));
        medicalReportDAO.save(medicalReport);
    }

    public MedicalReportDTO getMedicalReportById(Long id) {
        return MedicalReportMapper.toDTO(medicalReportDAO.findById(id));
    }

    public List<MedicalReportDTO> getAllReports() {
        return medicalReportDAO.findAll().stream()
                .map(MedicalReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MedicalReportDTO> getPatientReport(Long id) {
        return medicalReportDAO.findForPatient(id).stream()
                .map(MedicalReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteMedicalReport(Long id) {
        medicalReportDAO.delete(id);
    }
}
