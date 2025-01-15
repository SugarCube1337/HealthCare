package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.Vacancy;
import se.ifmo.healthcare.dto.VacancyDTO;

import java.time.LocalDate;

public class VacancyMapper {

    public static VacancyDTO toDTO(Vacancy vacancy) {
        if (vacancy == null) return null;
        VacancyDTO dto = new VacancyDTO();
        dto.setId(vacancy.getId());
        dto.setPosition(vacancy.getPosition());
        dto.setRequirements(vacancy.getRequirements());
        dto.setOpeningDate(vacancy.getOpeningDate().toString());
        dto.setMinSalary(vacancy.getMinSalary());
        dto.setStatus(vacancy.getStatus());
        dto.setTypeOfEmployment(vacancy.getTypeOfEmployment());
        return dto;
    }

    public static Vacancy toEntity(VacancyDTO dto) {
        if (dto == null) return null;
        Vacancy vacancy = new Vacancy();
        vacancy.setId(dto.getId());
        vacancy.setPosition(dto.getPosition());
        vacancy.setRequirements(dto.getRequirements());
        vacancy.setOpeningDate(LocalDate.parse(dto.getOpeningDate()));
        vacancy.setMinSalary(dto.getMinSalary());
        vacancy.setStatus(dto.getStatus());
        vacancy.setTypeOfEmployment(dto.getTypeOfEmployment());
        return vacancy;
    }
}