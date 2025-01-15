package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.*;
import se.ifmo.healthcare.dto.*;
import se.ifmo.healthcare.mappers.*;
import se.ifmo.healthcare.models.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisDAO diagnosisDAO;

    public void createDiagnosis(DiagnosisDTO dto) {
        Diagnosis diagnosis = DiagnosisMapper.toEntity(dto);
        diagnosisDAO.save(diagnosis);
    }

    public DiagnosisDTO getDiagnosisById(Long id) {
        return DiagnosisMapper.toDTO(diagnosisDAO.findById(id));
    }

    public List<DiagnosisDTO> getAllDiagnoses() {
        return diagnosisDAO.findAll().stream()
                .map(DiagnosisMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteDiagnosis(Long id) {
        diagnosisDAO.delete(id);
    }
}