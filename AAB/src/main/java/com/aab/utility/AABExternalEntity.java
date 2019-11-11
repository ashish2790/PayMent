package com.aab.utility;

import java.io.IOException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aab.constants.Constants;
import com.aab.entity.AABEntity;
import com.aab.exception.AABServiceException;
import com.aab.service.impl.AABServiceImpl;

public class AABExternalEntity {

	private static Logger logger = LoggerFactory.getLogger(AABExternalEntity.class);
	
	private AABExternalEntity(){
		
	}
	
	public static String getAmount(String branchCode,String refValue, String rrn, String url, String binNumber, String lastFourDigit) throws AABServiceException{
		logger.debug("Request type ->"+Constants.TCH_AAB_GET_AMOUNT);
		logger.debug("Branch code ->"+ branchCode);
		
		try {
			AABEntity aab = getAABEntity(refValue, rrn, url, branchCode, Constants.TCH_AAB_GET_AMOUNT, binNumber, lastFourDigit); //getAABEntity(input, Constants.TCH_AAB_GET_AMOUNT);
			AABServiceImpl.service(aab);
			return getResult(aab);
		}catch(AABServiceException e){
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException(e.getErrorCode(),e.getErrorMessage(),e.getResponseCode());
		}catch (Exception e) {
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException("A-002", "NOT VALID VALUE");
		}
	}
	
