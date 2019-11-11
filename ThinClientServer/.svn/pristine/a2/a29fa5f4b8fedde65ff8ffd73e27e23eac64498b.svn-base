package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.SummaryReport;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.SummaryReportService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value=Constants.TCH_SUMMARY_REPORT)
public class SummaryReportController extends AbstractController{

	private static final Logger logger = LoggerFactory.getLogger(SummaryReportController.class);
	
	@Autowired
	@Qualifier("summaryReportService")
	SummaryReportService summaryRepoService;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating summary report request...");
		SummaryReport summRepReq = new SummaryReport();
		try {
			try {
				summRepReq = (SummaryReport) JsonHelper.getActualObject(requestObj, SummaryReport.class);
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
				summRepReq = summaryRepoService.service(summRepReq);
			} catch (TCHServiceException e) {
				
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage());
				e.printStackTrace();
				return;
			}
			logger.debug("Setting summary report response");
		
			Response.setResponseObject(responseObj,summRepReq);
		} catch (Exception e) {
			logger.error("Exception in processing summary report request :"+e.getMessage(),e);
			e.printStackTrace();
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_R201,ErrorMaster.get(ErrorConstants.TCH_R201));
		}
	}
	

}
