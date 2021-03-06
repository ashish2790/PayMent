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
import com.awl.tch.service.RefundService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value = "REF")
public class RefundController extends AbstractController{

	private static final Logger logger = LoggerFactory.getLogger(RefundController.class);
	
	@Autowired
	@Qualifier("paymentRefundService")
	RefundService paymentRefundService;

	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating refund request...");
		Payment refundRequest = new Payment();
		try {
			try {
				refundRequest = (Payment) JsonHelper.getActualObject(requestObj, Payment.class);
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
			if(refundRequest.getMenuobj() != null && !("WLT").equals(refundRequest.getCardEntryMode())){	
				logger.debug("Inside qr"+refundRequest.getMenuobj());
				try {
					refundRequest = paymentRefundService.serviceQR(refundRequest);
				} catch (TCHServiceException e) {
					
					responseObj.setErrorPresent(true);
					Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
					e.printStackTrace();
					return;
				}
				logger.debug("Setting refund response");
			
				Response.setResponseObject(responseObj,refundRequest);
			}else if(refundRequest.getMenuobj() != null && ("WLT").equals(refundRequest.getCardEntryMode())){//changes by Priyanka for Freecharge Wallet 
				logger.debug("Inside Wallet refund");
				try{
					refundRequest.setAppName(Constants.WALLET_FREECHARGE);
					refundRequest = paymentRefundService.serviceFc(refundRequest);
				}catch (TCHServiceException e) {
					
					responseObj.setErrorPresent(true);
					Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
					e.printStackTrace();
					return;
				}
				logger.debug("Setting refund response");
			
				Response.setResponseObject(responseObj,refundRequest);
			
			}
			else{
				logger.debug("Inside normal refund"+refundRequest.getMenuobj());
				try {
					refundRequest = paymentRefundService.service(refundRequest);
				} catch (TCHServiceException e) {
					
					responseObj.setErrorPresent(true);
					Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
					e.printStackTrace();
					return;
				}
				logger.debug("Setting refund response");
			
				Response.setResponseObject(responseObj,refundRequest);
			}
			
		} catch (Exception e) {
			logger.error("Exception in processing Refund request :"+e.getMessage(),e);
			e.printStackTrace();
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_R001,ErrorMaster.get(ErrorConstants.TCH_R001));
		}
	}
	
}
