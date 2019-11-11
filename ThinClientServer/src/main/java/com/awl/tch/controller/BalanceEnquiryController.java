package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.BalanceEnquiryService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;


@Controller("BALENQ")
public class BalanceEnquiryController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(BalanceEnquiryController.class);
	
	@Autowired
	@Qualifier("balanceEnquiryImpl")
	BalanceEnquiryService balanceEnquiryService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating balance enquiry request...");
		Payment balanceEnuiryRequest = new Payment();
		try {
			try {
				balanceEnuiryRequest = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				responseObj.setErrorPresent(true);
				logger.error("Exception in parsing" +e.getMessage());
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				e.printStackTrace();
				return;
			}
			
			try {
				balanceEnuiryRequest = balanceEnquiryService.service(balanceEnuiryRequest);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting balance enquiry response");
		
			Response.setResponseObject(responseObj,balanceEnuiryRequest);
		} catch (Exception e) {
			logger.error("Exception in processing balance enquiry :"+e.getMessage(),e);
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_B101,ErrorMaster.get(ErrorConstants.TCH_B101));
		}
	}

}
