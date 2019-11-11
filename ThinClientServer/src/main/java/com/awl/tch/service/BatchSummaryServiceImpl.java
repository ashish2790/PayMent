package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.SummaryReport;
import com.awl.tch.dao.BatchSummaryReportDao;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;


@Service("batchSummarySerivce")
public class BatchSummaryServiceImpl extends AbstractServiceImpl implements BatchSummaryService{

	private static Logger logger = LoggerFactory.getLogger(BatchSummaryServiceImpl.class);
	
	@Autowired
	@Qualifier("batchSummaryReportDao")
	BatchSummaryReportDao batchSummaryDao;
	
	@Override
	public SummaryReport service(SummaryReport input) throws TCHServiceException {
		
		logger.info("Inside the batch summary report");
		try {
			logger.info("batch number :" + input.getBatchNumber());
			batchSummaryDao.getBatchWiseReportNew(input);
		} catch (TCHQueryException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}		
		logger.info("Exiting the batch summary report");
		return input;
	}

}
