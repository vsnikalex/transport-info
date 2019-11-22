package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "truck")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String plate;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    @Column(name = "location", columnDefinition="VARCHAR(512)", nullable = false)
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @OneToOne(mappedBy = "truck")
    private Delivery delivery;

    public Truck(long id) {
        this.id = id;
    }

}
