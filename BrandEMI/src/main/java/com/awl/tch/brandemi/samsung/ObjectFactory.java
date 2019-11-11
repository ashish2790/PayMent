
package com.awl.tch.brandemi.samsung;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.awl.tch.brandemi.samsung package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CEAuthHeader_QNAME = new QName("http://107.108.6.139/EMIHHPPineLabUAT/", "CEAuthHeader");
    private final static QName _String_QNAME = new QName("http://107.108.6.139/EMIHHPPineLabUAT/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.awl.tch.brandemi.samsung
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CESerialSaleConfirmationResponse }
     * 
     */
    public CESerialSaleConfirmationResponse createCESerialSaleConfirmationResponse() {
        return new CESerialSaleConfirmationResponse();
    }

    /**
     * Create an instance of {@link CESerialSaleValidate }
     * 
     */
    public CESerialSaleValidate createCESerialSaleValidate() {
        return new CESerialSaleValidate();
    }

    /**
     * Create an instance of {@link CESerialChangePasswordResponse }
     * 
     */
    public CESerialChangePasswordResponse createCESerialChangePasswordResponse() {
        return new CESerialChangePasswordResponse();
    }

    /**
     * Create an instance of {@link CESerialSaleValidateResponse }
     * 
     */
    public CESerialSaleValidateResponse createCESerialSaleValidateResponse() {
        return new CESerialSaleValidateResponse();
    }

    /**
     * Create an instance of {@link CESerialSaleConfirmation }
     * 
     */
    public CESerialSaleConfirmation createCESerialSaleConfirmation() {
        return new CESerialSaleConfirmation();
    }

    /**
     * Create an instance of {@link CEAuthHeader }
     * 
     */
    public CEAuthHeader createCEAuthHeader() {
        return new CEAuthHeader();
    }

    /**
     * Create an instance of {@link UnblockSerialServiceResponse }
     * 
     */
    public UnblockSerialServiceResponse createUnblockSerialServiceResponse() {
        return new UnblockSerialServiceResponse();
    }

    /**
     * Create an instance of {@link CESerialChangePassword }
     * 
     */
    public CESerialChangePassword createCESerialChangePassword() {
        return new CESerialChangePassword();
    }

    /**
     * Create an instance of {@link UnblockSerialService }
     * 
     */
    public UnblockSerialService createUnblockSerialService() {
        return new UnblockSerialService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CEAuthHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://107.108.6.139/EMIHHPPineLabUAT/", name = "CEAuthHeader")
    public JAXBElement<CEAuthHeader> createCEAuthHeader(CEAuthHeader value) {
        return new JAXBElement<CEAuthHeader>(_CEAuthHeader_QNAME, CEAuthHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://107.108.6.139/EMIHHPPineLabUAT/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
