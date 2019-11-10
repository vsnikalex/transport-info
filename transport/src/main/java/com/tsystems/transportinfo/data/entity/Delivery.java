package com.tsystems.transportinfo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery")
@NoArgsConstructor
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "done")
    private boolean done;

    @Column(name = "route")
    private String route;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    public List<Cargo> cargo;

    @OneToOne
    @JoinColumn(name="truck_id", nullable = false)
    public Truck truck;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Driver> drivers;

    public Delivery(long id) {
        this.id = id;
    }

}