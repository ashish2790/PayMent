package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.DetailReport;
import com.awl.tch.dao.DetailReportDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;

@Service("detailReportService")
public class DetailReportServiceImpl extends AbstractServiceImpl implements DetailReportService {

	private Logger logger = LoggerFactory.getLogger(DetailReportServiceImpl.class); 
	
	@Autowired
	@Qualifier("detailReportDao")
	DetailReportDaoImpl detailReportDao;
	
	@Override
	public DetailReport service(DetailReport input) throws TCHServiceException {
		
		logger.debug("Inside service of detail report");
		try {
			if(!isNewVersionPresent(input.getTerminalSerialNumber()))
				detailReportDao.getDetailReport(input);
			else
				detailReportDao.getDetailReportNew(input);
		} catch (TCHQueryException e) {
			logger.debug("Exceptyion while fetching detail report");
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
/*		input.setHeaderValue("TranType,InvoiceNumber,AuthCode,Primary A/C No,Exp Date,Amount, Card Type");
		
		String[] str = new String[3];
		str[0] = "SALE,00001,556546,5132453113144514,12/18,58.00,Mastercard";
		str[1] = "REFUND,00002,556546,5132453113144514,12/18,58.00,Mastercard";
		str[2] = "TIP,00003,556546,5132453113144514,12/18,58.00,Mastercard";
		
		input.setTransactionValue(str);
*/		logger.debug("Exiting service of detail report");
		input.setPacketNumber(null);
		return input;
	}

}
