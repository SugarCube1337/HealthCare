package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.WorkWithBiomaterialsDAO;
import se.ifmo.healthcare.dto.WorkWithBiomaterialsDTO;
import se.ifmo.healthcare.mappers.WorkWithBiomaterialsMapper;
import se.ifmo.healthcare.models.WorkWithBiomaterials;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkWithBiomaterialsService {
    @Autowired
    private WorkWithBiomaterialsDAO workWithBiomaterialsDAO;

    public void createWorkWithBiomaterials(WorkWithBiomaterialsDTO dto) {
        WorkWithBiomaterials workWithBiomaterials = WorkWithBiomaterialsMapper.toEntity(dto);
        workWithBiomaterialsDAO.save(workWithBiomaterials);
    }

    public WorkWithBiomaterialsDTO getWorkWithBiomaterialsById(Long id) {
        return WorkWithBiomaterialsMapper.toDTO(workWithBiomaterialsDAO.findById(id));
    }

    public List<WorkWithBiomaterialsDTO> getAllWorkWithBiomaterials() {
        return workWithBiomaterialsDAO.findAll().stream()
                .map(WorkWithBiomaterialsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteWorkWithBiomaterials(Long id) {
        workWithBiomaterialsDAO.delete(id);
    }
}
