
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
 *         &lt;element name="MySId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToShopCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ItemArg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "mySId",
    "toShopCode",
    "itemArg"
})
@XmlRootElement(name = "ItemAvailabilityCheck")
public class ItemAvailabilityCheck {

    @XmlElement(name = "MySId")
    protected String mySId;
    @XmlElement(name = "ToShopCode")
    protected String toShopCode;
    @XmlElement(name = "ItemArg")
    protected String itemArg;

    /**
     * Gets the value of the mySId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMySId() {
        return mySId;
    }

    /**
     * Sets the value of the mySId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMySId(String value) {
        this.mySId = value;
    }

    /**
     * Gets the value of the toShopCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToShopCode() {
        return toShopCode;
    }

    /**
     * Sets the value of the toShopCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToShopCode(String value) {
        this.toShopCode = value;
    }

    /**
     * Gets the value of the itemArg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemArg() {
        return itemArg;
    }

    /**
     * Sets the value of the itemArg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemArg(String value) {
        this.itemArg = value;
    }

}
