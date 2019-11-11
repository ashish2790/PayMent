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
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.model.TerminalParameterDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.ISOMessages;
import com.awl.tch.validator.ISOPaymentValidator;

@Service("cashbackServiceImpl")
public class CashbackServiceImpl extends AbstractServiceImpl implements TCHService<Payment>{

	private static final Logger logger = LoggerFactory.getLogger(CashbackServiceImpl.class);
	
	@Override
	public Payment service(Payment input) throws TCHServiceException {
		
		logger.debug("Service cashback");
		
		checkForMultipleOf100(input,100); // validation to check multiple of hundred or not
		
		String orgTermSerialNumber = input.getTerminalSerialNumber().trim();
		logger.debug("Terminal serial number :["+ orgTermSerialNumber +"]" );
		
		// validate fields in request packet for length and data type only
		ISOPaymentValidator.validate(input);
		
		 // First check for PCR flag is yes or not and then proceed further for actual transaction.
		checkForPcrFlag(input);
		
		//input.setBatchNumber(UtilityHelper.leftPad(batchNumber.toString(), 6)); 
		
		List<TerminalParameterDTO> listTermParamDto;
		try {
			input.setTerminalSerialNumber(orgTermSerialNumber); // changes as per discussion with aji
			listTermParamDto = paymentSaleDao.getTerminalParameter1(input);
		} catch (TCHQueryException e) {
			logger.debug(e.getMessage());
			throw new TCHServiceException(e.getErrorCode(), ErrorMaster.get(e.getErrorCode()));
		}
		
		if(listTermParamDto != null){
			if(!ISOPaymentValidator.binSpecificParameterWithValidation(input, listTermParamDto)){
				throw new TCHServiceException(ErrorConstants.TCH_S011, ErrorMaster.get(ErrorConstants.TCH_S011)); // Card not accepted , bin value error
			}
		}
		
		if(!ISOPaymentValidator.expiryDateValidation(input)){
			throw new TCHServiceException(ErrorConstants.TCH_S012, ErrorMaster.get(ErrorConstants.TCH_S012)); // Card expired
		}
		if(!ISOPaymentValidator.nonZeroAmount(input)){
			throw new TCHServiceException(ErrorConstants.TCH_S013, ErrorMaster.get(ErrorConstants.TCH_S013)); // nonzero or Negative value check
		}
		/*//AS PER UAT 22/09/2017  CASH  BACK NOT ALLOWED FOR JAB UPI // REMOVED HANDLED AT MAGNUS END 10.10.2017
		if(input.getTableId() != null && input.getTableId().contains(Constants.TCH_T2)){
			throw new TCHServiceException(ErrorConstants.TCH_S019, ErrorMaster.get(ErrorConstants.TCH_S019));
		}*/
		// Setting card entry mode
		getCardEntryMode(input);
			
		// Setting account type
		getAccountType(input);
		/*if(input.getTrack2() != null){
			String track2 = input.getTrack2();
			if(track2.contains("D"))
				input.setTrack2(track2.replace('D', '=').replace('F',' ').trim());
		}*/
		
		logger.debug("Packet transfer to switch");
			sendToSwitch(input, Constants.TCH_REQUEST_SALECSHBK);
		logger.debug("Response from switch");
		
		//input.setCurrencyName("Rs"); // hardcoded for sprint 1
		PaymentDTO paymentDto = input.getPaymentDTO();
		paymentDto.setRequestType(Constants.TCH_REQUEST_CSHBK);
		
		if(Constants.TCH_SUCCESS_RESPONSE.equals(input.getResponseCode())){
			paymentDto.setStatus(Constants.TCH_SUCCESS);
		}else
			paymentDto.setStatus(Constants.TCH_UNSUCCESS);

		// saving response
		paymentSaleDao.save(paymentDto);
		
		// setting precision value for original payment amount
		getOriginalAmountInPrecision(input);
		getAdditionalAmountInPrecision(input);
		if(!Constants.TCH_SUCCESS_RESPONSE.equals(input.getResponseCode())){
			error82(input);
			throw new TCHServiceException(ErrorConstants.TCH_S014,  ISOMessages.getSpecificMessageBasedOnResponseCode(input.getResponseCode()),input.getResponseCode(),input.getIssuerField55()); // invalid response from magnus
		}
		logger.debug("Exiting in payment service");
		//set null values
		setNull(input);
		return input;
	}
}
