/**
 * NonTreasury_AccSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface NonTreasury_AccSoap extends java.rmi.Remote {
    public java.lang.String[] nonTreasury_trx(java.lang.String grn, java.lang.String pos_txtID, java.math.BigDecimal grn_Amt, java.util.Calendar txt_datetime, org.apache.axis.types.UnsignedShort status) throws java.rmi.RemoteException;
}
