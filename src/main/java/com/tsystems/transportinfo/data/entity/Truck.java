package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.converters.GeocodingEntryConverter;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
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

    @Column(columnDefinition="VARCHAR(512)")
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @OneToOne(mappedBy = "truck")
    private Delivery delivery;

}
