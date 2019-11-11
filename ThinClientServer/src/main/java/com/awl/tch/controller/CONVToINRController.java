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
import com.awl.tch.service.ConToInrServiceImpl;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value=Constants.TCH_CONVERTTOINR)
public class CONVToINRController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(CONVToINRController.class);
	
	@Autowired
	@Qualifier("contoinrServiceimpl")
	ConToInrServiceImpl convInrServiceImpl;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating conv to inr");
		Payment dRequest = new Payment();
		try {
			try {
				dRequest = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
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
				dRequest = convInrServiceImpl.service(dRequest);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				logger.debug(e.getMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting details report response");
		
			Response.setResponseObject(responseObj,dRequest);
		} catch (Exception e) {
			logger.error("Exception in processing detail report request :"+e.getMessage(),e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_C001,ErrorMaster.get(ErrorConstants.TCH_C001));
		}
	}

}
