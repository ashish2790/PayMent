package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

/**
 * UPIController :
 * Purpose : to get request from EDC and convert it to actual object 
 * 
 * @author  Pooja Patil
 * @version 1.0
 * @since   2017-06-27
*/

@Controller(value="WEBSERVICE")
public class UPIController extends AbstractController{

	private static final Logger logger = LoggerFactory.getLogger(UPIController.class);
	
	/*@Autowired
	@Qualifier("tchWebServiceCallService")
	TCHWebServiceCallServiceImpl tchWebServiceCallService;*/
	
	@Override
	public void process(Request requestObj, Response responseObj) {
		logger.debug("Inside process() of TCHWebServiceCallController...");
		logger.debug("Intiating settlement request...");
		
		Payment request = new Payment();
		String qrString = null;
		try{		
			try {
				request = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
			} catch (ClassNotFoundException | IntrospectionException
					| InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e ) {
				responseObj.setErrorPresent(true);
				logger.debug(e.getMessage());
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				e.printStackTrace();
				return;
			}
			
			logger.info("UPIQR request Request :"+request.toString());
			//request = tchWebServiceCallService.service(request);
			Response.setResponseObject(responseObj,qrString);
			
		} catch(Exception e) {
			logger.error("Exception in processing QR Code generation request :"+e.getMessage(),e);
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_S001,ErrorMaster.get(ErrorConstants.TCH_S001));
			e.printStackTrace();
			return;
		}
	}
	
}
