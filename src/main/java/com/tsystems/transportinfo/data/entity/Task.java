package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private DriverAction action;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name="driver_id", unique = true)
    private Driver driver;

    @OneToOne
    @JoinColumn(name="truck_plate", unique = true)
    public Truck truck;

}
