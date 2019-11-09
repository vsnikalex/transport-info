package com.tsystems.transportinfo.data.entity;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@NoArgsConstructor
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private int weight;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @ManyToOne
    @JoinColumn(name="startDepot_id")
    private Depot startDepot;

    @ManyToOne
    @JoinColumn(name="endDepot_id")
    private Depot endDepot;

    @ManyToOne
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    public Cargo(long id) {
        this.id = id;
    }

}
