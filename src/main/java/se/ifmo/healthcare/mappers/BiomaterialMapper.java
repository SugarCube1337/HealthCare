package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.*;
import se.ifmo.healthcare.dto.*;

import java.time.LocalDate;

public class BiomaterialMapper {
    public static BiomaterialDTO toDTO(Biomaterial biomaterial) {
        BiomaterialDTO dto = new BiomaterialDTO();
        dto.setBiomaterialId(biomaterial.getBiomaterialId());
        dto.setCollectionDate(biomaterial.getCollectionDate().toString());
        dto.setDeliveryDate(biomaterial.getDeliveryDate().toString());
        dto.setPatient(PatientMapper.toDTO(biomaterial.getPatient()));
        dto.setStatus(biomaterial.getStatus());
        dto.setStoragePeriodHours(biomaterial.getStoragePeriodHours());
        dto.setType(biomaterial.getType());
        return dto;
    }

    public static Biomaterial toEntity(BiomaterialDTO dto) {
        Biomaterial biomaterial = new Biomaterial();
        biomaterial.setBiomaterialId(dto.getBiomaterialId());
        biomaterial.setStatus(dto.getStatus());
        biomaterial.setCollectionDate(LocalDate.parse(dto.getCollectionDate()));
        biomaterial.setDeliveryDate(LocalDate.parse(dto.getDeliveryDate()));
        biomaterial.setStoragePeriodHours(dto.getStoragePeriodHours());
        biomaterial.setType(dto.getType());
        biomaterial.setPatient(PatientMapper.toEntity(dto.getPatient()));
        return biomaterial;
    }
}