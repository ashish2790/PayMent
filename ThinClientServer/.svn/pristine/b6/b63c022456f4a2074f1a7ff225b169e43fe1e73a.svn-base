package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.Handshake;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.HandshakeServiceImpl;
import com.awl.tch.util.JsonHelper;

@Controller(value = "KEYEXG")
public class KeyExchangeController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(KeyExchangeController.class);
	
	@Autowired
	@Qualifier("handshakeService")
	private HandshakeServiceImpl handshakeService;
	
	@Override
	public void process(Request reqObj, Response resObj) {

		logger.debug("In key exchange process");

		Handshake hskRequest = null;
		try{
			try {
				hskRequest = (Handshake) JsonHelper.getActualObject(reqObj, Handshake.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e ) {
				// TODO Auto-generated catch block
				logger.error("Exception in getting Actual Handshake object from JSON :"+e.getMessage(),e);
				Response.setErrorResponseObject(resObj,ErrorConstants.TCH_Z002,
						"Invalid Json Format");
				e.printStackTrace();

				return;
			}
			logger.info("Handshake Request :"+hskRequest.toString());

			try {
				hskRequest.setKeyExchange("Y");
				hskRequest = handshakeService.service(hskRequest);
			}catch (TCHServiceException e) {
				logger.error(hskRequest.getTerminalSerialNumber() +": Exception while servicing handshake request :"+e.getMessage(),e);
				Response.setErrorResponseObject(resObj, e.getErrorCode(),
						e.getErrorMessage());
				return;
			}

			Response.setResponseObject(resObj,hskRequest);
		}catch(Exception e)
		{
			logger.error("Exception in processing Handshake request :"+e.getMessage(),e);
			Response.setErrorResponseObject(resObj,ErrorConstants.TCH_H001,
					"Handshake Failure");
			e.printStackTrace();

			return;
		}

	}

}
