
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
 *         &lt;element name="Region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Maincat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subcat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "region",
    "maincat",
    "subcat"
})
@XmlRootElement(name = "ListofItemsForDoorDly")
public class ListofItemsForDoorDly {

    @XmlElement(name = "MySId")
    protected String mySId;
    @XmlElement(name = "Region")
    protected String region;
    @XmlElement(name = "Maincat")
    protected String maincat;
    @XmlElement(name = "Subcat")
    protected String subcat;

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
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the maincat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaincat() {
        return maincat;
    }

    /**
     * Sets the value of the maincat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaincat(String value) {
        this.maincat = value;
    }

    /**
     * Gets the value of the subcat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcat() {
        return subcat;
    }

    /**
     * Sets the value of the subcat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcat(String value) {
        this.subcat = value;
    }

}
