package com.awl.tch.egras;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.types.UnsignedShort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tempuri.BankStatus_info;
import org.tempuri.NonTreasury_AccSoapProxy;
import org.tempuri.PosAppSoapProxy;
import org.tempuri.Pos_data;
import org.tempuri.UpdateBankStatusSoapProxy;

import com.egras.constant.EgrasEnumConstant;
import com.egras.entity.SaveStatus;
import com.egras.exception.EGRASServiceException;

@Component(EgrasEnumConstant.EGRAS_ADPTOR)
public class EgrasAdaptorImpl implements EgrasAdptor {
	
	static {
		load();
	}
	Calendar cal = Calendar.getInstance();
	private static Logger logger = LoggerFactory.getLogger(EgrasAdaptorImpl.class);
	@Override
	public Pos_data getGRN(String otp, String endPoint) throws RemoteException {
		/*System.setProperty("http.proxySet", "false");*/
		// TODO Auto-generated method stub
		logger.debug("Getting GRN value");
		logger.debug("OTP [" +otp+ "]");		
		PosAppSoapProxy posApp = new PosAppSoapProxy();
		posApp.setEndpoint(endPoint+EgrasEnumConstant.POSAPP_URL);
		logger.debug("URL [" +posApp.getEndpoint()+ "]");
		return posApp.validatePosApp_no(otp);
	}

	private static void load() {
		// TODO Auto-generated method stub
		listEgras.add("AMOUNT10");
		listEgras.add("AMOUNT7");
		listEgras.add("AMOUNT6");
		listEgras.add("ACCOUNT9");
		listEgras.add("AMOUNT8");
		listEgras.add("AMOUNT9");
		listEgras.add("ACCOUNT10");
		listEgras.add("ACCOUNT6");
		listEgras.add("ACCOUNT7");
		listEgras.add("ACCOUNT8");
	}

	@Override
	public BankStatus_info updateStatus(SaveStatus saveStatusObj,String endpoint) throws RemoteException, EGRASServiceException {
		
		logger.debug("Updating status");
		logger.debug("Data ["+saveStatusObj.toString()+"]");
		
		String encData = "";
		try {
			encData = saveStatusObj.getEncData();
			logger.debug("Encrypted Data ["+encData+"]");
		} catch (Exception e) {
			throw new EGRASServiceException("EG-02","ENCRYPT ERROR",null);
		}
		UpdateBankStatusSoapProxy update = new UpdateBankStatusSoapProxy();
		update.setEndpoint(endpoint+EgrasEnumConstant.SAVESTATUS_URL);
		logger.debug("URL [" +update.getEndpoint()+ "]");
		BankStatus_info bankInfo = new  BankStatus_info(saveStatusObj.getUserId(), saveStatusObj.getPassword(), saveStatusObj.getBankCode(), encData, null, null);
		return update.saveStatus(bankInfo);
	}

	@Override
	public Map<String,String> getNontrasoryInfo(String grn,String pos_txtID,String grn_Amt, String txt_datetime, char status,String endpoint) throws NumberFormatException, RemoteException {
		logger.debug("Calling non treasory service");
		NonTreasury_AccSoapProxy nontreasory = new NonTreasury_AccSoapProxy(endpoint + EgrasEnumConstant.NONTREASURY_URL);
		logger.debug("URL [" +nontreasory.getEndpoint()+ "]");
		Map<String,String> map = new HashMap<String, String>();
		
		/*try {
			cal.setTime(sdf.parse(txt_datetime));
		} catch (ParseException e) {
			logger.debug("Exception while parsing :" ,e);
		}*/
		logger.debug("Date value :" + txt_datetime);
		//get result in string array
		String[] result = nontreasory.nonTreasury_trx(grn, pos_txtID, new BigDecimal(grn_Amt).movePointLeft(2), Calendar.getInstance(), new UnsignedShort(status));
		
		//navigate and store information in map and if info not available put space
		for(String str : result){
			if(str.split("=").length > 1){
				map.put(str.split("=")[0], str.split("=")[1]);
			}else{
				map.put(str.split("=")[0], "");
			}
			
		}
		return map;
	}
	
	@Override
	public List<String> getEgrasList(){
		return listEgras;
	}
}
