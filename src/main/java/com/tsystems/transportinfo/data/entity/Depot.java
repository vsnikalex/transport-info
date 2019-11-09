package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import com.tsystems.transportinfo.data.entity.enums.DepotType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "depot")
@Getter
@Setter
public class Depot {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "location", columnDefinition="VARCHAR(512)")
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DepotType type;

}
