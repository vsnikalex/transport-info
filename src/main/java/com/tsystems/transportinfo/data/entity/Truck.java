package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.converters.NodeConverter;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import de.westnordost.osmapi.map.data.Node;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "truck")
@Getter
@Setter
public class Truck {

    @Id
    @Column
    private String plate;

    @Column
    private int capacity;

    @Column
    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    @Column
    @Convert(converter = NodeConverter.class)
    private Node location;

    @OneToOne(mappedBy = "truck")
    private Delivery delivery;

}
