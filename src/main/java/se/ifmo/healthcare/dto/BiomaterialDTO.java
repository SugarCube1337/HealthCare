package se.ifmo.healthcare.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class BiomaterialDTO {
    private Long biomaterialId;
    private PatientDTO patient;
    private String collectionDate;
    private String type;
    private String deliveryDate;
    private String status;
    private Integer storagePeriodHours;
    private LocalDate storage_expiry_date;
}