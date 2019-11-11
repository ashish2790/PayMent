package org.tempuri;

public class UpdateBankStatusSoapProxy implements org.tempuri.UpdateBankStatusSoap {
  private String _endpoint = null;
  private org.tempuri.UpdateBankStatusSoap updateBankStatusSoap = null;
  
  public UpdateBankStatusSoapProxy() {
    _initUpdateBankStatusSoapProxy();
  }
  
  public UpdateBankStatusSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initUpdateBankStatusSoapProxy();
  }
  
  private void _initUpdateBankStatusSoapProxy() {
    try {
      updateBankStatusSoap = (new org.tempuri.UpdateBankStatusLocator()).getUpdateBankStatusSoap();
      if (updateBankStatusSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)updateBankStatusSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)updateBankStatusSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (updateBankStatusSoap != null)
      ((javax.xml.rpc.Stub)updateBankStatusSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.UpdateBankStatusSoap getUpdateBankStatusSoap() {
    if (updateBankStatusSoap == null)
      _initUpdateBankStatusSoapProxy();
    return updateBankStatusSoap;
  }
  
  public org.tempuri.BankStatus_info saveStatus(org.tempuri.BankStatus_info objbankStatus_info) throws java.rmi.RemoteException{
    if (updateBankStatusSoap == null)
      _initUpdateBankStatusSoapProxy();
    return updateBankStatusSoap.saveStatus(objbankStatus_info);
  }
  
  
}