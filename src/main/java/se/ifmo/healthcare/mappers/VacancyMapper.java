package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.Vacancy;
import se.ifmo.healthcare.dto.VacancyDTO;

import java.time.LocalDate;

public class VacancyMapper {

    public static VacancyDTO toDTO(Vacancy vacancy) {
        if (vacancy == null) return null;
        VacancyDTO dto = new VacancyDTO();
        dto.id = vacancy.getId();
        dto.position = vacancy.getPosition();
        dto.requirements = vacancy.getRequirements();
        dto.openingDate = vacancy.getOpeningDate().toString();
        dto.minSalary = vacancy.getMinSalary();
        dto.status = vacancy.getStatus();
        dto.typeOfEmployment = vacancy.getTypeOfEmployment();
        return dto;
    }

    public static Vacancy toEntity(VacancyDTO dto) {
        if (dto == null) return null;
        Vacancy vacancy = new Vacancy();
        vacancy.setId(dto.id);
        vacancy.setPosition(dto.position);
        vacancy.setRequirements(dto.requirements);
        vacancy.setOpeningDate(LocalDate.parse(dto.openingDate));
        vacancy.setMinSalary(dto.minSalary);
        vacancy.setStatus(dto.status);
        vacancy.setTypeOfEmployment(dto.typeOfEmployment);
        return vacancy;
    }
}