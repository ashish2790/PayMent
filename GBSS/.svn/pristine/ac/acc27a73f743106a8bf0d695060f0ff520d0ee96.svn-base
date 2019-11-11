package com.awl.tch.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awl.tch.encryptor.RSAEncryption;
import com.awl.tch.exception.GBSSException;
import com.awl.tch.httpsclient.HttpsClient;
import com.awl.tch.mapper.GBSSMapper;
import com.awl.tch.model.GBSSRequest;
import com.awl.tch.model.GBSSRequestFinal;
import com.awl.tch.model.GBSSResponse;
import com.awl.tch.model.GBSSResponseFinal;

@Service("GBSSServiceimpl")
public class GBSSServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(GBSSServiceImpl.class);
	public GBSSResponse getAmount(GBSSRequest request,String url) throws GBSSException{
		String requestString = GBSSMapper.intoJson(request);
		logger.debug("Request String ["+requestString+"]");
		GBSSResponse actualResponse = null;
		String encryptedString = null;
		try {
			encryptedString = RSAEncryption.encrypt(requestString);
		} catch (InvalidKeyException | InvalidKeySpecException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			logger.debug("Exception ",e);
			throw new GBSSException("GB-01","ENCRYPTION ISSUE");
		}
		logger.debug("Encrypted String " + encryptedString);
		
		GBSSRequestFinal finalRequest = new GBSSRequestFinal();
		finalRequest.setDataVal(encryptedString);
		
		String finalString = GBSSMapper.intoFinalJaso(finalRequest);
		logger.debug("Actual request send :"+ finalString);
		String responseString = send(url,finalString);
		
		GBSSResponseFinal responseFinal = GBSSMapper.fromFinalJson(responseString);
		String actualReponse = responseFinal.getDataval();
		logger.debug("Reponse encryted value :" + actualReponse);
		
		String decyptedStr;
		try {
			decyptedStr = RSAEncryption.decrypt(actualReponse);
		} catch (InvalidKeyException | InvalidKeySpecException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			logger.debug("Exception ",e);
			throw new GBSSException("GB-01","DECRYPTION ISSUE");
		}
		logger.debug("Decrypted String :" + decyptedStr); 
		
		actualResponse = GBSSMapper.fromJson(decyptedStr);
		
		logger.debug("Reponse [" + actualResponse +"]");
		return actualResponse;
	}
	
	private String send(String url, String request) {
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		return HttpsClient.send(url, request, headerMap);
	}
	
	
	public GBSSResponse updateAmount(GBSSRequest request,String url) throws GBSSException{
		String requestString = GBSSMapper.intoJson(request);
		logger.debug("Request String ["+requestString+"]");
		GBSSResponse actualResponse = null;
		try {
		String encryptedString = RSAEncryption.encrypt(requestString);
		logger.debug("Encrypted String " + encryptedString);
		
		GBSSRequestFinal finalRequest = new GBSSRequestFinal();
		finalRequest.setDataVal(encryptedString);
		
		String finalString = GBSSMapper.intoFinalJaso(finalRequest);
		logger.debug("Actual request send :"+ finalString);
		String responseString = send(url,finalString);
		
		GBSSResponseFinal responseFinal = GBSSMapper.fromFinalJson(responseString);
		String actualReponse = responseFinal.getDataval();
		logger.debug("Reponse encryted value :" + actualReponse);
		
		String decyptedStr = RSAEncryption.decrypt(actualReponse);
		logger.debug("Decrypted String :" + decyptedStr); 
		
		actualResponse = GBSSMapper.fromJson(decyptedStr);
		
		logger.debug("Reponse [" + actualResponse +"]");
		}catch (Exception e){
			logger.debug("Excpetion occured ..",e);
		}
		return actualResponse;
	}
}
