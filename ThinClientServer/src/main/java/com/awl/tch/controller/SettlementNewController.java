package com.awl.tch.controller;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.Settlement;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.SettelementService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(Constants.TCH_SSETL)
public class SettlementNewController  extends AbstractController{

	private static final Logger logger = LoggerFactory.getLogger(SettlementController.class);
	@Autowired
	@Qualifier("settlementServiceImpl")
	SettelementService settlementService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating settlement request...");
		Settlement settlementReq = new Settlement();
		try {
			try {
				settlementReq = (Settlement) JsonHelper.getActualObject(requestObj, Settlement.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				responseObj.setErrorPresent(true);
				logger.debug(e.getMessage());
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				e.printStackTrace();
				return;
			}
			
			try {
				settlementReq.setSettlementNew(Constants.TCH_Y);
				settlementReq = settlementService.service(settlementReq);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				logger.debug(e.getMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting settlement response");
		
			Response.setResponseObject(responseObj,settlementReq);
		} catch (Exception e) {
			logger.error("Exception in processing settlement request :"+e.getMessage(),e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_S001,ErrorMaster.get(ErrorConstants.TCH_S001));
		}
	}


}
