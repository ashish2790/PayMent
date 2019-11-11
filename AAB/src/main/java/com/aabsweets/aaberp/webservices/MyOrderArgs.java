
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
 *         &lt;element name="customer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ItemArg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DlyboyMobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DlyboyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DlyboyPassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sublocality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fulladdress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="landmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pincode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentRefno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliverydate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliverytime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustmobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnlineOrderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instruction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrdrThru_Web_Or_App" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dlymode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InvAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Others" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "customer",
    "itemArg",
    "dlyboyMobileNo",
    "dlyboyName",
    "dlyboyPassCode",
    "locality",
    "sublocality",
    "fulladdress",
    "landmark",
    "pincode",
    "emailid",
    "paymentOption",
    "paymentRefno",
    "deliverydate",
    "deliverytime",
    "custmobileNo",
    "onlineOrderNo",
    "instruction",
    "ordrThruWebOrApp",
    "dlymode",
    "invAmt",
    "others"
})
@XmlRootElement(name = "MyOrderArgs")
public class MyOrderArgs {

    @XmlElement(name = "MySId")
    protected String mySId;
    @XmlElement(name = "ToShopCode")
    protected String toShopCode;
    protected String customer;
    @XmlElement(name = "ItemArg")
    protected String itemArg;
    @XmlElement(name = "DlyboyMobileNo")
    protected String dlyboyMobileNo;
    @XmlElement(name = "DlyboyName")
    protected String dlyboyName;
    @XmlElement(name = "DlyboyPassCode")
    protected String dlyboyPassCode;
    protected String locality;
    protected String sublocality;
    protected String fulladdress;
    protected String landmark;
    protected String pincode;
    protected String emailid;
    @XmlElement(name = "PaymentOption")
    protected String paymentOption;
    @XmlElement(name = "PaymentRefno")
    protected String paymentRefno;
    protected String deliverydate;
    protected String deliverytime;
    @XmlElement(name = "CustmobileNo")
    protected String custmobileNo;
    @XmlElement(name = "OnlineOrderNo")
    protected String onlineOrderNo;
    protected String instruction;
    @XmlElement(name = "OrdrThru_Web_Or_App")
    protected String ordrThruWebOrApp;
    @XmlElement(name = "Dlymode")
    protected String dlymode;
    @XmlElement(name = "InvAmt")
    protected String invAmt;
    @XmlElement(name = "Others")
    protected String others;

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
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomer(String value) {
        this.customer = value;
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

    /**
     * Gets the value of the dlyboyMobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlyboyMobileNo() {
        return dlyboyMobileNo;
    }

    /**
     * Sets the value of the dlyboyMobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlyboyMobileNo(String value) {
        this.dlyboyMobileNo = value;
    }

    /**
     * Gets the value of the dlyboyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlyboyName() {
        return dlyboyName;
    }

    /**
     * Sets the value of the dlyboyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlyboyName(String value) {
        this.dlyboyName = value;
    }

    /**
     * Gets the value of the dlyboyPassCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlyboyPassCode() {
        return dlyboyPassCode;
    }

    /**
     * Sets the value of the dlyboyPassCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlyboyPassCode(String value) {
        this.dlyboyPassCode = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocality(String value) {
        this.locality = value;
    }

    /**
     * Gets the value of the sublocality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSublocality() {
        return sublocality;
    }

    /**
     * Sets the value of the sublocality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSublocality(String value) {
        this.sublocality = value;
    }

    /**
     * Gets the value of the fulladdress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFulladdress() {
        return fulladdress;
    }

    /**
     * Sets the value of the fulladdress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFulladdress(String value) {
        this.fulladdress = value;
    }

    /**
     * Gets the value of the landmark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLandmark() {
        return landmark;
    }

    /**
     * Sets the value of the landmark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLandmark(String value) {
        this.landmark = value;
    }

    /**
     * Gets the value of the pincode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Sets the value of the pincode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPincode(String value) {
        this.pincode = value;
    }

    /**
     * Gets the value of the emailid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * Sets the value of the emailid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailid(String value) {
        this.emailid = value;
    }

    /**
     * Gets the value of the paymentOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentOption() {
        return paymentOption;
    }

    /**
     * Sets the value of the paymentOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentOption(String value) {
        this.paymentOption = value;
    }

    /**
     * Gets the value of the paymentRefno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentRefno() {
        return paymentRefno;
    }

    /**
     * Sets the value of the paymentRefno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentRefno(String value) {
        this.paymentRefno = value;
    }

    /**
     * Gets the value of the deliverydate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverydate() {
        return deliverydate;
    }

    /**
     * Sets the value of the deliverydate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverydate(String value) {
        this.deliverydate = value;
    }

    /**
     * Gets the value of the deliverytime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverytime() {
        return deliverytime;
    }

    /**
     * Sets the value of the deliverytime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverytime(String value) {
        this.deliverytime = value;
    }

    /**
     * Gets the value of the custmobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustmobileNo() {
        return custmobileNo;
    }

    /**
     * Sets the value of the custmobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustmobileNo(String value) {
        this.custmobileNo = value;
    }

    /**
     * Gets the value of the onlineOrderNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineOrderNo() {
        return onlineOrderNo;
    }

    /**
     * Sets the value of the onlineOrderNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineOrderNo(String value) {
        this.onlineOrderNo = value;
    }

    /**
     * Gets the value of the instruction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Sets the value of the instruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstruction(String value) {
        this.instruction = value;
    }

    /**
     * Gets the value of the ordrThruWebOrApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdrThruWebOrApp() {
        return ordrThruWebOrApp;
    }

    /**
     * Sets the value of the ordrThruWebOrApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdrThruWebOrApp(String value) {
        this.ordrThruWebOrApp = value;
    }

    /**
     * Gets the value of the dlymode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlymode() {
        return dlymode;
    }

    /**
     * Sets the value of the dlymode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlymode(String value) {
        this.dlymode = value;
    }

    /**
     * Gets the value of the invAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvAmt() {
        return invAmt;
    }

    /**
     * Sets the value of the invAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvAmt(String value) {
        this.invAmt = value;
    }

    /**
     * Gets the value of the others property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthers() {
        return others;
    }

    /**
     * Sets the value of the others property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthers(String value) {
        this.others = value;
    }

}
