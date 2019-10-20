package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "driver")
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(columnDefinition="VARCHAR(512)")
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @ManyToOne
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Task> tasks;

}
