package com.awl.tch.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.TerminalParameterDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.ISOMessages;
import com.awl.tch.validator.ISOPaymentValidator;


@Service("balanceEnquiryImpl")
public class BalanceEnquiryServiceImpl extends AbstractServiceImpl implements BalanceEnquiryService{

	private static Logger logger = LoggerFactory.getLogger(BalanceEnquiryServiceImpl.class);
	
	@Override
	public Payment service(Payment input) throws TCHServiceException {
		logger.debug("Entering in service of balance enquiry");
		
		List<TerminalParameterDTO> listTermParamDto;
		try {
			//input.setTerminalSerialNumber(orgTermSerialNumber);
			listTermParamDto = paymentSaleDao.getTerminalParameter1(input);
		} catch (TCHQueryException e) {
			logger.debug("Exception getting values for terminal parameter :" + e.getMessage());
			throw new TCHServiceException(e.getErrorCode(), ErrorMaster.get(e.getErrorCode()));
		}
		
		if(listTermParamDto != null){
			if(!ISOPaymentValidator.binSpecificParameterWithValidation(input, listTermParamDto)){
				throw new TCHServiceException(ErrorConstants.TCH_S011, ErrorMaster.get(ErrorConstants.TCH_S011)); // Card not accepted , bin value error
			}
		}
		
		if(input.getBalEnqAllowed() != null && Constants.TCH_YES.equals(input.getBalEnqAllowed())){
				
			getCardEntryMode(input);
			
			getAccountType(input);
			
		}else if(input.getBalEnqAllowed() != null && !Constants.TCH_YES.equals(input.getBalEnqAllowed())){
			throw new TCHServiceException(ErrorConstants.TCH_B002, ErrorMaster.get(ErrorConstants.TCH_B002));
		}else if(input.getBalEnqAllowed() == null){
			throw new TCHServiceException(ErrorConstants.TCH_B001, ErrorMaster.get(ErrorConstants.TCH_B001));
		}
		logger.debug("Sending request to Switch");
			sendToSwitch(input, Constants.TCH_BALANCE_ENQ);
		logger.debug("receiving request from Switch");
		
		if(!Constants.TCH_SUCCESS_RESPONSE.equals(input.getResponseCode())){
			throw new TCHServiceException(ErrorConstants.TCH_B003,  ISOMessages.getSpecificMessageBasedOnResponseCode(input.getResponseCode()));
		}
		logger.debug("Exiting in service of balance enquiry");
		
		setNull(input);
		return input;
	}

}
