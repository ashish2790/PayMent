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
import com.awl.tch.service.EnquiryACKServiceImpl;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller("ENQACK")
public class EnquiryACKController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnquiryController.class);
	
	@Autowired
	@Qualifier("enquiryACKServiceImpl")
	EnquiryACKServiceImpl enquiryService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating aab enq ack request request...");
		Payment aabEnqReq = new Payment();
		try {
			try {
				aabEnqReq = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				responseObj.setErrorPresent(true);
				logger.debug(e.getMessage());
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				e.printStackTrace();
				return;
			}
			
			try {
				aabEnqReq = enquiryService.service(aabEnqReq);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting aab enq  response");
		
			Response.setResponseObject(responseObj,aabEnqReq);
		} catch (Exception e) {
			logger.error("Exception in processing aab enq ack request :"+e.getMessage(),e);
			e.printStackTrace();
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_A001,ErrorMaster.get(ErrorConstants.TCH_A001));
		}
	}

}
