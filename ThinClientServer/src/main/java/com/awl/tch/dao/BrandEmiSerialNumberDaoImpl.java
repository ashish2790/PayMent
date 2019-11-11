package com.awl.tch.dao;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.SkuObject;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.BrandEMIDTO;
import com.awl.tch.util.Property;



@Repository("brandEmiSerialDao")
public class BrandEmiSerialNumberDaoImpl extends GenericDaoImpl<BrandEMIDTO>{
	
	private static Logger logger = LoggerFactory.getLogger(BrandEmiSerialNumberDaoImpl.class);
	
	/*
	 * Changes made by Saloni Jindal on 6-09-2017
	 */
	public SuperMenuObject getEmiSerialValidateData(final SuperMenuObject input)  throws TCHServiceException {
		logger.debug("Emi Serial Validate list");
		String query = "Select * from TCH_EMI_VALIDATE_SERIAL where B_MID = ? and B_REF_VALUE = ? ";
		
		logger.debug("B_MID [" +input.getMerchantId() + "]");
		logger.debug("B_REF_VALUE [" +input.getReferenceValue()+ "]");
		
		Object[] args={input.getMerchantId(),input.getReferenceValue()};
		if(Property.isShowSql()){
			logger.info("query : " + query);
		}
		try{
			List<Map<String, Object>> rows=getJdbcTemplate().queryForList(query,args);
			List<SkuObject> skuCodeslist = new LinkedList<SkuObject>();  
					
			if(rows != null && !rows.isEmpty())
			{
				for(Map<String, Object> row : rows)
				{
					SkuObject skuObj = new SkuObject();
					skuObj.setSkucode((String) row.get("B_SKU_CODE"));
					skuObj.setAmount(new BigDecimal((String) row.get("B_ORG_AMT")).movePointLeft(2).toPlainString());
					skuObj.setBrandName((String) row.get("B_BRAND_NAME"));
					skuObj.setProductCategory((String) row.get("B_PROUCT_CATEGORY"));
					skuObj.setCategory((String) row.get("B_CATEGORY"));
					skuObj.setSubCategory((String) row.get("B_SUB_CATEGORY"));
					skuObj.setMid((String) row.get("B_MID"));
					skuObj.setMobileNumber((String) row.get("B_MOBILE_NUMBER"));
					skuCodeslist.add(skuObj);
				}	
			}else{
				throw new TCHServiceException("SK-01","NO DATA FOUND");
			}
			input.setSkuCodes(skuCodeslist.toArray(new SkuObject[skuCodeslist.size()]));
		}catch(Exception e){
			throw new TCHServiceException("BR-15", "RECORD NOT FOUND");
		}	
		return input;	
	}

	public boolean getEmiSerialValidateDataForVoid(SuperMenuObject input) throws TCHServiceException {
		List<Map<String, Object>> rows = getEmiDetails(input);
			if(rows != null && !rows.isEmpty() )
			{
				if (rows.size()==1){
					return true;
				}else{
					logger.debug("Row size is [ " +rows.size()+ " ]");
				}
					
			}
		return false;
	}

	public List<Map<String, Object>> getEmiDetails(SuperMenuObject input) {
		logger.debug("Emi Serial Validate list");
		String query = "Select * from TCH_EMI_VALIDATE_SERIAL where B_TERM_SER_NUM = ? and B_INVOICE_NUMBER = ? ";
		
		logger.debug("B_TERM_SER_NUM [" +input.getTerminalSerialNumber() + "]");
		logger.debug("B_INVOICE_NUMBER [" +input.getInvoiceNumber()+ "]");
		
		Object[] args={input.getTerminalSerialNumber(), input.getInvoiceNumber()};
		if(Property.isShowSql()){
			logger.info("query : " + query);
		}
			List<Map<String, Object>> rows=getJdbcTemplate().queryForList(query,args);
		return rows;
	}

	public void updateEmiValidateStatus(SuperMenuObject input, String status) throws TCHQueryException {
		try{
		String query;
		logger.debug("Marking status flag");
		query = "UPDATE TCH_EMI_VALIDATE_SERIAL SET B_STATUS = ? WHERE B_TERM_SER_NUM = ? AND B_INVOICE_NUMBER = ?";
		if(Property.isShowSql()){
			logger.info(query);
		}
		logger.debug("TERM_SER_NUM [" +input.getTerminalSerialNumber() + "]");
		logger.debug("INVOICE NUMBER [" +input.getInvoiceNumber() +"]");
		getJdbcTemplate().update(query ,new Object[] {status, input.getTerminalSerialNumber(), input.getInvoiceNumber()});
		logger.debug("Status flag marked as : "+ status);
		}
		catch(Exception e){
			throw new TCHQueryException("BR-13",e.getMessage());
		}
	}
	
	/*
	 * End of changes
	 */

}



