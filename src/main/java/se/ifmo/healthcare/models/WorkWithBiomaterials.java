package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "Work_With_Biomaterials")
public class WorkWithBiomaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workWithBiomaterialId;

    @ManyToOne
    @JoinColumn(name = "staff_member_id", nullable = false)
    private StaffMember staffMember;

    @ManyToOne
    @JoinColumn(name = "biomaterial_id", nullable = false)
    private Biomaterial biomaterial;

    @Column(nullable = false)
    private LocalDate beginTime;

}