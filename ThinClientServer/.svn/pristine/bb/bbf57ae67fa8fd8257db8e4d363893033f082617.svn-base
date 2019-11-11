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
import com.awl.tch.service.ReversalService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value="REVESL")
public class ReversalController extends AbstractController{

	private static final Logger logger = LoggerFactory.getLogger(ReversalController.class);
	
	@Autowired
	@Qualifier("paymentReversalService")
	ReversalService paymentReversalService;

	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating reversal request...");
		Payment reversalRequest = new Payment();
		try {
			try {
				reversalRequest = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				e.printStackTrace();
				return;
			}
			
			try {
				if(reversalRequest.getMenuobj() != null && ("WLT").equals(reversalRequest.getCardEntryMode())){
					reversalRequest = paymentReversalService.serviceFC(reversalRequest);
				}else{
					reversalRequest = paymentReversalService.service(reversalRequest);
				}
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting reversal response");
		
			Response.setResponseObject(responseObj,reversalRequest);
		} catch (Exception e) {
			logger.error("Exception in processing reversal request :"+e.getMessage(),e);
			e.printStackTrace();
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_R101,ErrorMaster.get(ErrorConstants.TCH_R101));
		}
	}
	
}
