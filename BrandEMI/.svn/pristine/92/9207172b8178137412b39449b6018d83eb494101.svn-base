/**
 * 
 */
package com.awl.tch.brandemi.validation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.brandemi.samsung.CEAuthHeader;
import com.awl.tch.brandemi.samsung.ValidateSerialService;
import com.awl.tch.brandemi.samsung.ValidateSerialServiceSoap;
import com.awl.tch.brandemi.util.Constants;
import com.awl.tch.brandemi.util.HelperUtil;


/**
 * SamsungService provides block and unblock serial number service
 * @author kunal.surana
 *
 */
public class SamsungService implements ManufacturerService {

	private static final Logger logger = LoggerFactory.getLogger(SamsungService.class);
	
	private static SamsungService service = new SamsungService();
	
	private SamsungService(){
	}
	
	/**
	 * SamsungService provides block and unblock serial number service
	 * @return Single instance of SamsungService
	 */
	public static SamsungService getInstance(){
		return service;
	}
	
	/* (non-Javadoc)
	 * @see com.awl.tch.brandemi.validation.ManufacturerService#blockSerialNumber(java.util.HashMap, java.lang.String[])
	 */
	@Override
	public String blockSerialNumber(HashMap<String, String> config, String... args) {
		// TODO Auto-generated method stub
		String url = config.get(Constants.URL);
		String referenceId = HelperUtil.getReferenceNumber();
		String skuCode = args[1];
		String vendor = "A";
		String saleType = "1";
		String productType ="";
		String partnerCode = "";
		String serialNumber = args[0];
		String saleDate = HelperUtil.getDate("yyyyMMdd");

		URL urlObj = null;
		try {
			logger.debug("connecting URL:"+url);
			urlObj = new  URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("Exception while connecting URL :"+url,e);
			return HelperUtil.generateResponse(Constants.FALSE, "Unable to connect brand URL");
		}
		try{
			String username = config.get(Constants.USERNAME);
			String password = config.get(Constants.PASSWORD);
			ValidateSerialService samsungService  = new ValidateSerialService(urlObj);
			CEAuthHeader auth = new CEAuthHeader();
			auth.setUsername(username);
			auth.setPassword(password);
			ValidateSerialServiceSoap validateService = samsungService.getValidateSerialServiceSoap();
			logger.debug("Block Request Parameters-------");
			logger.debug("referenceId :"+referenceId);
			logger.debug("serialNumber :"+serialNumber);
			logger.debug("saleDate :"+saleDate);
			logger.debug("skuCode :"+skuCode);
			logger.debug("vendor :"+vendor);
			logger.debug("saleType :"+saleType);
			logger.debug("productType :"+productType);
			logger.debug("partnerCode :"+partnerCode);
			
			String response = validateService.ceSerialSaleConfirmation(referenceId, serialNumber, saleDate,skuCode,vendor, saleType, productType,partnerCode,auth);
			logger.info("blockSerialNumber response:"+response);
			return response;
		}catch(Exception e)
		{
			logger.error("Exception while sending data to URL",e);
			return HelperUtil.generateResponse(Constants.FALSE, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.awl.tch.brandemi.validation.ManufacturerService#unblockSerialNumber(java.util.HashMap, java.lang.String[])
	 */
	@Override
	public String unblockSerialNumber(HashMap<String, String> config, String... args) {
		// TODO Auto-generated method stub
		String url = config.get(Constants.URL);
		String serialNumber = args[0];
		URL urlObj = null;
		try {
			urlObj = new  URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("Exception while connecting URL :"+url,e);
			return HelperUtil.generateResponse(Constants.FALSE, "Unable to connect brand URL");
		}
		try{
			ValidateSerialService samsungService  = new ValidateSerialService(urlObj);
			CEAuthHeader auth = new CEAuthHeader();
			auth.setUsername(config.get(Constants.USERNAME));
			auth.setPassword(config.get(Constants.PASSWORD));
			ValidateSerialServiceSoap validateService = samsungService.getValidateSerialServiceSoap();
			logger.debug("UnBlock Request Parameters-------");
			logger.debug("serialNumber :"+serialNumber);
			String response  = validateService.unblockSerialService(serialNumber, auth);
			logger.info("unblockSerialNumber response:"+response);
			return response;
		}catch(Exception e)
		{
			logger.error("Exception while sending data to URL",e);
			return HelperUtil.generateResponse(Constants.FALSE, e.getMessage());
		}
	}

}
