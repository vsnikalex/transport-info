package com.tsystems.transportinfo.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery")
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean done;

    @Column
    private String route;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    public List<Cargo> cargo;

    @OneToOne
    @JoinColumn(name="truck_id", nullable = false)
    public Truck truck;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Driver> drivers;

}
