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
import com.awl.tch.service.VoidService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value="VOID")
public class VoidController extends AbstractController{

	private static final Logger logger  = LoggerFactory.getLogger(VoidController.class);
	
	@Autowired
	@Qualifier("paymentVoidService")
	VoidService paymentVoidService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating Void request...");
		Payment voidRequest = new Payment();
		try {
			try {
				voidRequest = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				logger.debug(e.getMessage());
				e.printStackTrace();
				return;
			}
			
			try {
				voidRequest = paymentVoidService.service(voidRequest);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				logger.debug(e.getMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting void response");
		
			Response.setResponseObject(responseObj,voidRequest);
		} catch (Exception e) {
			logger.error("Exception in processing Void request :"+e.getMessage(),e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_V001,ErrorMaster.get(ErrorConstants.TCH_V001));
		}
	} 

}
