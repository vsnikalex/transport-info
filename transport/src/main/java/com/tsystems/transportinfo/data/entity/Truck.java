package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "truck")
@NoArgsConstructor
@Getter
@Setter
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
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

    public Truck(long id) {
        this.id = id;
    }

}
