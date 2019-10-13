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
    @GeneratedValue
    private Long id;

    @Column
    private String location;

    @Column
    private String description;

    @Column
    private int weight;

    @Column
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @OneToOne(mappedBy = "cargo")
    private Delivery delivery;

}
