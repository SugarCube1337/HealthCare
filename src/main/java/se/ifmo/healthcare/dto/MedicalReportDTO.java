package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class MedicalReportDTO {
    private Long medicalReportId;
    private StaffMemberDTO staffMember;
    private ResearchDTO research;
    private String result;
}