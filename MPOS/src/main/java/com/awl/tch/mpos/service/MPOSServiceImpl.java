	package com.awl.tch.mpos.service;


import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awl.tch.mpos.bean.EncRequestResponseObject;
import com.awl.tch.mpos.bean.MOPSBean;
import com.awl.tch.mpos.bean.MOPSResponse;
import com.awl.tch.mpos.bean.RequestResponseObject;
import com.awl.tch.mpos.bean.ResMap;
import com.awl.tch.mpos.constants.Constants;
import com.awl.tch.mpos.exception.SbiMopsException;
import com.awl.tch.mpos.utility.HttpsClient;
import com.awl.tch.mpos.utility.MOPSUtilityHelper;
import com.google.gson.Gson;
import com.mpos.helper.Property;
import com.sbi.pos.enc.POSSecurity;

@Repository(Constants.TCH_SBI_SERVICEIMPL)
public class MPOSServiceImpl implements MOPSService{

	private static Logger logger = LoggerFactory.getLogger(MPOSServiceImpl.class);
	static String key=null;
	Gson gson = new Gson();
	static {
		disableSslVerification();
		key = Property.getKey();

	}
	@Override
	public MOPSResponse getAmount(MOPSBean input, String url) throws SbiMopsException {
		logger.debug("Inside getAmount()..");
		String requestString = "";
		try{
			requestString = gson.toJson(MOPSUtilityHelper.requestMapper(input));      // json request
			logger.debug("Request from TCH before encryption : [ " + requestString  +"]");
			requestString = POSSecurity.encryptValue(requestString,key);
			logger.debug("Request from TCH after encryption : [ " + requestString  +"]");
		}catch (Exception e) {
			logger.debug("Exception Occured",e);
			throw new SbiMopsException(Constants.TCH_MP_02,"INVALID INPUT");
		}
		EncRequestResponseObject encReqObj = new EncRequestResponseObject();
		encReqObj.setEncRequestVal(requestString);
		input.setEncData(encReqObj);
		String encRequestString = gson.toJson(MOPSUtilityHelper.requestMapperResponse(input));
		logger.debug("Request in qualified JSON format : [ " + encRequestString + " ]");
		String response = sendRequest(encRequestString, url);
		logger.debug("Response : "+response);// decrypted response
		try {
			if (response != null && !response.isEmpty()) {
				RequestResponseObject encRes = getRes(response);
				ResMap resMap=encRes.getResMap();
				logger.debug(" response res : "+ resMap);
				logger.debug("Parsed response : "+ encRes);
				response = POSSecurity.decryptValue(resMap.getEncdata(),key);
			} 
		} catch (Exception e) {
			logger.debug("Exception Occured",e);
			throw new SbiMopsException(Constants.TCH_MP_04,"INVALID DATA ISSUE");
		}
		logger.debug("Response obtained : [" + response + "]");
		if (response != null && !response.isEmpty()) {
			MOPSResponse finalResponse =  getResponse(response);
			if("00".equals(finalResponse.getStatusCode()) || "F1".equals(finalResponse.getStatusCode())){
				return finalResponse;
			}else{
				throw new SbiMopsException(Constants.TCH_MP_01,finalResponse.getStatusDescription());
			}
		} 
		logger.debug("Exiting getAmount()..");
		return null;
	}
	
	@Override
	public MOPSResponse updateStatus(MOPSBean input, String url) throws SbiMopsException {
		logger.debug("Inside updateStatus()..");
		String requestString = null;
		
		try{
			requestString = gson.toJson(MOPSUtilityHelper.requestMapperUpdate(input));      // json request
			logger.debug("Request from TCH before encryption : [ " + requestString  +" ]");
			requestString = POSSecurity.encryptValue(requestString,key);
			logger.debug("Request from TCH after encryption : [ " + requestString  +"]");
		}catch (Exception e) {
			logger.debug("Exception Occured",e);
			throw new SbiMopsException(Constants.TCH_MP_02,"INVALID DATA ISSUE");
		}
		
		EncRequestResponseObject encReqObj = new EncRequestResponseObject();
		encReqObj.setEncRequestVal(requestString);
		input.setEncData(encReqObj);
		String encRequestString = gson.toJson(MOPSUtilityHelper.requestMapperResponse(input));
		logger.debug("Request in qualified JSON format : [ " + encRequestString + " ]");
		String response = sendRequest(encRequestString, url);
		logger.debug("Response : "+response);// decrypted response
		try {
			if (response != null && !response.isEmpty()) {
				RequestResponseObject encRes = getRes(response);
				ResMap resMap=encRes.getResMap();
				logger.debug(" response res : "+ resMap);
				logger.debug("Parsed response : "+ encRes);
				response = POSSecurity.decryptValue(resMap.getEncdata(),key);
			} 
		} catch (Exception e) {
			logger.debug("Exception Occured",e);
			throw new SbiMopsException(Constants.TCH_MP_04,"INVALID DATA ISSUE");
		}
		logger.debug("Response obtained : [" + response + "]");
		if (response != null && !response.isEmpty()) {
			return getResponse(response);
		} 
		logger.debug("Exiting updateStatus()..");
		return null;
	}
	
	private String sendRequest(String requestString, String url) {
		logger.debug("Sending request..");
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		return sendRequest(requestString, url,headerMap);
	}

	public String sendRequest(String requestString, String url,HashMap<String, String> map){
		return HttpsClient.send(url, requestString, map);
	}
	
	private static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (Exception e) {
			logger.debug("Exception Occured",e);
		}
	}
	
	private RequestResponseObject getRes(String response) 
	{
		return gson.fromJson(response ,RequestResponseObject.class);
	}

	private MOPSResponse getResponse(String response){
		return gson.fromJson(response, MOPSResponse.class);
	}
}
