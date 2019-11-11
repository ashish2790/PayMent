package com.awl.tch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.awl.tch.constant.Constants;
import com.awl.tch.dao.WalletDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.WalletDTO;
import com.awl.tch.wallet.fc.bean.WalletResponseBean;
import com.awl.tch.wallet.fc.constant.FcConstant;
import com.awl.tch.wallet.fc.model.FcWalletRequest;
import com.awl.tch.wallet.fc.service.FcService;


@Component(Constants.TCH_REVERSAL_CALL)
@Scope("prototype")
public class ReversalReqCall extends Thread {

	private static Logger logger = LoggerFactory.getLogger(ReversalReqCall.class);

	@Autowired
	@Qualifier(FcConstant.TCH_FC_REVERSE_SERVICE)
	protected FcService fcService;
	
	@Autowired
	@Qualifier(FcConstant.TCH_FC_SERVICE_DAO)
	protected WalletDaoImpl fcWalletDaoImpl;
	
	public ReversalReqCall() {
		
	}
	
	
	FcWalletRequest input ;
	
	public void setWalletRequest(FcWalletRequest input){
		this.input=input;
	}
	

	@Override
	public void run(){  
			try {
				logger.info("MID [ "+input.getMerchantId()+" ]");
				logger.info("TID [ "+input.getTerminalId()+" ]");
				logger.info("Invoice number [ "+input.getInvNumber()+" ]");
				logger.debug("Transaction ref number [ "+input.getTransactionRefNumber()+" ]");
				logger.debug("Auth token [ "+input.getAuthToken()+" ]");
				logger.info("Reversal Request [ "+input.toString()+" ]");
				
				WalletResponseBean response = new WalletResponseBean();
				int i = 0;
				while(i < 5){
					logger.debug("Reversal Attempt : " +(i+1));
					try{
						response = fcService.cosumeWS(input);
					}catch(Exception e){
						logger.error("Unable to connect to FreeCharge server : "+e.getMessage());
					}
					if(Constants.TCH_SUCCESS_RESPONSE.equals(response.getResponseCode())){
						break;
					}
					i++;
					
				}
				WalletDTO walletDTO = fcWalletDaoImpl.getExistingWalletDetails(input.getTransactionRefNumber());
				walletDTO.setResponseCode(response.getResponseCode());
				walletDTO.setResponseDesc(response.getResponseDesc());
				walletDTO.setServerTxnId(response.getTxnId());
				walletDTO.setReqType(Constants.TCH_REQUEST_REVERSAL);
				//update response into DTO
				logger.debug("Updating response into DTO");
				fcWalletDaoImpl.update(walletDTO);
				
			
			} catch (TCHQueryException e) {
				logger.error("Exception while getting existing Wallet transaction");
			}
			
	}
	

	public FcWalletRequest getInput() {
		return input;
	}

	public void setInput(FcWalletRequest input) {
		this.input = input;
	}
	
}
