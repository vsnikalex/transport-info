package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.converters.NodeListConverter;
import lombok.Getter;
import lombok.Setter;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

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
    @Convert(converter = NodeListConverter.class)
    private List<Node> route;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    public List<Cargo> cargo;

    @OneToOne
    @JoinColumn(name="truck_plate", unique = true)
    public Truck truck;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Driver> drivers;

}
