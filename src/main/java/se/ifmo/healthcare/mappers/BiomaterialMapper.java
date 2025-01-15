package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.*;
import se.ifmo.healthcare.dto.*;

import java.time.LocalDate;

public class BiomaterialMapper {
    public static BiomaterialDTO toDTO(Biomaterial biomaterial) {
        BiomaterialDTO dto = new BiomaterialDTO();
        dto.biomaterialId = biomaterial.getBiomaterialId();
        dto.collectionDate = biomaterial.getCollectionDate().toString();
        dto.deliveryDate = biomaterial.getDeliveryDate().toString();
        dto.setPatient(PatientMapper.toDTO(biomaterial.getPatient()));
        dto.status = biomaterial.getStatus();
        dto.storagePeriodHours = biomaterial.getStoragePeriodHours();
        dto.type = biomaterial.getType();
        return dto;
    }

    public static Biomaterial toEntity(BiomaterialDTO dto) {
        Biomaterial biomaterial = new Biomaterial();
        biomaterial.setBiomaterialId(dto.biomaterialId);
        biomaterial.setStatus(dto.status);
        biomaterial.setCollectionDate(LocalDate.parse(dto.collectionDate));
        biomaterial.setDeliveryDate(LocalDate.parse(dto.deliveryDate));
        biomaterial.setStoragePeriodHours(dto.storagePeriodHours);
        biomaterial.setType(dto.type);
        biomaterial.setPatient(PatientMapper.toEntity(dto.getPatient()));
        return biomaterial;
    }
}