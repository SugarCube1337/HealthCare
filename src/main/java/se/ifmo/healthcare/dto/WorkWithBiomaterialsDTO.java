package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class WorkWithBiomaterialsDTO {
    private Long workWithBiomaterialId;
    private StaffMemberDTO staffMember;
    private BiomaterialDTO biomaterial;
    private String beginTime;
}

