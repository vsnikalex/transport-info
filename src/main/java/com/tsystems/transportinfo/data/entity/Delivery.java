package com.tsystems.transportinfo.data.entity;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.converters.PointListConverter;
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
    @Convert(converter = PointListConverter.class)
    private List<GHPoint> route;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    public List<Cargo> cargo;

    @OneToOne
    @JoinColumn(name="truck_id", unique = true)
    public Truck truck;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Driver> drivers;

}
