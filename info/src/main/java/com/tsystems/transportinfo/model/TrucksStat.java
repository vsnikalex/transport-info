package com.tsystems.transportinfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "driverStat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrucksStat {

    private int available;
    private int inUse;
    private int defective;
    private int total;

}
