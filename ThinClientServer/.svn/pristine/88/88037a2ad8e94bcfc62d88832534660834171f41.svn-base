package com.awl.tch.dao;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;

@Repository(Constants.TCH_BRAND_EMI_DAO)
public class BrandEmiDaoImpl extends GenericDaoImpl<PaymentDTO>{

	private static Logger logger = LoggerFactory.getLogger(BrandEmiDaoImpl.class);
	
	public String getMid(String terminalSerialNumber) throws TCHQueryException{
		
		String sql = "select distinct(a.MID) from tc_tid_hwsr_mapping a where a.hwsrno= ?";
		String mid = "";
		
		logger.debug("Terminal serial number :" + terminalSerialNumber);
		if(Property.isShowSql()){
			logger.debug("SQL for getting tpdu :[" + sql +"]");
		}
		
		try {
			mid = getJdbcTemplate().queryForObject(sql, String.class, new Object[]{terminalSerialNumber});
			logger.debug("MID : " + mid);
		}catch(DataAccessException dae){
			logger.debug("Exception while fetching data :" + dae.getMessage());
			throw new TCHQueryException(ErrorConstants.TCH_S009, ErrorMaster.get(ErrorConstants.TCH_S009));
		}catch (Exception e){
			logger.debug("Exception while fetching data :" + e.getMessage());
			throw new TCHQueryException(ErrorConstants.TCH_S009, ErrorMaster.get(ErrorConstants.TCH_S009));
		}
		
		if(mid != null)
			return mid.toString();
		else 
			throw new TCHQueryException("Mid not present : "  + terminalSerialNumber);
	}
	/**
	 * Offline validation for particular product is done based on validity days present in insight
	 * @return
	 * @throws TCHQueryException 
	 */
	public Integer getNumberOfDaysWithoutValidation(final SuperMenuObject input) throws TCHQueryException{
		logger.debug("Check offline validation..");
		
		String query = "SELECT TO_CHAR(FLOOR(SYSDATE - TO_DATE(SUBSTR(B_CREATED,0,9),'dd-month-yy','NLS_DATE_LANGUAGE = American'))) as DATEDIFF "+
				"FROM TCH_EMI_VALIDATE_SERIAL WHERE B_TERM_SER_NUM = ? AND B_MID = ? AND B_STATUS = 'P' AND ROWNUM < 2 ORDER BY 1 DESC";
		if(Property.isShowSql()){
			logger.info(query);
		}
		logger.debug("term num [" +input.getTerminalSerialNumber() +"]");
		logger.debug("MERCHANT ID [" +input.getMerchantId() +"]");
		List<Map<String, Object>> rows = null;
		Integer noOfdays = 0;
		try{
			rows = getJdbcTemplate().queryForList(query,new Object[]{input.getTerminalSerialNumber(), input.getMerchantId()});
			try{
			if(rows != null){
				for(Map<String, Object> row : rows){
					logger.debug(" DATE DIFF:" + row.get("DATEDIFF"));
					noOfdays = Integer.valueOf((String) row.get("DATEDIFF"));
				}
			}
			logger.debug("No of days after [" + noOfdays + "]");
			}catch(Exception e){
				logger.debug("Exception occured :" ,e);
			}
		}catch(Exception e){
			throw new TCHQueryException("BR-13", "CONTACT TCH");
		}
		return noOfdays;
	}
	
	
	public void validationConfirmMark(final SuperMenuObject input){
		logger.debug("marking validation flag");
		String query = "UPDATE TCH_EMI_VALIDATE_SERIAL SET B_STATUS = 'S' WHERE B_TERM_SER_NUM = ? AND B_SERIAL_NUMBER = ? AND B_INVOICE_NUMBER = ?";
		if(Property.isShowSql()){
			logger.info(query);
		}
		logger.debug("TERMINAL SERIAL NUMBER [" +input.getTerminalSerialNumber() +"]");
		logger.debug("IMEI NUMBER [" +input.getSerialNumber() +"]");
		logger.debug("INVOICE NUMBER [" +input.getReferenceValue() +"]");
		getJdbcTemplate().update(query ,new Object[] {input.getTerminalSerialNumber().trim(), input.getSerialNumber(), input.getReferenceValue()});
		logger.debug("status flag marked....");
	}
	
	public Set<String> getListOfTidBasedOnMid(String mid){
		
		Set<String> lstTID = new HashSet<String>();
		
		String sql = "SELECT TID FROM TC_TID_HWSR_MAPPING WHERE MID = ?";
		if(Property.isShowSql()){
			logger.info(sql);
		}
		logger.debug("MID [" +mid +"]");
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql ,new Object[] {mid});
		if(rows != null){
			for(Map<String, Object> row : rows){
				lstTID.add((String) row.get("TID"));
			}
		}else{
			return Collections.EMPTY_SET;
		}
		return lstTID;
	}
	
	public void updateTIDforbrandEMi(String mid){
		
		String sql = "UPDATE TC_TID_HWSR_MAPPING SET updateFlag = 'Y' WHERE mid = ?";
		if(Property.isShowSql()){
			logger.info(sql);
		}
		logger.debug("MID [" +mid +"]");
		getJdbcTemplate().update(sql, new Object[] {mid});
		logger.debug("updated tid in TC_TID_HWSR_MAPPING table");
	}
	
	public boolean isUpdateAvailableForHWSR(String termSerNum){
		String sql = "select updateFlag from TC_TID_HWSR_MAPPING where hwsrno = ?";
		if(Property.isShowSql()){
			logger.info(sql);
		}
		logger.debug("TermSerNum [" +termSerNum +"]");
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, new Object[] {termSerNum});
		for(Map<String, Object> row :rows){
			String updateFlag = (String) row.get("UPDATEFLAG");
			if("Y".equalsIgnoreCase(updateFlag)){
				return true;
			}
		}
		return false;
	}
	
	public void disableUpdateFlagForHWSR(String termSerNum){
		try{
			String sql1 = "UPDATE TC_TID_HWSR_MAPPING SET updateFlag = 'N' where hwsrno = ?";
			getJdbcTemplate().update(sql1, new Object[] {termSerNum});
		}catch(Exception e){
			logger.debug("Excpetion while updating flag for TC_TID_HWSR_MAPPING..for hwsrno "+ termSerNum);
		}
	}
	
}
