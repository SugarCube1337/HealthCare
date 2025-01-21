package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.VacancyDAO;
import se.ifmo.healthcare.dto.VacancyDTO;
import se.ifmo.healthcare.mappers.VacancyMapper;
import se.ifmo.healthcare.models.Vacancy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    @Autowired
    private VacancyDAO vacancyDAO;

    public void closeVacancy(Long vacancyId) {
        vacancyDAO.closeVacancy(vacancyId);
    }

    public void createVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = VacancyMapper.toEntity(vacancyDTO);
        vacancyDAO.save(vacancy);
    }

    public VacancyDTO getVacancyById(Long id) {
        Vacancy vacancy = vacancyDAO.findById(id);
        return vacancy != null ? VacancyMapper.toDTO(vacancy) : null;
    }

    public Vacancy findById(Long id) {
        return vacancyDAO.findById(id);
    }

    public List<VacancyDTO> getAllVacancies() {
        return vacancyDAO.findAll()
                .stream()
                .map(VacancyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = VacancyMapper.toEntity(vacancyDTO);
        vacancyDAO.update(vacancy);
    }

    public void deleteVacancy(Long id) {
        vacancyDAO.delete(id);
    }
}
