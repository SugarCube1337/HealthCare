package se.ifmo.healthcare.mappers;


import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.models.StaffMember;

public class StaffMemberMapper {
    public static StaffMemberDTO toDTO(StaffMember staffMember) {
        StaffMemberDTO dto = new StaffMemberDTO();
        dto.setId(staffMember.getId());
        dto.setPosition(staffMember.getPosition());
        dto.setQualification(staffMember.getQualification());
        dto.setPerson(PersonMapper.toDTO(staffMember.getPerson()));
        return dto;
    }

    public static StaffMember toEntity(StaffMemberDTO dto) {
        StaffMember staffMember = new StaffMember();
        staffMember.setId(dto.getId());
        staffMember.setPosition(dto.getPosition());
        staffMember.setQualification(dto.getQualification());
        staffMember.setPerson(PersonMapper.toEntity(dto.getPerson()));
        return staffMember;
    }
}
