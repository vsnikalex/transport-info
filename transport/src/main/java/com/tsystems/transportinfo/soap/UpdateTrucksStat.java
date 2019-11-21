
package com.tsystems.transportinfo.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateTrucksStat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateTrucksStat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trucksStat" type="{http://soap.transportinfo.tsystems.com/}trucksStat" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateTrucksStat", propOrder = {
    "trucksStat"
})
public class UpdateTrucksStat {

    protected TrucksStat trucksStat;

    /**
     * Gets the value of the trucksStat property.
     * 
     * @return
     *     possible object is
     *     {@link TrucksStat }
     *     
     */
    public TrucksStat getTrucksStat() {
        return trucksStat;
    }

    /**
     * Sets the value of the trucksStat property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrucksStat }
     *     
     */
    public void setTrucksStat(TrucksStat value) {
        this.trucksStat = value;
    }

}
