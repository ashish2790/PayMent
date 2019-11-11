package com.awl.tch.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ExternalEntityConstants;
import com.awl.tch.model.IrctcDTO;
import com.awl.tch.util.Property;

@Repository(Constants.TCH_IRCTC_DAO)
public class IrctcDaoImpl extends GenericDaoImpl<IrctcDTO> {

	private static Logger logger = LoggerFactory.getLogger(IrctcDaoImpl.class);
	
	/**
	 * Check for already used txn id or not
	 * @param input
	 * @return
	 */
	public boolean isAreadyUsedTxnId(Payment input){
		String query = "SELECT COUNT(1) FROM TCH_IRCTC_REPORT WHERE I_CPGTXNID = ? AND I_ASSET_SR_NO = ? AND I_RRN IS NOT NULL";
		
		if(Property.isShowSql()){
			logger.debug("query ["+query+"]");
		}
		logger.debug("I_CPGTXNID ["+input.getReferenceValue()+"]");
		logger.debug("I_ASSET_SR_NO ["+input.getTerminalSerialNumber()+"]");
		Integer count = getJdbcTemplate().queryForObject(query, new Object[]{input.getReferenceValue(),input.getTerminalSerialNumber()} , Integer.class);
		System.out.println(count);
		if(count != 0)	return false;
		return true;
	}
	
	
	/**
	 * @param txnId
	 * @return
	 */
	public String getOriginalDetails(String txnId){
		
		String query  = "SELECT I_AMOUNT FROM TCH_IRCTC_REPORT WHERE I_CPGTXNID = ?";
		
		if(Property.isShowSql()){
			logger.debug(query);
		}
		logger.debug("I_CPGTXNID [" + txnId + "]");
		String amount = "";
		try{
		 amount = getJdbcTemplate().queryForObject(query, new Object[]{txnId} , String.class);
		 logger.debug("Amunt :" + amount);
		}catch(Exception e){
			logger.debug("Exception  occured while fetching details .." ,e);
		}
		return amount;
	}
	
	public String getApptxnId(String txnId){
		
		String query  = "SELECT I_APP_TXN_ID FROM TCH_IRCTC_REPORT WHERE I_CPGTXNID = ?";
		
		if(Property.isShowSql()){
			logger.debug(query);
		}
		logger.debug("I_APP_TXN_ID [" + txnId + "]");
		String appTxn = "";
		try{
			appTxn = getJdbcTemplate().queryForObject(query, new Object[]{txnId} , String.class);
		 logger.debug("APPTXNID :" + appTxn);
		}catch(Exception e){
			logger.debug("Exception  occured while fetching details .." ,e);
		}
		return appTxn;
	}
	
	
	
	/**
	 * @param dto
	 */
	public void updateInfo(IrctcDTO dto){
		logger.debug("Update record");
		
		if(ExternalEntityConstants.TCH_IRCTC_VOID.equals(dto.getReqType())){
			String query = "UPDATE TCH_IRCTC_REPORT SET I_BANK_CODE = 'SB',  I_REQ_TYPE = ?, I_MESSAGE = ? WHERE I_CPGTXNID = ?";
			if(Property.isShowSql()){
				logger.debug("query :" + query);
			}
			logger.debug("request type :"+dto.getReqType());
			logger.debug("txn id :"+dto.getCpgTxnId());
			try{
			int i = getJdbcTemplate().update(query, new Object[]{
					dto.getReqType(),
					dto.getMessage(),
					dto.getCpgTxnId()} );
			}catch(Exception e){
				logger.debug("failed in update");
				logger.debug("Exception e:" , e);
			}
		}else{
			String query = "UPDATE TCH_IRCTC_REPORT SET I_BANK_CODE = 'SB', I_INVOICE_NO = ?, I_SERVICE_PROVIDER = ?, I_RRN = ?,"
					+ " I_CARD_DIGIT = ? ,I_CARD_TYPE = ? ,I_CARD_PROVIDER = ? , I_REQ_TYPE = ? , I_APP_TXN_ID = ? , I_POSMID = ?, "
					+ "I_PINSTATUS = ? , I_AUTHCODE = ? , I_STNCODE = ?, I_DIVCODE = ? , I_CLIENT_APP_CODE = ? , I_ZONECODE = ? ,"
					+ " I_RLYTID = ? , I_BATCH_NO = ? , I_MESSAGE = ? WHERE  I_CPGTXNID = ?";
			if(Property.isShowSql()){
				logger.debug("query :" + query);
			}
			logger.debug("IRCTCDTO :"+dto);
			
			try{
			int i = getJdbcTemplate().update(query, new Object[]{
					dto.getInvoiceNo(),
					dto.getServiceProvider(),
					dto.getRrn(),
					dto.getCardigit(),
					dto.getCardType(),
					dto.getCardProvider(),
					dto.getReqType(),
					dto.getAppTxnId(),
					dto.getPosMID(),
					dto.getPinStatus(),
					dto.getAuthCode(),
					dto.getStnCode(),
					dto.getDivCode(),dto.getClientAppCode(),dto.getZoneCode(),dto.getRlyTid(),dto.getBatchNo(),dto.getMessage(),
					dto.getCpgTxnId()} );
			}catch(Exception e){
				logger.debug("failed in update");
				logger.debug("Exception e:" , e);
			}
		}
		
		logger.debug("update completed..");
		
	}
	
	public void updatepaymentDto(IrctcDTO dto){
		logger.debug("Update PaymentDTO record");
		
	
			String query = "UPDATE TCH_PAYMENT_TXN SET TCH_PAYMENT_AGT = ?, TCH_AGT_SUB_MER = ? WHERE  P_RETRIVAL_REF_NUMBER = ?";
			if(Property.isShowSql()){
				logger.debug("query :" + query);
			}
			logger.debug("IRCTCDTO :"+dto);
			
			
			String tch_agt_sub_mer = padRight(dto.getStnCode(), 6)+padRight(dto.getDivCode(),6)+padRight(dto.getZoneCode(),4)+padRight(dto.getClientAppCode(),8);
			String tch_Agt_Sub_Mer = tch_agt_sub_mer.trim();
			logger.debug("Railway tid :"+dto.getRlyTid());
			logger.debug("TCH_AGT_SUB_MER :"+tch_Agt_Sub_Mer);
			
			try{
			int i = getJdbcTemplate().update(query, new Object[]{
					dto.getRlyTid(),
					tch_Agt_Sub_Mer,
					dto.getRrn()
					} );
			}catch(Exception e){
				logger.debug("failed in update");
				logger.debug("Exception e:" , e);
			}
		
		
		logger.debug("update paymentDto completed..");
		
	}
	
	public static String padRight(String Tch_Agt_Sub_Mer, int n) {
	     return String.format("%1$-" + n + "s", Tch_Agt_Sub_Mer);  
	}
}
