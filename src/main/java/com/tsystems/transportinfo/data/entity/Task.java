package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;

    @OneToOne
    @JoinColumn(name="truck_id")
    public Truck truck;

    public Task withStart(LocalDateTime newStart) {
        return new Task(this.id, this.action, newStart, this.end, this.driver, this.truck);
    }

    public Task withEnd(LocalDateTime newEnd) {
        return new Task(this.id, this.action, this.start, newEnd, this.driver, this.truck);
    }

}
