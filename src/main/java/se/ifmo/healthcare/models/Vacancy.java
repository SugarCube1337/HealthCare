package se.ifmo.healthcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String position;

    @NotNull
    private String requirements;

    @NotNull
    private LocalDate openingDate;

    @NotNull
    private Integer minSalary;

    private String status;

    private String typeOfEmployment;
}


