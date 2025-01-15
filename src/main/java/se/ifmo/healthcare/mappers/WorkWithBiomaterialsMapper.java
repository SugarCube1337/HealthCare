package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.dto.BiomaterialDTO;
import se.ifmo.healthcare.dto.WorkWithBiomaterialsDTO;
import se.ifmo.healthcare.models.Biomaterial;
import se.ifmo.healthcare.models.WorkWithBiomaterials;

import java.time.LocalDate;

public class WorkWithBiomaterialsMapper {

    public static WorkWithBiomaterialsDTO toDTO(WorkWithBiomaterials workWithBiomaterials) {
        WorkWithBiomaterialsDTO dto = new WorkWithBiomaterialsDTO();
        dto.setWorkWithBiomaterialId(workWithBiomaterials.getWorkWithBiomaterialId());
        dto.setBiomaterial(BiomaterialMapper.toDTO(workWithBiomaterials.getBiomaterial()));
        dto.setBeginTime(workWithBiomaterials.getBeginTime().toString());
        dto.setStaffMember(StaffMemberMapper.toDTO(workWithBiomaterials.getStaffMember()));
        return dto;
    }

    public static WorkWithBiomaterials toEntity(WorkWithBiomaterialsDTO dto) {
        WorkWithBiomaterials workWithBiomaterials = new WorkWithBiomaterials();
        workWithBiomaterials.setWorkWithBiomaterialId(dto.getWorkWithBiomaterialId());
        workWithBiomaterials.setBiomaterial(BiomaterialMapper.toEntity(dto.getBiomaterial()));
        workWithBiomaterials.setBeginTime(LocalDate.parse(dto.getBeginTime()));
        workWithBiomaterials.setStaffMember(StaffMemberMapper.toEntity(dto.getStaffMember()));
        return workWithBiomaterials;
    }
}
