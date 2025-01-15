package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.MedicalReportDTO;
import se.ifmo.healthcare.models.MedicalReport;


public class MedicalReportMapper {
    public static MedicalReportDTO toDTO(MedicalReport medicalReport) {
        MedicalReportDTO dto = new MedicalReportDTO();
        dto.setMedicalReportId(medicalReport.getMedicalReportId());
        dto.setResult(medicalReport.getResult());
        dto.setStaffMember(StaffMemberMapper.toDTO(medicalReport.getStaffMember()));
        dto.setResearch(ResearchMapper.toDTO(medicalReport.getResearch()));
        return dto;
    }

    public static MedicalReport toEntity(MedicalReportDTO dto) {
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setMedicalReportId(dto.getMedicalReportId());
        medicalReport.setResearch(ResearchMapper.toEntity(dto.getResearch()));
        medicalReport.setResult(dto.getResult());
        medicalReport.setStaffMember(StaffMemberMapper.toEntity(dto.getStaffMember()));
        return medicalReport;
    }
}
