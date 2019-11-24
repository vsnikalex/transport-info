package com.tsystems.transportinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    private long id;
    private boolean done;
    private String truck;
    private String[] cargoes;
    private String[] drivers;
    private String route;

}
