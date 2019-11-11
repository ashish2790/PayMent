package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.Carddetail;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.CardDetailService;
import com.awl.tch.util.JsonHelper;


@Controller(value="CRDDET")
public class CardDetailController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(CardDetailController.class);

	@Autowired
	@Qualifier("cardDetailService")
	CardDetailService cardDetailService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {
		logger.debug("Entering in card details controller");
		Carddetail cardDetailRequest = null;
		try {
		try {
			cardDetailRequest = (Carddetail) JsonHelper.getActualObject(requestObj, Carddetail.class);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| IntrospectionException e) {
			// TODO Auto-generated catch block
			logger.error("Exception from JSON to actual object in CardDetail :"+e.getMessage(),e);
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002,
					"Invalid Json Format");
			e.printStackTrace();
		}
		
		logger.info("CardDetail Request :"+cardDetailRequest.toString());
		logger.info("Service method call in cardDetails controller");
		try {
			cardDetailRequest  = cardDetailService.service(cardDetailRequest);
		} catch (TCHServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Response.setResponseObject(responseObj,cardDetailRequest);
		}catch(Exception e) {
			Response.setErrorResponseObject(responseObj, ErrorConstants.TCH_CD001,"Card detail response failure");
		}
		
	}
}
