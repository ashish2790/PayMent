package org.tempuri;

public class NonTreasury_AccSoapProxy implements org.tempuri.NonTreasury_AccSoap {
  private String _endpoint = null;
  private org.tempuri.NonTreasury_AccSoap nonTreasury_AccSoap = null;
  
  public NonTreasury_AccSoapProxy() {
    _initNonTreasury_AccSoapProxy();
  }
  
  public NonTreasury_AccSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initNonTreasury_AccSoapProxy();
  }
  
  private void _initNonTreasury_AccSoapProxy() {
    try {
      nonTreasury_AccSoap = (new org.tempuri.NonTreasury_AccLocator()).getNonTreasury_AccSoap();
      if (nonTreasury_AccSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)nonTreasury_AccSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)nonTreasury_AccSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (nonTreasury_AccSoap != null)
      ((javax.xml.rpc.Stub)nonTreasury_AccSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.NonTreasury_AccSoap getNonTreasury_AccSoap() {
    if (nonTreasury_AccSoap == null)
      _initNonTreasury_AccSoapProxy();
    return nonTreasury_AccSoap;
  }
  
  public java.lang.String[] nonTreasury_trx(java.lang.String grn, java.lang.String pos_txtID, java.math.BigDecimal grn_Amt, java.util.Calendar txt_datetime, org.apache.axis.types.UnsignedShort status) throws java.rmi.RemoteException{
    if (nonTreasury_AccSoap == null)
      _initNonTreasury_AccSoapProxy();
    return nonTreasury_AccSoap.nonTreasury_trx(grn, pos_txtID, grn_Amt, txt_datetime, status);
  }
  
  
}