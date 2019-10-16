package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.converters.NodeConverter;
import de.westnordost.osmapi.map.data.Node;
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

    @Column
    @Convert(converter = NodeConverter.class)
    private Node location;

    @ManyToOne
    @JoinColumn(name="delivery_id", unique = true)
    private Delivery delivery;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Task> tasks;

}
