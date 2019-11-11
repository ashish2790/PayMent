package com.awl.tch.service;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.iso8583.ISOdccSale;
import com.awl.tch.util.ErrorMaster;

/**
 * To check whether a terminal is mapped for DCC or not
 * if YES then set DCC values
 * @param input
 * @throws TCHServiceException
 * @author pooja.patil
 */
@Service(Constants.TCH_ENQUIRY_EXCHANGE_SERVICE)
public class EnquiryExchangeServiceImpl extends AbstractServiceImpl implements EnquiryExchangeService{

	private static final Logger logger  = LoggerFactory.getLogger(EnquiryExchangeServiceImpl.class);
	
	@Override
	public Payment service(Payment input) throws TCHServiceException {
		logger.debug("Entering in Enquiry exchange service");
			Map<String, String> m = getEMIDCCFlag(input.getTerminalSerialNumber().trim());
			if(!MapUtils.isEmpty(m) && m.size() > 0){
				  if(Constants.TCH_YES.equals(m.get(Constants.TCH_DCC_FLAG))){
					  logger.debug("DCCFLAG is up..");
					  ISOdccSale.setDCCValues(input);
				  }else{
					  logger.debug("This Terminal is not mapped for DCC enquiry exchange");
					  throw new TCHServiceException(ErrorConstants.TCH_EX002, ErrorMaster.get(ErrorConstants.TCH_EX002));
				  }
			}else{
				logger.debug("No terminal found with DCC");
			}
		return input;
	}

}
