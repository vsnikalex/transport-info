package com.tsystems.transportinfo.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estWorkHours", nullable = false)
    private Long estWorkHours;

    @Column(name = "done", nullable = false)
    private boolean done;

    @Column(name = "route", nullable = false)
    private String route;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    public List<Cargo> cargo;

    @OneToOne
    @JoinColumn(name="truck_id", nullable = false)
    public Truck truck;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Driver> drivers;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    public Delivery(long id) {
        this.id = id;
    }

}
