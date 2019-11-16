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
public class DriversStat {

    private int available;
    private int driving;
    private int others;
    private int total;

}
