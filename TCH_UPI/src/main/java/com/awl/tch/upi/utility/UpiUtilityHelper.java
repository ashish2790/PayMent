package com.awl.tch.upi.utility;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.upi.bean.QRDataElementsObject;
import com.awl.tch.upi.bean.QRRequestParameters;
import com.awl.tch.upi.constant.Constants;

public class UpiUtilityHelper {

	private static Logger logger = LoggerFactory.getLogger(UpiUtilityHelper.class);
	
	/**
	 * Set the parameter to send to web service
	 * 1. bank code 2. mid 3. tid 4. amount
	 * @param param
	 * @return
	 */
	public static QRRequestParameters requestMapper(String reqType, String... param){
		QRDataElementsObject dataElements = new QRDataElementsObject();
		QRRequestParameters reqParams = new QRRequestParameters();
		reqParams.setFromEntity(Constants.UPI_TCH);
		reqParams.setBank_code(param[0]);
		logger.debug("From Entity [" + reqParams.getFromEntity() + "]");
		logger.debug("Bank Code [" + reqParams.getBank_code() + "]");
		
		dataElements.setMid(param[1]);
		dataElements.setTid(param[2]);
		switch (reqType) {
		case Constants.UPI_CHECKSTATUS:
		case Constants.UPI_CANCELQR:
			dataElements.setPrgType(param[3]);
			dataElements.setTxnId(param[4]);
			break;
		case Constants.UPI_REFUND:
			dataElements.setRefundAmount(new BigDecimal(param[3]).movePointLeft(2).toPlainString());	// changes by ashish
			logger.debug("AMOUNT [" + dataElements.getRefundAmount() + "]");
			dataElements.setRrn(param[4]);
			dataElements.setMobileNumber(param[5]);
			dataElements.setRefundReason("SYSTEM GERNERATED REASON");
			break;
		default:
			
			dataElements.setAmount(new BigDecimal(param[3]).movePointLeft(2).toPlainString());	// changes by ashish
			logger.debug("AMOUNT [" + dataElements.getAmount() + "]");
			dataElements.setPrgType(param[4]);
			logger.debug("QR Type [" + dataElements.getQrType() + "]");
			break;
		}
		
		logger.debug("MID [" + dataElements.getMid() + "]");
		logger.debug("TID [" + dataElements.getTid() + "]");
		
		reqParams.setQrDataElementsObject(dataElements);
		return reqParams;
	}
	
}
