
package com.tsystems.transportinfo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for driversStat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="driversStat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="available" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="driving" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="others" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driversStat", propOrder = {
    "available",
    "driving",
    "others",
    "total"
})
@NoArgsConstructor
@AllArgsConstructor
public class DriversStat {

    protected int available;
    protected int driving;
    protected int others;
    protected int total;

    /**
     * Gets the value of the available property.
     * 
     */
    public int getAvailable() {
        return available;
    }

    /**
     * Sets the value of the available property.
     * 
     */
    public void setAvailable(int value) {
        this.available = value;
    }

    /**
     * Gets the value of the driving property.
     * 
     */
    public int getDriving() {
        return driving;
    }

    /**
     * Sets the value of the driving property.
     * 
     */
    public void setDriving(int value) {
        this.driving = value;
    }

    /**
     * Gets the value of the others property.
     * 
     */
    public int getOthers() {
        return others;
    }

    /**
     * Sets the value of the others property.
     * 
     */
    public void setOthers(int value) {
        this.others = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

}
