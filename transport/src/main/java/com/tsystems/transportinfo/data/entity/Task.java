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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverAction action;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end")
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
