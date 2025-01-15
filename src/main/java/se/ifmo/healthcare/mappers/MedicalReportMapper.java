package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.MedicalReportDTO;
import se.ifmo.healthcare.models.MedicalReport;


public class MedicalReportMapper {
    public static MedicalReportDTO toDTO(MedicalReport medicalReport) {
        MedicalReportDTO dto = new MedicalReportDTO();
        dto.medicalReportId = medicalReport.getMedicalReportId();
        dto.result = medicalReport.getResult();
        dto.staffMember = StaffMemberMapper.toDTO(medicalReport.getStaffMember());
        dto.research = ResearchMapper.toDTO(medicalReport.getResearch());
        return dto;
    }

    public static MedicalReport toEntity(MedicalReportDTO dto) {
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setMedicalReportId(dto.medicalReportId);
        medicalReport.setResearch(ResearchMapper.toEntity(dto.research));
        medicalReport.setResult(dto.result);
        medicalReport.setStaffMember(StaffMemberMapper.toEntity(dto.staffMember));
        return medicalReport;
    }
}
