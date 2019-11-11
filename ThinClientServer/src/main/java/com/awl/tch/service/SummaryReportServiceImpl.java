package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.SummaryReport;
import com.awl.tch.dao.SummaryReportDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;

@Service("summaryReportService")
public class SummaryReportServiceImpl extends AbstractServiceImpl implements SummaryReportService{

	private static Logger logger = LoggerFactory.getLogger(SummaryReportServiceImpl.class);
	@Autowired
	@Qualifier("summaryReportDaoImpl")
	SummaryReportDaoImpl summaryReportDao;
	
	@Override
	public SummaryReport service(SummaryReport input)
			throws TCHServiceException {
		try {
			if(!isNewVersionPresent(input.getTerminalSerialNumber()))
				summaryReportDao.getRequestWiseCountAndAmount(input);
			else
				summaryReportDao.getRequestWiseCountAndAmountNew(input);
				
		} catch (TCHQueryException e) {
			logger.debug("Exception in feching report " + e.getMessage());
			throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
		}
		return input;
	}

}
