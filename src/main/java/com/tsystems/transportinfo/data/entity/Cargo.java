package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String description;

    @Column(name = "weight")
    private int weight;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    Cargo(String description, int weight, CargoStatus status) {
        this.description = description;
        this.weight = weight;
        this.status = status;
    }

}
