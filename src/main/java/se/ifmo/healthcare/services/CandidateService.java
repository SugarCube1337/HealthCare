package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.CandidateDAO;
import se.ifmo.healthcare.dao.StaffMemberDAO;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.dao.VacancyDAO;
import se.ifmo.healthcare.dto.CandidateDTO;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.mappers.CandidateMapper;
import se.ifmo.healthcare.mappers.StaffMemberMapper;
import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.models.StaffMember;
import se.ifmo.healthcare.models.User;
import se.ifmo.healthcare.models.Vacancy;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private CandidateDAO candidateDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StaffMemberDAO staffMemberDAO;

    @Autowired
    private VacancyDAO vacancyDAO;

    public void applyForVacancy(Long candidateId, Long vacancyId, String wantPosition) {
        Candidate candidate = candidateDAO.findById(candidateId);
        Vacancy vacancy = vacancyDAO.findById(vacancyId);

        if (candidate == null || vacancy == null) {
            throw new IllegalArgumentException("Candidate or Vacancy not found");
        }

        // Устанавливаем желаемую позицию
        candidate.setWantPosition(wantPosition);

        // Добавляем связь между кандидатом и вакансией
        candidate.getVacancies().add(vacancy);

        // Сохраняем изменения
        candidateDAO.save(candidate);
    }

    public void approveCandidate(Long candidateId, Long vacancyId) {
        Candidate candidate = candidateDAO.findById(candidateId);
        Vacancy vacancy = vacancyDAO.findById(vacancyId);
        StaffMember newStaff = new StaffMember();
        newStaff.setPerson(candidate.getPerson());
        newStaff.setPosition(vacancy.getPosition());
        newStaff.setQualification(candidate.getQualification());
        staffMemberDAO.save(newStaff);
    }

    public void rejectCandidate(Long candidateId, Long vacancyId) {
        Candidate candidate = candidateDAO.findById(candidateId);
        // Notify candidate about rejection ?????
    }


    public void createCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = CandidateMapper.toEntity(candidateDTO);
        candidate.setFillingDate(LocalDate.now());
        User user = new User();
        user.setUsername(candidateDTO.getUser().getUsername());
        user.setPassword(candidateDTO.getUser().getPassword());
        user.setRole("CANDIDATE");
        candidateDAO.save(candidate);
        userDAO.save(user);
    }


    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateDAO.findById(id);
        return candidate != null ? CandidateMapper.toDTO(candidate) : null;
    }

    public List<CandidateDTO> getCandidatesForVacancy(Long vacancyId) {
        Vacancy vacancy = vacancyDAO.findById(vacancyId); // Получаем вакансию
        if (vacancy == null) {
            return List.of(); // Если вакансии не существует, возвращаем пустой список
        }

        System.out.println("Vacancy: " + vacancy);

        // Получаем всех кандидатов
        List<Candidate> candidates = candidateDAO.findByVacancyId(vacancyId);
        System.out.println("All candidates for vacancy: " + candidates);

        // Фильтруем по позиции
        List<CandidateDTO> filteredCandidates = candidates.stream()
                .filter(candidate -> candidate.getWantPosition().equals(vacancy.getPosition()))
                .map(CandidateMapper::toDTO)
                .collect(Collectors.toList());

        System.out.println("Filtered candidates: " + filteredCandidates);
        return filteredCandidates;
    }




    public List<CandidateDTO> getAllCandidates() {
        return candidateDAO.findAll()
                .stream()
                .map(CandidateMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = CandidateMapper.toEntity(candidateDTO);
        candidateDAO.update(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateDAO.delete(id);
    }

    public CandidateDTO getCandidateByPersonId(Long id) {
        Candidate candidate = candidateDAO.findCandidateByPersonId(id);
        return candidate == null ? null: CandidateMapper.toDTO(candidate);

    }
    public void updateWantPosition(Long candidateId, String wantPosition) {
        Candidate candidate = candidateDAO.findById(candidateId);
        if (candidate != null) {
            candidate.setWantPosition(wantPosition);
            candidateDAO.update(candidate);
        }
    }
}
