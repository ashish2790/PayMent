package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.EnquiryExchangeService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value="ENQEXG")
public class DccEnquiryController extends AbstractController{

	private static final Logger logger = LoggerFactory.getLogger(DccEnquiryController.class);
	
	@Autowired
	@Qualifier(Constants.TCH_ENQUIRY_EXCHANGE_SERVICE)
	EnquiryExchangeService enquiryExchangeService;
	
	/**
	   *This method take request in <tt>json</tt> format and proceed with
	   *it and then store response in response object in <tt>json</tt> format.
	   *
	   * @param reqObj This is the first paramter and it contain actual request object.
	   * @param resObj  This is the second parameter and it contain response object.
	   */
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Initiate sale request");
		Payment enqExgRequest = new Payment();
		try {
			try {
				enqExgRequest = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				responseObj.setErrorPresent(true);
				e.printStackTrace();
				logger.debug(e.getMessage());
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_EX001,ErrorMaster.get(ErrorConstants.TCH_EX001));
				return;
			}
			logger.info("Enquiry Exchange Request :"+enqExgRequest.toString());

			try {
				enqExgRequest = enquiryExchangeService.service(enqExgRequest);
			}catch (TCHServiceException e) {
				Response.setErrorResponseObject(responseObj, e.getErrorCode(),
						e.getErrorMessage());
				return;
			}

			Response.setResponseObject(responseObj,enqExgRequest);
		}catch(Exception e)
		{
			logger.error("Exception in processing Enquiry exchange request :"+e.getMessage(),e);
			return;
		}

	}
	
}
