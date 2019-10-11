package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
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

}
