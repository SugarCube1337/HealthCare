package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.CandidateDAO;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.dto.CandidateDTO;
import se.ifmo.healthcare.mappers.CandidateMapper;
import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private CandidateDAO candidateDAO;



    @Autowired
    private UserDAO userDAO;

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



    public List<CandidateDTO> getCandidatesByVacancyId(Long vacancyId) {
        return candidateDAO.findCandidatesByVacancyId(vacancyId).stream()
                .map(CandidateMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void rejectCandidate(Long candidateId) {
        Candidate candidate = candidateDAO.findById(candidateId);
    }

    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateDAO.findById(id);
        return candidate != null ? CandidateMapper.toDTO(candidate) : null;
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
}
