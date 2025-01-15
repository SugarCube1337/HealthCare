package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.BiomaterialDTO;
import se.ifmo.healthcare.dto.WorkWithBiomaterialsDTO;
import se.ifmo.healthcare.models.Biomaterial;
import se.ifmo.healthcare.models.WorkWithBiomaterials;

import java.time.LocalDate;

public class WorkWithBiomaterialsMapper {

    public static WorkWithBiomaterialsDTO toDTO(WorkWithBiomaterials workWithBiomaterials) {
        WorkWithBiomaterialsDTO dto = new WorkWithBiomaterialsDTO();
        dto.workWithBiomaterialId  = workWithBiomaterials.getWorkWithBiomaterialId();
        dto.biomaterial = BiomaterialMapper.toDTO(workWithBiomaterials.getBiomaterial());
        dto.beginTime = workWithBiomaterials.getBeginTime().toString();
        dto.staffMember = StaffMemberMapper.toDTO(workWithBiomaterials.getStaffMember());
        return dto;
    }

    public static WorkWithBiomaterials toEntity(WorkWithBiomaterialsDTO dto) {
        WorkWithBiomaterials workWithBiomaterials = new WorkWithBiomaterials();
        workWithBiomaterials.setWorkWithBiomaterialId(dto.workWithBiomaterialId);
        workWithBiomaterials.setBiomaterial(BiomaterialMapper.toEntity(dto.biomaterial));
        workWithBiomaterials.setBeginTime(LocalDate.parse(dto.beginTime));
        workWithBiomaterials.setStaffMember(StaffMemberMapper.toEntity(dto.staffMember));
        return workWithBiomaterials;
    }
}
