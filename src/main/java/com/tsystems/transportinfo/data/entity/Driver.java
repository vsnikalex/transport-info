package com.tsystems.transportinfo.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String location;

    @OneToMany
    private List<Task> tasks = new ArrayList<>();

}
