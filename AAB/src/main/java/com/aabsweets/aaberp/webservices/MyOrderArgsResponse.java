
package com.aabsweets.aaberp.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MyOrderArgsResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "myOrderArgsResult"
})
@XmlRootElement(name = "MyOrderArgsResponse")
public class MyOrderArgsResponse {

    @XmlElement(name = "MyOrderArgsResult")
    protected String myOrderArgsResult;

    /**
     * Gets the value of the myOrderArgsResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMyOrderArgsResult() {
        return myOrderArgsResult;
    }

    /**
     * Sets the value of the myOrderArgsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMyOrderArgsResult(String value) {
        this.myOrderArgsResult = value;
    }

}
