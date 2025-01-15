package se.ifmo.healthcare.dto;

import lombok.Data;

@Data
public class BiomaterialDTO {
    public Long biomaterialId;
    public PatientDTO patient;
    public String collectionDate;
    public String type;
    public String deliveryDate;
    public String status;
    public Integer storagePeriodHours;
}