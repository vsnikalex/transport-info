
package com.tsystems.transportinfo.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trucksStat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trucksStat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="available" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defective" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inUse" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "trucksStat", propOrder = {
    "available",
    "defective",
    "inUse",
    "total"
})
public class TrucksStat {

    protected int available;
    protected int defective;
    protected int inUse;
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
     * Gets the value of the defective property.
     * 
     */
    public int getDefective() {
        return defective;
    }

    /**
     * Sets the value of the defective property.
     * 
     */
    public void setDefective(int value) {
        this.defective = value;
    }

    /**
     * Gets the value of the inUse property.
     * 
     */
    public int getInUse() {
        return inUse;
    }

    /**
     * Sets the value of the inUse property.
     * 
     */
    public void setInUse(int value) {
        this.inUse = value;
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
