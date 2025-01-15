package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Biomaterial")
public class Biomaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biomaterialId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private LocalDate collectionDate;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer storagePeriodHours;

}
