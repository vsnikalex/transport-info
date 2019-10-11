package com.tsystems.transportinfo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cargo")
@NoArgsConstructor
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    @NotNull
    @NotBlank
    @Size(min = 4, max = 250)
    private String description;

    @Column(name = "weight")
    @NotNull
    @Range(min = 100, max = 27_000)
    private int weight;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    Cargo(String description, int weight, CargoStatus status) {
        this.description = description;
        this.weight = weight;
        this.status = status;
    }
}
