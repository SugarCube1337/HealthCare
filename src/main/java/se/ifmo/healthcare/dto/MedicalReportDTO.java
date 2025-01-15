package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class MedicalReportDTO {
    public Long medicalReportId;
    public StaffMemberDTO staffMember;
    public ResearchDTO research;
    public String result;
}