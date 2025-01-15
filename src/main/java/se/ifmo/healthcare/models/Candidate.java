package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @NotNull
    private String wantPosition;


    private LocalDate fillingDate;

    @NotNull
    private String qualification;

    @NotNull
    private Integer experience;

    @ManyToMany
    @JoinTable(
            name = "candidate_vacancy",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id")
    )
    private List<Vacancy> vacancies;

    public Candidate() {
        this.fillingDate = LocalDate.now();
    }
}