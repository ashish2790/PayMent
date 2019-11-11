
package com.awl.tch.brandemi.samsung;

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
 *         &lt;element name="CESerialSaleValidateResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ceSerialSaleValidateResult"
})
@XmlRootElement(name = "CESerialSaleValidateResponse")
public class CESerialSaleValidateResponse {

    @XmlElement(name = "CESerialSaleValidateResult")
    protected String ceSerialSaleValidateResult;

    /**
     * Gets the value of the ceSerialSaleValidateResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCESerialSaleValidateResult() {
        return ceSerialSaleValidateResult;
    }

    /**
     * Sets the value of the ceSerialSaleValidateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCESerialSaleValidateResult(String value) {
        this.ceSerialSaleValidateResult = value;
    }

}
