package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import com.tsystems.transportinfo.data.entity.enums.DepotType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "depot")
@NoArgsConstructor
@Getter
@Setter
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location", columnDefinition="VARCHAR(512)", nullable = false)
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DepotType type;

    public Depot(long id) {
        this.id = id;
    }

}