	public static String setStatus(String branchCode,String refValue, String rrn, String url, String binNumber, String lastFourDigit) throws AABServiceException{

		logger.debug("Request type ->"+Constants.TCH_AAB_SET_STATUS);
		logger.debug("Branch code ->"+ branchCode);
		
		try {
			AABEntity aab = getAABEntity(refValue, rrn, url, branchCode, Constants.TCH_AAB_SET_STATUS, binNumber, lastFourDigit);
			AABServiceImpl.service(aab);
			return getResult(aab);
		} catch(AABServiceException e){
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException(e.getErrorCode(),e.getErrorMessage(),e.getResponseCode());
		}catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException("A-002", "NOT VALID VALUE");
		}
	
	}
	
	public static String setVoidStatus(String branchCode,String refValue, String rrn, String url, String binNumber, String lastFourDigit) throws AABServiceException{
		
		logger.debug("Request type ->"+Constants.TCH_AAB_VOID);
		logger.debug("Branch code ->"+ branchCode);
		try {
			AABEntity aab = getAABEntity(refValue, rrn, url, branchCode, Constants.TCH_AAB_VOID, binNumber, lastFourDigit);
			AABServiceImpl.service(aab);
			return getResult(aab);
		}catch(AABServiceException e){
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException(e.getErrorCode(),e.getErrorMessage(),e.getResponseCode());
		} catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException("A-002", "NOT VALID VALUE");
		}
	}
	
	public static String setVoidStatusUpdate(String branchCode,String refValue, String rrn, String url, String binNumber, String lastFourDigit) throws AABServiceException{
			
			logger.debug("Request type ->"+Constants.TCH_AAB_VOID_UPDATE);
			logger.debug("Branch code ->"+ branchCode);
			try {
				AABEntity aab = getAABEntity(refValue, rrn, url, branchCode, Constants.TCH_AAB_VOID_UPDATE, binNumber, lastFourDigit);
				AABServiceImpl.service(aab);
				return getResult(aab);
			}catch(AABServiceException e){
				logger.debug("Exception :" + e.getMessage());
				throw new AABServiceException(e.getErrorCode(),e.getErrorMessage(),e.getResponseCode());
			}catch (IOException | JSONException e) {
				logger.debug("Exception :" + e.getMessage());
				throw new AABServiceException("A-002", "NOT VALID VALUE");
			}
		
		}
	
	
	public static String setFailureStatus(String branchCode,String refValue, String rrn, String url, String binNumber, String lastFourDigit) throws AABServiceException{

		logger.debug("Request type ->"+Constants.TCH_AAB_SET_STATUS);
		logger.debug("Branch code ->"+ branchCode);
		try {
			AABEntity aab = getAABEntity(refValue, rrn, url, branchCode, Constants.TCH_AAB_SET_STATUS, binNumber, lastFourDigit);
			AABServiceImpl.service(aab);
			return getResult(aab);
		}catch(AABServiceException e){
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException(e.getErrorCode(),e.getErrorMessage(),e.getResponseCode());
		}catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new AABServiceException("A-002", "NOT VALID VALUE");
		}
	
	
	}
	
	private static AABEntity getAABEntity(String refValue, String rrn, String url,String branchCode, String requestType, String binNumber, String lastFourDigit) throws AABServiceException{
		try{
			logger.debug("Reference value ->" +refValue);
			logger.debug("Retrival ref number ->" +rrn);
			AABEntity aab = new AABEntity();
			//aab.setMySId("sbi12345");
			aab.setMySId("Swx6fc9825881ef289f45acdz5645ef45dsf.3b35fvg.dg45S");
			// prod : Swx6fc9825881ef289f45acdz5645ef45dsf.3b35fvg.dg45S
			aab.setRequestType(requestType);
			aab.setTransId(refValue);
			aab.setUrl(url);
			aab.setFromShopCode(branchCode);
			if(Constants.TCH_AAB_SET_STATUS.equals(requestType)){
				aab.setCardNumber(binNumber.substring(0,6)+"-"+lastFourDigit);
				aab.setCardBank("0");
			}else {
				aab.setCardNumber("0");
				aab.setCardBank("0");
			}
			if(rrn != null){
				aab.setAcknowledgementId(rrn);
			}else{
				aab.setAcknowledgementId("0");
			}
			//aab.setUrl("http://182.75.43.245/APIPay/PlaceMyOrder.asmx"); //http://182.75.43.245/APIPay/PlaceMyOrder.asmx
			aab.setXmlnsValue("http://aaberp.aabsweets.com/webservices/");
			aab.setSoapAction("http://aaberp.aabsweets.com/webservices/CardPaymentRequest");
			return aab;
		}catch(Exception e){
			logger.debug("Exception in creating AAB entity" + e.getMessage());
			throw new AABServiceException("A-003", "Invalid input parameter");
		}
	}
	
	private static String getResult(AABEntity aab) throws AABServiceException, JSONException{
		JSONArray objects = new JSONArray(aab.getResult());
		JSONObject resultObj = objects.getJSONObject(0);
		if(Constants.TCH_AAB_DONE.equals((String) resultObj.get("MyResult"))){
			logger.info("Invoice amount from service ->"+(String) resultObj.get("InvAmount"));
			Double d = new Double((String) resultObj.get("InvAmount"));
			d= d*100;
			String amount = String.valueOf(d.intValue());
			logger.debug("Amount value : " + amount);
			return amount;
		}else {
			throw new AABServiceException("A-003", (String) resultObj.get("MyResult"));
		}
	}
	
	public static String getParameterizedXML(AABEntity aab){
		return
		"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
				"<soap:Body>\n"+
					"<CardPaymentRequest xmlns=\""+aab.getXmlnsValue() +"\">\n"+
						"<MySId>"+aab.getMySId()+"</MySId>\n"+
						"<RequestType>"+aab.getRequestType()+"</RequestType>\n"+
						"<FromShopCode>"+aab.getFromShopCode()+"</FromShopCode>\n"+
						"<TrnsId>"+aab.getTransId()+"</TrnsId>\n"+
						"<ExtTransactionID>0</ExtTransactionID>\n"+
						"<AcknowledgementId>"+aab.getAcknowledgementId()+"</AcknowledgementId>\n"+
						"<TrnDate>0</TrnDate>\n"+
						"<CardBank>"+ aab.getCardBank() +"</CardBank>\n"+
						"<CardNo>"+aab.getCardNumber()+"</CardNo>\n"+
					"</CardPaymentRequest>"+
				"</soap:Body>"+
			"</soap:Envelope>";
	}

}
