package com.tsystems.transportinfo.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "driversStat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriversStat implements Serializable {

    private int available;
    private int driving;
    private int others;
    private int total;

}
