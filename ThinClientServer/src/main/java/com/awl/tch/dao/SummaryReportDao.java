package com.awl.tch.dao;

import com.awl.tch.bean.SummaryReport;
import com.awl.tch.exceptions.TCHQueryException;

public interface SummaryReportDao {

	public void getRequestWiseCountAndAmount(final SummaryReport input) throws TCHQueryException;
	
	public void getRequestWiseCountAndAmountNew(final SummaryReport input) throws TCHQueryException;
}
