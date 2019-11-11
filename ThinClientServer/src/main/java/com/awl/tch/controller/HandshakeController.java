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

/**
* <h1>Handshake</h1>
* Handshake program get the handshake request 
* and process it for response object.
* <p>
* In which request parameter must contain mandatory 
* field as <tt>Terminal serial number</tt>,
* base on which all the related information like <tt>client id</tt>,<tt>version number</tt>,
* <tt>card ranges</tt>,<tt>flags</tt>,<tt>handshake version number</tt>, is going to
* send as response object as <tt>json</tt> format.
* 
* <b>Note:</b> All the mandatory field must be there
* to get appropriate response from switch.
*
* @author  Kunal Surana
* @author  Ashish Bhavsar
* @version 1.0
* @since   2016-09-21
*/

@Controller(value="HNDSHK")
public class HandshakeController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(HandshakeController.class);

	@Autowired
	@Qualifier("handshakeService")
	private HandshakeServiceImpl handshakeService;

	/**
	   *This method take request in <tt>json</tt> format and proceed with
	   *it and then store response in response object in <tt>json</tt> format
	   *
	   * @param reqObj This is the first paramter and it contain actual request object.
	   * @param resObj  This is the second parameter and it contain response object.
	   */
	@Override
	public void process(Request reqObj,Response resObj) {

		logger.debug("In Handshake process");

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
