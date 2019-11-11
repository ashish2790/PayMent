package com.awl.tch.brandemi.validation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.brandemi.util.Constants;
import com.awl.tch.brandemi.util.HelperUtil;
import com.awl.tch.brandemi.util.HttpsClient;

public class NonGsmLGService implements ManufacturerService {

	private static final Logger logger = LoggerFactory.getLogger(NonGsmLGService.class);

	private static NonGsmLGService service = new NonGsmLGService();

	private NonGsmLGService(){
	}

	/**
	 * LG service for Consumer Durables Category provides block and unblock serial number service
	 * @return Single instance of MboLGService
	 */
	public static NonGsmLGService getInstance(){
		return service;
	}

	/* (non-Javadoc)
	 * @see com.awl.tch.brandemi.validation.ManufacturerService#blockSerialNumber(java.util.HashMap, java.lang.String[])
	 */
	@Override
	public String blockSerialNumber(HashMap<String, String> config, String... args) {
		// TODO Auto-generated method stub
		String  url = config.get(Constants.LG_NON_GSM_URL);
		url= url+"/BlockSrNoResult?";
		String statusCode="";
		String status="BLOCK FAILED";
		try {
			String queryString = getQueryString("1",args);
			url = url + queryString;
			String response = HttpsClient.send(url, null, null);
			if(response!=null && response.length() > 3)
			{
				status = response.substring((response.indexOf("<STATUS>")+8),response.indexOf("</STATUS>"));
				statusCode = response.substring((response.indexOf("<STATUSCODE>")+12),response.indexOf("</STATUSCODE>"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception while blockSerialNumber LG NON GSM",e);
		}
		if("2".equals(statusCode.trim()))
			return HelperUtil.generateResponse(Constants.TRUE, status);
		else
			return HelperUtil.generateResponse(Constants.FALSE, status);
	}

	/**
	 * Generates Query String for block unblock LG NON GSM URL
	 * @param flag 1 indicates block 2 as unblock
	 * @param args data from thinclient
	 * @return
	 */
	private String getQueryString(String flag, String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SR_NO=");
		sb.append(args[0]);
		sb.append("&name=");
		sb.append(Constants.LG_FROM_COMPANYNAME);
		sb.append("&model=");
		sb.append(args[1]);
		sb.append("&flag=");
		sb.append(flag);
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.awl.tch.brandemi.validation.ManufacturerService#unblockSerialNumber(java.util.HashMap, java.lang.String[])
	 */
	@Override
	public String unblockSerialNumber(HashMap<String, String> config, String... args) {
		// TODO Auto-generated method stub
		String  url = config.get(Constants.LG_NON_GSM_URL);
		url= url+"/BlockSrNoResult?";
		String statusCode="";
		String status="BLOCK FAILED";
		try {
			String queryString = getQueryString("2",args);
			url = url + queryString;
			String response = HttpsClient.send(url, null, null);
			if(response!=null && response.length() > 3)
			{
				status = response.substring((response.indexOf("<STATUS>")+8),response.indexOf("</STATUS>"));
				statusCode = response.substring((response.indexOf("<STATUSCODE>")+12),response.indexOf("</STATUSCODE>"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception while unblockSerialNumber LG NON GSM",e);
		}
		if("3".equals(statusCode.trim()))
			return HelperUtil.generateResponse(Constants.TRUE, status);
		else
			return HelperUtil.generateResponse(Constants.FALSE, status);
	}

}
