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
import com.awl.tch.service.BatchSummaryService;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.JsonHelper;

@Controller(value=Constants.TCH_BATCHREP)
public class BatchSummaryReportController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(BatchSummaryReportController.class);

	@Autowired
	@Qualifier("batchSummarySerivce")
	BatchSummaryService batchSummary;
	
	/**
	   *This method take request in <tt>json</tt> format and proceed with
	   *it and then store response in response object in <tt>json</tt> format.
	   *
	   * @param reqObj This is the first paramter and it contain actual request object.
	   * @param resObj  This is the second parameter and it contain response object.
	   */
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Intiating batch summary report request...");
		SummaryReport summaryReport = new SummaryReport();
		try {
			try {
				summaryReport = (SummaryReport) JsonHelper.getActualObject(requestObj, SummaryReport.class);
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
				summaryReport = batchSummary.service(summaryReport);
			} catch (TCHServiceException e) {
				responseObj.setErrorPresent(true);
				Response.setErrorResponseObject(responseObj,e.getErrorCode(),e.getErrorMessage(),e.getResponseCode(),e.getIssuerField55());
				logger.debug(e.getMessage());
				return;
			}
			logger.debug("Setting sale response");
			Response.setResponseObject(responseObj,summaryReport);
		} catch (Exception e) {
			logger.error("Exception in processing Sale request :"+e.getMessage(),e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_S001,ErrorMaster.get(ErrorConstants.TCH_S001));
		}
	}

}
