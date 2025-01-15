package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class WorkWithBiomaterialsDTO {
    public Long workWithBiomaterialId;
    public StaffMemberDTO staffMember;
    public BiomaterialDTO biomaterial;
    public String beginTime;
}

