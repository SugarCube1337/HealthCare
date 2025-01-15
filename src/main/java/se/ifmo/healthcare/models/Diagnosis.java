package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Diagnosis")
public class Diagnosis {
    @Id
    private Long diagnosisId;

    @Column(nullable = false)
    private String diagnosisName;

    @Column(nullable = false)
    private String icdCode;

}

