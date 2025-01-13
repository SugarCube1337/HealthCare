package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.PatientDAO;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.mappers.PatientMapper;
import se.ifmo.healthcare.models.Patient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    public void createPatient(PatientDTO patientDTO) {
        Patient patient = PatientMapper.toEntity(patientDTO);
        patientDAO.save(patient);
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientDAO.findById(id);
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
