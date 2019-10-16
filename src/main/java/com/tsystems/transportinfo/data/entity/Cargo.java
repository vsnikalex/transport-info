package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.converters.NodeConverter;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import de.westnordost.osmapi.map.data.Node;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @Column
    private int weight;

    @Column
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @Column
    @Convert(converter = NodeConverter.class)
    private Node location;

    @Column
    @Convert(converter = NodeConverter.class)
    private Node destination;

    @ManyToOne
    @JoinColumn(name="delivery_id", unique = true)
    private Delivery delivery;

}
