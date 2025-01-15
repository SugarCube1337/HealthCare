package se.ifmo.healthcare.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}