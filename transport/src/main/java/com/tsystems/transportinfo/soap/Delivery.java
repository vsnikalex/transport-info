
package com.tsystems.transportinfo.soap;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for delivery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delivery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cargoes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="drivers" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="route" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="truck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delivery", propOrder = {
    "cargoes",
    "drivers",
    "id",
    "route",
    "truck"
})
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @XmlElement(nillable = true)
    protected List<String> cargoes;
    @XmlElement(nillable = true)
    protected List<String> drivers;
    protected long id;
    protected String route;
    protected String truck;

    /**
     * Gets the value of the cargoes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cargoes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCargoes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCargoes() {
        if (cargoes == null) {
            cargoes = new ArrayList<String>();
        }
        return this.cargoes;
    }

    /**
     * Gets the value of the drivers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drivers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrivers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDrivers() {
        if (drivers == null) {
            drivers = new ArrayList<String>();
        }
        return this.drivers;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoute(String value) {
        this.route = value;
    }

    /**
     * Gets the value of the truck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruck() {
        return truck;
    }

    /**
     * Sets the value of the truck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruck(String value) {
        this.truck = value;
    }

}
