package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
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

    @Column(columnDefinition="VARCHAR(512)")
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @Column(columnDefinition="VARCHAR(512)")
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry destination;

    @ManyToOne
    @JoinColumn(name="delivery_id", unique = true)
    private Delivery delivery;

}
