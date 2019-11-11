
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
 *         &lt;element name="UnblockSerialServiceResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "unblockSerialServiceResult"
})
@XmlRootElement(name = "UnblockSerialServiceResponse")
public class UnblockSerialServiceResponse {

    @XmlElement(name = "UnblockSerialServiceResult")
    protected String unblockSerialServiceResult;

    /**
     * Gets the value of the unblockSerialServiceResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnblockSerialServiceResult() {
        return unblockSerialServiceResult;
    }

    /**
     * Sets the value of the unblockSerialServiceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnblockSerialServiceResult(String value) {
        this.unblockSerialServiceResult = value;
    }

}
