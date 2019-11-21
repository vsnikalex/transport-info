
package com.tsystems.transportinfo.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateDriversStat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateDriversStat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="driversStat" type="{http://soap.transportinfo.tsystems.com/}driversStat" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateDriversStat", propOrder = {
    "driversStat"
})
public class UpdateDriversStat {

    protected DriversStat driversStat;

    /**
     * Gets the value of the driversStat property.
     * 
     * @return
     *     possible object is
     *     {@link DriversStat }
     *     
     */
    public DriversStat getDriversStat() {
        return driversStat;
    }

    /**
     * Sets the value of the driversStat property.
     * 
     * @param value
     *     allowed object is
     *     {@link DriversStat }
     *     
     */
    public void setDriversStat(DriversStat value) {
        this.driversStat = value;
    }

}
