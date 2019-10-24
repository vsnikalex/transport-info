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
    private String description;

    @Column
    private int weight;

    @Column
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Depot location;

    @ManyToOne
    @JoinColumn(name="destination_id")
    private Depot destination;

    @ManyToOne
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

}
