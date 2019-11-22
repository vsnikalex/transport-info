package com.tsystems.transportinfo.data.entity;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.GeocodingEntryConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "location", columnDefinition="VARCHAR(512)", nullable = false)
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
