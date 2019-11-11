package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.EMVACKBean;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.EMVACKServiceImpl;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;


@Controller("EMVACK")
public class EMVACKController extends AbstractController{

	@Autowired
	@Qualifier("emvACKService")
	EMVACKServiceImpl emvServiceImpl;
	
	private static Logger logger = LoggerFactory.getLogger(EMVACKController.class);
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating sale request...");
		EMVACKBean request = new EMVACKBean();
		try {
			try {
				request = (EMVACKBean) JsonHelper.getActualObject(requestObj, EMVACKBean.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				responseObj.setErrorPresent(true);
				logger.debug(e.getMessage());
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002, ErrorMaster.get(ErrorConstants.TCH_Z002));
				return;
			}
			try {
				request = emvServiceImpl.service(request);
			} catch (TCHServiceException e) {
				responseObj.setErrorPresent(true);
				logger.debug(e.getMessage());
				return;
			}
			Response.setResponseObject(responseObj,request);
		} catch (Exception e) {
			logger.error("Exception in processing emv ack request :"+e.getMessage(),e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_S001,ErrorMaster.get(ErrorConstants.TCH_S001));
		}
	}

}
