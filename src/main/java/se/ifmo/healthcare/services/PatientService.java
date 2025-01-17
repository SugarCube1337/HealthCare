package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.PatientDAO;
import se.ifmo.healthcare.dao.UserDAO;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.mappers.PatientMapper;
import se.ifmo.healthcare.models.Patient;
import se.ifmo.healthcare.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private UserDAO userDAO;

    public void createPatient(PatientDTO patientDTO) {
        Patient patient = PatientMapper.toEntity(patientDTO);
        User user = new User();
        user.setUsername(patientDTO.getUser().getUsername());
        user.setPassword(patientDTO.getUser().getPassword());
        user.setRole("PATIENT");
        userDAO.save(user);
        patientDAO.save(patient);
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientDAO.findById(id);
        return patient == null ? null : PatientMapper.toDTO(patient);
    }

    public PatientDTO getPatientByPersonId(Long id){
        Patient patient = patientDAO.findUPatientByPersonId(id);
        return patient == null ? null : PatientMapper.toDTO(patient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientDAO.findAll().stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updatePatient(PatientDTO patientDTO) {
        Patient patient = PatientMapper.toEntity(patientDTO);
        patientDAO.update(patient);
    }

    public void deletePatient(Long id) {
        patientDAO.delete(id);
    }
}
