package com.awl.tch.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.dao.TipAdjustmentDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentAmexDTO;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.ISOMessages;

@Service("tipAdjustmentService")
public class TipAdjustmentServiceImpl extends AbstractServiceImpl implements TipAdjustmentService{

//private static final ThreadLocal<BigDecimal> ONE_HUNDRED = new ThreadLocal<BigDecimal>();
	@Autowired
	@Qualifier("tipAdjustmentDao")
	TipAdjustmentDaoImpl tipAdjustmentDao;
	
	private static final Logger logger = LoggerFactory.getLogger(TipAdjustmentServiceImpl.class);
	
	@Override
	public Payment service(Payment input) throws TCHServiceException {
		return service1(input);
		}

	public Payment service1(final Payment input) throws TCHServiceException{

		logger.debug("Entering in service Tip");
		
		checkForPcrFlag(input);
		
		PaymentDTO existingDto = new PaymentDTO();
		try {
			existingDto = tipAdjustmentDao.getSaleParameterWithSp(input);
			/*
			 * Condition to check if txn count value present in request
			 * if yes send original tip transaction which was already approved
			 * if no send for new tip transaction
			 */
			if(input.getNoOfCount() != null){
				logger.debug("Found original transaction :: tip aproved : " +existingDto.getTipApproved());
				BeanUtils.copyProperties(existingDto, input, "decisionFlag", "track2");
				input.setBatchNumber(String.format("%06d", existingDto.getBatchNumber()));
				input.setAuthorizationId(existingDto.getAuthorizationId());
				input.setLastFourDigitValue(existingDto.getLastFourDigitValue().toString());
				if(existingDto.getCurrencyCode() != null)
					input.setCurrencyCode(existingDto.getCurrencyCode().toString());
				//input.setOriginalAmount(String.valueOf(new Double(existingDto.getOriginalAmount()).longValue()));
				logger.debug(existingDto.getOriginalAmount().toString());
				int orgAmt = existingDto.getOriginalAmount().intValue();
				int addAmt = Integer.valueOf(input.getAdditionalAmount());
				String totalAmt = String.valueOf(orgAmt - addAmt);
				input.setOriginalAmount(totalAmt);
				input.setAdditionalAmount(input.getAdditionalAmount());
				input.setTotalInr(String.valueOf(orgAmt));
				// tip already approved then send default approved transaction
				if("1".equals(existingDto.getTipApproved())){
					setNull(input);
					logger.debug("Exiting from service Tip");
					return input;
				}
			}
		 } catch (TCHQueryException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		 }
		// COPY ALL DATA FROM DTO TO PAYMENT BEAN
		BeanUtils.copyProperties(existingDto, input, "decisionFlag", "track2");
		
		if(existingDto != null && (existingDto.getRequestType().equals(Constants.TCH_REQUEST_CSHBK) || existingDto.getRequestType().equals(Constants.TCH_REQUEST_SALECSHBK)))
			throw new TCHServiceException(ErrorConstants.TCH_T009, ErrorMaster.get(ErrorConstants.TCH_T009));
		
		input.setBatchNumber(String.format("%06d", existingDto.getBatchNumber()));
		input.setAuthorizationId(existingDto.getAuthorizationId());
		input.setLastFourDigitValue(existingDto.getLastFourDigitValue().toString());
		 // changes to allowed refund on full amount flag is setting explicitly
		if(existingDto.getRefundFlag() == 1)
			input.setRefundNewFlag("Y");
		if(existingDto.getCurrencyCode() != null)
			input.setCurrencyCode(existingDto.getCurrencyCode().toString());
		input.setOriginalAmount(String.valueOf(new Double(existingDto.getOriginalAmount()).longValue()));
		
		String orgAmount = input.getOriginalAmount();
		
		calculateTipPercentValidation(input);
		logger.debug("sending request to switch");
		if("1".equals(input.getTipApproved())){
			logger.debug("found original tranasction");
			logger.debug("transaction is already adjusted ");
			input.setAdditionalAmount(String.valueOf(new Double(existingDto.getAdditionalAmount()).longValue()));
			input.setTotalInr(input.getOriginalAmount());
		}else{
			sendToSwitch(input, Constants.TCH_REQUEST_TIPADJUST);
			if(!Constants.TCH_SUCCESS_RESPONSE.equals(input.getResponseCode())){
				throw new TCHServiceException(ErrorConstants.TCH_T003,  ISOMessages.getSpecificMessageBasedOnResponseCode(input.getResponseCode()));
			}else{
				// while saving 
				input.setOriginalAmount(getTotalAmount(input).toString());
				input.setTotalInr(input.getOriginalAmount());
				PaymentDTO paymentDto = input.getPaymentDTO();
				paymentDto.setStatus(Constants.TCH_SUCCESS);
				paymentDto.setTipApproved("1");
				if(Constants.TCH_CR_DB.equals(input.getHostId())){
					logger.debug("updating void ");
					tipAdjustmentDao.update(paymentDto);
				}else{
					logger.debug("updating void valus for amex sale");
					PaymentAmexDTO paymentAmexDto = new PaymentAmexDTO();
					BeanUtils.copyProperties(paymentDto, paymentAmexDto);
					paymentAmexDao.update(paymentAmexDto);
				}
				
			}
		}
		// need to send terminal original amount as it is.
		input.setOriginalAmount(orgAmount);
		
		// setting international indicator 
		input.setCardLabel(getCardLabelWithInternationalInd(input));
		getCardEntryModeReverse(input);
		// setting null values
		setNull(input);
		logger.debug("Exiting from service Tip");
		return input;
	}
	
	private Long getTotalAmount(final Payment input){
		Long oAmount = new Long(input.getOriginalAmount());
		Long aAmount = new Long(input.getAdditionalAmount());
		return (oAmount + aAmount);
		
	}
	
	private static void calculateTipPercentValidation(Payment input) throws TCHServiceException{
		
		logger.debug("Original amount :" +input.getOriginalAmount()+"| additional amount :"+input.getAdditionalAmount()+"|Tip percent :"+input.getTipPercent());
		BigDecimal ONE_HUNDRED = new BigDecimal("100");
		Long orgAmount =  new Long(input.getOriginalAmount());
		Long addAmount = new Long(input.getAdditionalAmount());
		BigDecimal tipPer = new BigDecimal(input.getTipPercent());
		
		if(tipPer.intValue() == 0){
			//AS DISCUSSED ON MEETING 23/05/2017 (KHITIJ) BY ASHISH
			throw new TCHServiceException(ErrorConstants.TCH_T005, ErrorMaster.get(ErrorConstants.TCH_T005)); 
			/*if(addAmount.compareTo(orgAmount) == 1){
				throw new TCHServiceException(ErrorConstants.TCH_T006, ErrorMaster.get(ErrorConstants.TCH_T006));
			}*/
		}
		else{
			BigDecimal tipAmount = new BigDecimal(orgAmount);
			tipAmount  = tipAmount.divide(ONE_HUNDRED).multiply(tipPer);
			if (addAmount.compareTo(tipAmount.longValue()) == 1){
				throw new TCHServiceException(ErrorConstants.TCH_T006, ErrorMaster.get(ErrorConstants.TCH_T006) + "Percent allowed " +tipPer.intValue() + "%." );
			}
		}
	}
}
