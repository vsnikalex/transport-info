package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "truck")
@Getter
@Setter
public class Truck {

    @Id
    @Column
    private String plate;

    @Column
    private int capacity;

    @Column
    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    @Column
    private String location;

    @OneToOne
    @JoinColumn(name="delivery_id", unique = true)
    private Delivery delivery;

}
