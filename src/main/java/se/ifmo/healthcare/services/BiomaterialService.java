package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.BiomaterialDAO;
import se.ifmo.healthcare.dto.BiomaterialDTO;
import se.ifmo.healthcare.mappers.BiomaterialMapper;
import se.ifmo.healthcare.models.Biomaterial;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BiomaterialService {
    @Autowired
    private BiomaterialDAO biomaterialDAO;

    public void createBiomaterial(BiomaterialDTO dto) {
        Biomaterial biomaterial = BiomaterialMapper.toEntity(dto);
        biomaterialDAO.save(biomaterial);
    }

    public void updateBiomaterial(BiomaterialDTO biomaterialDTO) {
        Biomaterial biomaterial = biomaterialDAO.findById(biomaterialDTO.getBiomaterialId());

        biomaterial.setType(biomaterialDTO.getType());
        biomaterial.setStatus(biomaterialDTO.getStatus());
        biomaterial.setStoragePeriodHours(biomaterialDTO.getStoragePeriodHours());

        biomaterialDAO.save(biomaterial);
    }
    public void sendToNurse(Long id) {
        Biomaterial biomaterial = biomaterialDAO.findById(id);
        biomaterial.setStatus("Sent to Nurse");
        biomaterialDAO.save(biomaterial);
    }


    public BiomaterialDTO getBiomaterialById(Long id) {
        return BiomaterialMapper.toDTO(biomaterialDAO.findById(id));
    }

    public List<BiomaterialDTO> getAllBiomaterials() {
        return biomaterialDAO.findAll().stream()
                .map(BiomaterialMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteBiomaterial(Long id) {
        biomaterialDAO.delete(id);
    }
}
