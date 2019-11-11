package com.awl.tch.dao;

import com.awl.tch.bean.SummaryReport;
import com.awl.tch.exceptions.TCHQueryException;

public interface BatchSummaryReportDao extends GenericDao<SummaryReport>{

	public void getBatchWiseReport(SummaryReport input) throws TCHQueryException;
	
	public void getBatchWiseReportNew(SummaryReport input) throws TCHQueryException;
	
}
