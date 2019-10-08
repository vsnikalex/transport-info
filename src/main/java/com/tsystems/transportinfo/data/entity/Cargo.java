package com.tsystems.transportinfo.data.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private String weight;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

}
