package org.tempuri;

public class PosAppSoapProxy implements org.tempuri.PosAppSoap {
  private String _endpoint = null;
  private org.tempuri.PosAppSoap posAppSoap = null;
  
  public PosAppSoapProxy() {
    _initPosAppSoapProxy();
  }
  
  public PosAppSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initPosAppSoapProxy();
  }
  
  private void _initPosAppSoapProxy() {
    try {
      posAppSoap = (new org.tempuri.PosAppLocator()).getPosAppSoap();
      if (posAppSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)posAppSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)posAppSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (posAppSoap != null)
      ((javax.xml.rpc.Stub)posAppSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.PosAppSoap getPosAppSoap() {
    if (posAppSoap == null)
      _initPosAppSoapProxy();
    return posAppSoap;
  }
  
  public org.tempuri.Pos_data validatePosApp_no(java.lang.String rand) throws java.rmi.RemoteException{
    if (posAppSoap == null)
      _initPosAppSoapProxy();
    return posAppSoap.validatePosApp_no(rand);
  }
  
  
}