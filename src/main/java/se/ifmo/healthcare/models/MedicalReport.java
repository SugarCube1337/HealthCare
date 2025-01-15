package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Medical_Report")
public class MedicalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalReportId;

    @ManyToOne
    @JoinColumn(name = "staff_member_id", nullable = false)
    private StaffMember staffMember;

    @ManyToOne
    @JoinColumn(name = "research_id", nullable = false)
    private Research research;

    private String result;

}