
package com.tsystems.transportinfo.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateDeliveryList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateDeliveryList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deliveryList" type="{http://soap.transportinfo.tsystems.com/}deliveryList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateDeliveryList", propOrder = {
    "deliveryList"
})
public class UpdateDeliveryList {

    protected DeliveryList deliveryList;

    /**
     * Gets the value of the deliveryList property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryList }
     *     
     */
    public DeliveryList getDeliveryList() {
        return deliveryList;
    }

    /**
     * Sets the value of the deliveryList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryList }
     *     
     */
    public void setDeliveryList(DeliveryList value) {
        this.deliveryList = value;
    }

}
