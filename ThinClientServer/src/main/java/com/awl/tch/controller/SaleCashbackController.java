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
import com.awl.tch.service.SaleCashbackService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;
/**
* <h1>Sale</h1>
* SaleCashbackController is going to process sale + cashback transaction
* <p>
* In which request parameter must contain mandatory 
* field as <tt>Terminal serial number</tt>,<tt>Original amount</tt>
* which is going to make use for making <tt>ISO220</tt> packet
* <p>
* The request going to send to switch and after that respective 
* response code and related information going to get from switch.
* 
* <b>Note:</b> All the mandatory field must be there
* to get appropriate response from switch.
*
* @author  Ashish Bhavsar
* @version 1.0
* @since   2016-09-21
*/
@Controller(value="SLCSHBK")
public class SaleCashbackController extends AbstractController{

	private static final Logger logger  = LoggerFactory.getLogger(SaleCashbackController.class);
	
	@Autowired
	@Qualifier("saleCashBackService")
	SaleCashbackService saleCashBackService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating sale cash back request...");
		Payment cashbackReq = new Payment();
		try {
			try {
				cashbackReq = (Payment) JsonHelper.getActualObject(requestObj, Payment	.class);
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
				cashbackReq = saleCashBackService.service(cashbackReq);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage(),e.getResponseCode(),e.getIssuerField55());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting SALE CASH BACK response");
		
			Response.setResponseObject(responseObj,cashbackReq);
		} catch (Exception e) {
			logger.error("Exception in processing Refund request :"+e.getMessage(),e);
			e.printStackTrace();
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_S501,ErrorMaster.get(ErrorConstants.TCH_S501));
		}
	}
	

}
