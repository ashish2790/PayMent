package com.awl.tch.mpos.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.mpos.bean.EncRequestResponseObject;
import com.awl.tch.mpos.bean.MOPSBean;
import com.awl.tch.mpos.bean.MOPSRequest;

public class MOPSUtilityHelper {

		private static Logger logger = LoggerFactory.getLogger(MOPSUtilityHelper.class);
		
		public static MOPSRequest requestMapper(MOPSBean input){
			MOPSRequest reqParams = new MOPSRequest();
			reqParams.setBankRefNo(input.getBankRefNo());
			reqParams.setDateTime(input.getDateTime());
			logger.debug("Bank Ref No. [" + reqParams.getBankRefNo() + "]");
			logger.debug("DateTime [" + reqParams.getDateTime() + "]");
			return reqParams;
		}
		
		public static MOPSRequest requestMapperResponse(MOPSBean input) {
			MOPSRequest reqParams = new MOPSRequest();
			reqParams.setEncData(input.getEncData());
			return reqParams;
		}
		
		public static MOPSRequest requestMapperUpdate(MOPSBean input){
			MOPSRequest reqParams = new MOPSRequest();
			//reqParams.setOrderId(input.getOrderId());
			reqParams.setBankRefNo(input.getBankRefNo());
			reqParams.setDateTime(input.getDateTime());
			//reqParams.setTransactionStatus(input.getTransactionStatus());
			reqParams.setStatusCode(input.getStatusCode());
			reqParams.setStatusDescription(input.getStatusDescription());
			reqParams.setStatus(input.getStatus());
			reqParams.setAmount(input.getAmount());
			//logger.debug("Order ID [" + reqParams.getOrderId() + "]");
			logger.debug("Bank Ref No. [" + reqParams.getBankRefNo() + "]");
			logger.debug("DateTime [" + reqParams.getDateTime() + "]");
			//logger.debug("Transaction Status [" + reqParams.getTransactionStatus() + "]");
			logger.debug("Status description [" + reqParams.getStatusDescription() + "]");
			logger.debug("Status code  [" + reqParams.getStatusCode() + "]");
			logger.debug("Amount [" + reqParams.getAmount() + "]");
			return reqParams;
		}

}
	
