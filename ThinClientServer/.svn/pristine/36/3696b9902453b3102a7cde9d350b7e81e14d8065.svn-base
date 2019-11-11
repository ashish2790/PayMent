package com.awl.tch.upi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.service.AbstractServiceImpl;
import com.awl.tch.service.TCHService;
import com.awl.tch.upi.dao.QRDaoImpl;

/**
 * TCHWebServiceCallServiceImpl :
 * Purpose : to get request from UPIController and get information on the basis of terminal serial number
 * for different decision flag values, response is sent to Mobile Server and corresponding response is saved into Database.
 * 
 * @author  Pooja Patil
 * @version 1.0
 * @since   2017-06-27
*/

@Service("tchWebServiceCallService")
public class TCHWebServiceCallServiceImpl extends AbstractServiceImpl implements TCHService<Payment>{

	private static final Logger logger = LoggerFactory.getLogger(TCHWebServiceCallServiceImpl.class);
	
	@Autowired
	@Qualifier("qrDao")
	QRDaoImpl tchWebServiceCallDao;
	
	/*@Autowired
	@Qualifier("upiWebServiceCallService")
	UPIWebServiceCallService upiWebServiceCallService;*/
	
	@Override
	public Payment service(Payment input) throws TCHServiceException{
		return input;/*
		logger.debug("Service UPIService");
		
		try {
			tchWebServiceCallDao.getTerminalDetails(input);
		} catch (TCHQueryException e) {
			logger.debug("Exception in fetching data :" + e.getMessage());
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
		
		if(Constants.TCH_UPI_QR.equals(input.getDecisionFlag())){
			input.setQrType("2");
			QRRequestParameters reqParams =  upiWebServiceCallService.getQRString(input.getMerchantId(),input.getTerminalId(),input.getBankCode(),input.getQrType(),input.getOriginalAmount());
			input.setQrType(reqParams.getQrDataElementsObject().getQrType());
			input.setQrString(reqParams.getQrDataElementsObject().getQrString());
			input.setBranchCode(reqParams.getQrDataElementsObject().getTrId());
			input.setRetrivalRefNumber(reqParams.getRrn());
			input.setAuthorizationId(reqParams.getAuthCd());
			try{
				tchWebServiceCallDao.saveResponse(input);
			}catch(Exception e){
				logger.debug("Exception in saving QR Response into Database");
				e.printStackTrace();
			}
		}
		
		if(Constants.TCH_UPI_QRACK.equals(input.getDecisionFlag())){
			upiWebServiceCallService.getACKString(input.getMerchantId(),input.getTerminalId(),input.getBankCode(),input.getQrType(),input.getBranchCode());
			try{
				tchWebServiceCallDao.saveResponse(input);
			}catch(Exception e){
				logger.debug("Exception in saving ACKQR Response into Database");
				e.printStackTrace();
			}
			
		}
		
		if(Constants.TCH_UPI_QRCAN.equals(input.getDecisionFlag())){
			upiWebServiceCallService.getCAN(input.getMerchantId(),input.getTerminalId(),input.getBankCode(),input.getQrType(),input.getBranchCode());
			try{
				tchWebServiceCallDao.saveResponse(input);
			}catch(Exception e){
				logger.debug("Exception in saving CANQR Response into Database");
				e.printStackTrace();
			}
		}
		logger.debug("returning response to EDC..");
		return input;
		
	*/}

}
