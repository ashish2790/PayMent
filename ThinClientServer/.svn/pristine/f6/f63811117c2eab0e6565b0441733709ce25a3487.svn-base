package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.adaptor.brandemi.AbstractBrandEmiAdaptor;
import com.awl.tch.bean.Payment;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.constant.Constants;
import com.awl.tch.dao.EnquiryDaoImpl;
import com.awl.tch.dao.ReversalDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.ISOMessages;
import com.awl.tch.wallet.common.WalletException;
import com.awl.tch.dao.WalletDaoImpl;
import com.awl.tch.wallet.fc.constant.FcConstant;

@Service("paymentReversalService")
public class ReversalServiceImpl extends AbstractServiceImpl implements ReversalService{

	private static final Logger logger = LoggerFactory.getLogger(ReversalServiceImpl.class);
	
	@Autowired
	@Qualifier("paymentReversalDao")
	ReversalDaoImpl paymentServiceDao;
	
	@Autowired
	@Qualifier("enquiryDaoImpl")
	EnquiryDaoImpl enquiryDaoImpl;
	
	@Autowired
	@Qualifier(FcConstant.TCH_FC_SERVICE_DAO)
	WalletDaoImpl fcWalletDaoImpl;
	
	@Override
	public Payment service(Payment input) throws TCHServiceException {
		
		logger.debug("Entering in payment reversal service");
		checkForPcrFlag(input);
		
		PaymentDTO paymentDTO = null;//input.getPaymentDTO();
		
		try {
			paymentDTO = paymentServiceDao.checkForActualTxn(input);
			
			if(paymentDTO.getRequestType() != null){
				if(Constants.TCH_E1.equals(input.getDecisionFlag())|| Constants.TCH_E2.equals(input.getDecisionFlag()) 
						|| Constants.TCH_17.equals(input.getDecisionFlag())){
					//including field 55 to skip as in sale reversal this field will come from terminal
					BeanUtils.copyProperties(paymentDTO, input, "track2","decisionFlag","field55","tableId" ,"cardEntryMode");
				}else{
					BeanUtils.copyProperties(paymentDTO, input, "track2","decisionFlag","tableId","cardEntryMode");
					// setting decision flag to null in case of reversal we should not sent it.
					input.setDecisionFlag(null); 
				}
			}
			input.setBatchNumber(String.format("%06d", paymentDTO.getBatchNumber()));
			input.setAuthorizationId(paymentDTO.getAuthorizationId());
			input.setLastFourDigitValue(paymentDTO.getLastFourDigitValue().toString());
			if(paymentDTO.getCurrencyCode() != null)
				input.setCurrencyCode(paymentDTO.getCurrencyCode().toString());
			input.setDccAmount(String.format("%.0f", Double.valueOf(paymentDTO.getDccAmount())));
			
			// set account type
			getAccountType(input);
			
			// get card entry mode
			getCardEntryMode(input);
			
		} catch (TCHQueryException e) {
			// set account type
			getAccountType(input);
			
			// get card entry mode
			getCardEntryMode(input);
			
			if("R-002".equals(e.getErrorCode())){
				if(input.getNoOfCount() != null && Integer.parseInt(input.getNoOfCount()) < input.getReversalCount()){
					//paymentDTO = input.getPaymentDTO();
					logger.debug("Saving data after R-002");	
					if(paymentDTO == null){
						paymentDTO = new PaymentDTO();
						paymentDTO = input.getPaymentDTO();
					}
					paymentDTO.setOriginalAmount(new Double(0));
					paymentDTO.setRequestType(Constants.TCH_REQUEST_REVERSAL);
					paymentDTO.setStatus(Constants.TCH_UNSUCCESS);
					// send reversal to magnus and get the response as it is and send success response to terminal
					sendToSwitch(input, Constants.TCH_REQUEST_REVERSAL);
					if(Constants.TCH_SUCCESS_RESPONSE.equals(input.getResponseCode())){
						paymentSaleDao.save(paymentDTO);
						setNull(input);
						return input;
					}else{
						throw new TCHServiceException("R-001",  ISOMessages.getSpecificMessageBasedOnResponseCode(input.getResponseCode()));
					}
				}
			}
			logger.debug("No transaction found for reversal");
		}
		
		if(paymentDTO == null){
			paymentDTO = new PaymentDTO();
			paymentDTO = input.getPaymentDTO();
		}
		if(input.getNoOfCount() != null && Integer.parseInt(input.getNoOfCount()) > input.getReversalCount()){
			logger.debug("Auto approval of reversal after cosecutive failure");
			paymentDTO.setOriginalAmount(new Double(0));
			paymentDTO.setRequestType(Constants.TCH_REQUEST_REVERSAL);
			paymentDTO.setStatus(Constants.TCH_UNSUCCESS);
			if(input.getPaymentId() != null){
				//setToBillingSystem(input);
				paymentSaleDao.update(paymentDTO);
			}
			else
				paymentSaleDao.save(paymentDTO);
			setNull(input);
			return input;
		}
		
		sendToSwitch(input, Constants.TCH_REQUEST_REVERSAL);
		if(Constants.TCH_SUCCESS_RESPONSE.equals(input.getResponseCode())){
			//setToBillingSystem(input);
			paymentDTO.setOriginalAmount(new Double(0));
			paymentDTO.setRequestType(Constants.TCH_REQUEST_REVERSAL);
			paymentDTO.setStatus(Constants.TCH_SUCCESS);
			
			
			try {
				//unblocking in serial number. 
				SuperMenuObject superMenu = null;
				if(AbstractBrandEmiAdaptor.brandEmiFinalCache.get(input.getTerminalSerialNumber()) != null){
					superMenu = AbstractBrandEmiAdaptor.brandEmiFinalCache.get(input.getTerminalSerialNumber());
					brandEmiAdaptor.unblockSerialNumber(superMenu);
				}else{
					superMenu = new SuperMenuObject();
					BeanUtils.copyProperties(input, superMenu);
					brandEmiAdaptor.unblockSerialNumberNonCache(superMenu);
				}
				brandEmiSerialNumberImpl.updateEmiValidateStatus(superMenu,Constants.EMI_VALIDATE_SERIAL_STATUS_REVERSAL);
				
			} catch (TCHQueryException | TCHServiceException e) {
			logger.error("Could not unblock Serial number.");
			}catch(Exception e){
				logger.error("Could not unblock Serial number.",e);
			}
			if(input.getPaymentId() != null){
				//setToBillingSystem(input);
				paymentSaleDao.update(paymentDTO);
			}
			else
				paymentSaleDao.save(paymentDTO);
		}else{
			throw new TCHServiceException("R-001",  ISOMessages.getSpecificMessageBasedOnResponseCode(input.getResponseCode()));
		}
		
		setNull(input);
			
		logger.debug("Entering in payment reversal service");
		return input;
	}
	
	@Override
	public Payment serviceFC(Payment reversalRequest) throws TCHServiceException {
		logger.debug("inside Freecharge request for reversal");
		try {
			reversalRequest.setAppName(Constants.WALLET_FREECHARGE);
			reversalRequest = fcWalletAdaptor.getReversal(reversalRequest);
		} catch (WalletException e) {
			logger.error("Error while getting reversal enquiry : " + e.getErrorMessage());
			throw new TCHServiceException(e.getErrorCode(), ErrorMaster.get(e.getErrorCode()));
		}
		reversalRequest.setBatchNumber(null);
		return reversalRequest;
	}

}
