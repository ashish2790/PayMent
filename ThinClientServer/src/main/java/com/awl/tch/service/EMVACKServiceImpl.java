package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.EMVACKBean;
import com.awl.tch.dao.EMVACKDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;

@Service("emvACKService")
public class EMVACKServiceImpl extends AbstractServiceImpl implements TCHService<EMVACKBean>{

	@Autowired
	@Qualifier("emvDaoImpl")
	EMVACKDaoImpl emvDaoImpl;
	
	private static Logger logger = LoggerFactory.getLogger(EMVACKServiceImpl.class);
	
	@Override
	public EMVACKBean service(EMVACKBean input) throws TCHServiceException {
		
		try {
			emvDaoImpl.updateSaleParameter(input);
		} catch (TCHQueryException e) {
			logger.debug("Excpetion in updating records :" );
			throw new TCHServiceException(e.getErrorMessage(),e.getErrorMessage());
		}
		
		input.setClientId(null);
		input.setField55(null);
		input.setInvoiceNumber(null);
		input.setTerminalSerialNumber(null);
		input.setRetrivalRefNumber(null);
		
		return input;
	}

}
