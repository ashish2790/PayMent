package com.awl.tch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import com.awl.tch.aab.model.AABEntity;
import com.awl.tch.adaptor.ExternalEntityService;
import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.exceptions.TCHServiceException;
*/
public class UtilityExternalEntityHelper {

	private static Logger logger = LoggerFactory.getLogger(UtilityExternalEntityHelper.class);
	
	private UtilityExternalEntityHelper(){
		
	}
	
	/*public static void getAmount(final Payment input, ExternalEntityService<?> ees) throws TCHServiceException{
		logger.debug("Request type ->"+Constants.TCH_AAB_GET_AMOUNT);
		logger.debug("Branch code ->"+ input.getBranchCode());
		
		try {
			AABEntity aab = getAABEntity(input, Constants.TCH_AAB_GET_AMOUNT);
			ees.service(aab);
			getResult(input,aab);
			logger.debug("Invoice amount :" + input.getOriginalAmount());
		} catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_A002, ErrorMaster.get(ErrorConstants.TCH_A002));
		}
	}
	
	public static void setStatus(final Payment input, ExternalEntityService<?> ees) throws TCHServiceException{

		logger.debug("Request type ->"+Constants.TCH_AAB_SET_STATUS);
		logger.debug("Branch code ->"+ input.getBranchCode());
		
		try {
			AABEntity aab = getAABEntity(input, Constants.TCH_AAB_SET_STATUS);
			ees.service(aab);
			getResult(input,aab);
			logger.debug("Invoice amount :" + input.getOriginalAmount());
		} catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_A002, ErrorMaster.get(ErrorConstants.TCH_A002));
		}
	
	}
	
	public static void setVoidStatus(final Payment input, ExternalEntityService<?> ees) throws TCHServiceException{
		
		logger.debug("Request type ->"+Constants.TCH_AAB_VOID);
		logger.debug("Branch code ->"+ input.getBranchCode());
		try {
			AABEntity aab = getAABEntity(input, Constants.TCH_AAB_VOID);
			ees.service(aab);
			getResult(input,aab);
			logger.debug("Invoice amount :" + input.getOriginalAmount());
		} catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_A002, ErrorMaster.get(ErrorConstants.TCH_A002));
		}
	
	}
	
	public static void setVoidStatusUpdate(final Payment input, ExternalEntityService<?> ees) throws TCHServiceException{
			
			logger.debug("Request type ->"+Constants.TCH_AAB_VOID_UPDATE);
			logger.debug("Branch code ->"+ input.getBranchCode());
			try {
				AABEntity aab = getAABEntity(input, Constants.TCH_AAB_VOID_UPDATE);
				ees.service(aab);
				getResult(input,aab);
				logger.debug("Invoice amount :" + input.getOriginalAmount());
			} catch (IOException | JSONException e) {
				logger.debug("Exception :" + e.getMessage());
				throw new TCHServiceException(ErrorConstants.TCH_A002, ErrorMaster.get(ErrorConstants.TCH_A002));
			}
		
		}
	
	
	public static void setFailureStatus(final Payment input, ExternalEntityService<?> ees) throws TCHServiceException{

		logger.debug("Request type ->"+Constants.TCH_AAB_SET_STATUS);
		logger.debug("Branch code ->"+ input.getBranchCode());
		try {
			AABEntity aab = getAABEntity(input, Constants.TCH_AAB_SET_STATUS);
			ees.service(aab);
			getResult(input,aab);
			logger.debug("Invoice amount :" + input.getOriginalAmount());
		} catch (IOException | JSONException e) {
			logger.debug("Exception :" + e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_A002, ErrorMaster.get(ErrorConstants.TCH_A002));
		}
	
	
	}
	
	private static AABEntity getAABEntity(Payment input, String requestType) throws TCHServiceException{
		try{
			String refValue =  String.format("%04d", Integer.parseInt(input.getReferenceValue()));
		
			logger.debug("Reference value ->" +refValue);
			logger.debug("Retrival ref number ->" +input.getRetrivalRefNumber());
			AABEntity aab = new AABEntity();
			//for dev sbi12345
			aab.setMySId("Swx6fc9825881ef289f45acdz5645ef45dsf.3b35fvg.dg45S"); // for production  : Swx6fc9825881ef289f45acdz5645ef45dsf.3b35fvg.dg45S
			aab.setRequestType(requestType);
			aab.setTransId(refValue);
			aab.setUrl(input.getUrl());
			aab.setFromShopCode(input.getBranchCode());
			if(Constants.TCH_AAB_SET_STATUS.equals(requestType)){
				aab.setCardNumber(input.getBinNumber().substring(0,6)+"-"+input.getLastFourDigitValue());
				aab.setCardBank("0");
			}else {
				aab.setCardNumber("0");
				aab.setCardBank("0");
			}
			if(input.getRetrivalRefNumber() != null){
				aab.setAcknowledgementId(input.getRetrivalRefNumber());
			}else{
				aab.setAcknowledgementId("0");
			}
			//aab.setUrl("http://182.75.43.245/APIPay/PlaceMyOrder.asmx"); //http://182.75.43.245/APIPay/PlaceMyOrder.asmx
			aab.setXmlnsValue("http://aaberp.aabsweets.com/webservices/");
			aab.setSoapAction("http://aaberp.aabsweets.com/webservices/CardPaymentRequest");
			return aab;
		}catch(Exception e){
			logger.debug("Exception in creating AAB entity" + e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_A003, "INVALID INPUT PARAMETER");
		}
	}
	
	private static void getResult(final Payment input, AABEntity aab) throws TCHServiceException, JSONException{
		JSONArray objects = new JSONArray(aab.getResult());
		JSONObject resultObj = objects.getJSONObject(0);
		if(Constants.TCH_AAB_DONE.equals((String) resultObj.get("MyResult"))){
			logger.info("Invoice amount from service ->"+(String) resultObj.get("InvAmount"));
			Double d = new Double((String) resultObj.get("InvAmount"));
			d= d*100;
			input.setOriginalAmount(String.valueOf(d.intValue()));
		}else {
			throw new TCHServiceException(ErrorConstants.TCH_A003, (String) resultObj.get("MyResult"));
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
	}*/
	
	
}
