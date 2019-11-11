package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.dao.ConToInrDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.util.UtilityHelper;

@Service(Constants.TCH_CONVERTTOINR_SERVICE)
public class ConToInrServiceImpl extends AbstractServiceImpl implements TCHService<Payment>{

	private static Logger logger = LoggerFactory.getLogger(ConToInrServiceImpl.class);
	
	@Autowired
	@Qualifier(Constants.TCH_CONVERTTOINR_DAO)
	ConToInrDaoImpl conInrDao; 
	
	@Autowired
	@Qualifier(Constants.TCH_VOID_SERVICE)
	VoidService paymentVoidService;
	
	@Autowired
	@Qualifier(Constants.TCH_SALE_SERVICE)
	SaleService saleService;
	
	
	@Override
	public Payment service(Payment input) throws TCHServiceException {
		
		logger.debug("convert to inr service");
		String invoiceNumber = input.getInvoiceNumber();
		String refValue = input.getReferenceValue();
		String stanNumber = input.getStanNumber();
		try {
			try{
				input.setDecisionFlag(Constants.TCH_VOID_ACK);
				Payment p = paymentVoidService.service(input);
			}catch(TCHServiceException e){
				logger.debug("Void failure on original transaction");
				throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage(),e.getResponseCode());
			}
			logger.debug("VOIDED Successfully");
			
			logger.debug("Voided previous sale with invoice number : [" + refValue +"]");
			logger.debug("current invoice number " + input.getInvoiceNumber() );
			
			input.setReferenceValue(refValue);
			
			
			PaymentDTO existingDto = conInrDao.getOriginalSale(input);
			BeanUtils.copyProperties(existingDto, input, "decisionFlag", "track2");
			input.setBatchNumber(String.format("%06d", existingDto.getBatchNumber()));
			input.setLastFourDigitValue(existingDto.getLastFourDigitValue().toString());
			input.setOriginalAmount(String.valueOf(new Double(existingDto.getOriginalAmount()).longValue()));
			input.setDecisionFlag(Constants.TCH_DCCCAN);
			input.setInvoiceNumber(invoiceNumber);
			input.setStanNumber(stanNumber);
			input.setCardEntryMode(UtilityHelper.reverseCardEntryMode(input.getCardEntryMode()));
			input.setAccountType(UtilityHelper.reverseAccountNumber(input.getAccountType()));
			
			//logger.debug("account type :"+ input.getAccountType());
			logger.debug("invoice number :"+ input.getInvoiceNumber());
			logger.debug("card entry mode :"  + input.getCardEntryMode());
			
			input.setExchangeRate(null);
			input.setMarkup(null);
			input.setDccAmount(null);
			input.setCurrencyName("INR");
			input.setCurrencyCode("356");
			input.setPaymentId(null);
			input.setSaleOfflineFlag("Y");
			input.setSessionKey(Constants.TCH_CONVERTTOINR);
			saleService.service(input);
			
		} catch (TCHQueryException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
		logger.debug("exiting convert to inr service");
		return input;
	}
}
