package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Research")
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long researchId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_with_biomaterial_id", nullable = false)
    private WorkWithBiomaterials workWithBiomaterial;

    @Column(nullable = false)
    private String researchMethod;

    @Column(nullable = false)
    private String technology;

    @Column(nullable = false)
    private LocalDate date;

}
