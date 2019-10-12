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

    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @OneToOne(optional = false, mappedBy="delivery")
    public Truck truck;

    @OneToOne(optional = false, mappedBy="delivery")
    public Cargo cargo;

}
