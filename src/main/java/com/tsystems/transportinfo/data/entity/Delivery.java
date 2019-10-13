package com.tsystems.transportinfo.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String destination;

    @OneToOne
    @JoinColumn(name="cargo_id", unique = true)
    public Cargo cargo;

    @OneToOne
    @JoinColumn(name="truck_plate", unique = true)
    public Truck truck;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

}
