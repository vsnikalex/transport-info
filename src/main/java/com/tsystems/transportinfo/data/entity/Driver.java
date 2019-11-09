package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "driver")
@NoArgsConstructor
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "location", columnDefinition="VARCHAR(512)")
    @Convert(converter = GeocodingEntryConverter.class)
    private GHGeocodingEntry location;

    @ManyToOne
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Driver(long id) {
        this.id = id;
    }

}
