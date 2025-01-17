package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "staff_member", schema = "healthcare")
public class StaffMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_member_id")
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "qualification", nullable = false)
    private String qualification;
}
