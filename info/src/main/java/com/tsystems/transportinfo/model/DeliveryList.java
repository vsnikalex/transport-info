package com.tsystems.transportinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "deliveryList")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryList implements Serializable {

    private List<Delivery> deliveries;

}
