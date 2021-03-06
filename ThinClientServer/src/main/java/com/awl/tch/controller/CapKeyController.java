package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.CapKey;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.CapkeyService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value="CAPKEY")
public class CapKeyController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(CapKeyController.class);

	@Autowired
	@Qualifier("capkeyService")
	CapkeyService capKeyService;
	
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating capkey request...");
		CapKey capKeyRequest = new CapKey();
		try {
			try {
				capKeyRequest = (CapKey) JsonHelper.getActualObject(requestObj, CapKey.class);
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
				capKeyRequest = capKeyService.service(capKeyRequest);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting refund response");
		
			Response.setResponseObject(responseObj,capKeyRequest);
		} catch (Exception e) {
			logger.error("Exception in processing Refund request :"+e.getMessage(),e);
			e.printStackTrace();
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_R001,ErrorMaster.get(ErrorConstants.TCH_R001));
		}
	}


}
