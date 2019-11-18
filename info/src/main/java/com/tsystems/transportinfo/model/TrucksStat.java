package com.tsystems.transportinfo.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "trucksStat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrucksStat implements Serializable {

    private int available;
    private int inUse;
    private int defective;
    private int total;

}
