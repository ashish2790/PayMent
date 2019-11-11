package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.ImagePrint;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.ImageService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value="ENQLOGO")
public class ImageController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	@Qualifier("imageService")
	ImageService imageService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Inside get image logo");
		ImagePrint imagePrint = new ImagePrint();
		try {
			try {
				imagePrint = (ImagePrint) JsonHelper.getActualObject(requestObj, ImagePrint.class);
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
				imagePrint = imageService.service(imagePrint);
			} catch (TCHServiceException e) {
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage(),e.getResponseCode(),e.getIssuerField55());
				logger.debug(e.getMessage());
				return;
			}
			logger.debug("Setting sale response");
			Response.setResponseObject(responseObj,imagePrint);
		} catch (Exception e) {
			logger.error("Exception in processing Sale request :"+e.getMessage(),e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_S001,ErrorConstants.TCH_S001);
		}
	}

}
