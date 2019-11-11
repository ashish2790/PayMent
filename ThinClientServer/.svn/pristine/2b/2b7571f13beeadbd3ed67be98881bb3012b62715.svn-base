package com.awl.tch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.EnqAckDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;

@Repository("enquiryDaoImpl")
public class EnquiryDaoImpl extends GenericDaoImpl<EnqAckDTO>{

	private static Logger logger = LoggerFactory.getLogger(EnquiryDaoImpl.class);
	
	public HashMap<String,String> getUtilityInfo(String appName) throws TCHQueryException{

		logger.debug("Inside dao impl enquiry");
		
		String sql = "SELECT U_KEY, U_VALUE  FROM TCH_UTILITY_CONFIG WHERE U_UTILITY_NAME='"+appName+"'";
		
		if(Property.isShowSql()){
			logger.debug("Fetching utility info sql ["+sql+"]");
		}
		List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);
		HashMap<String,String> detail = new HashMap<String, String>();
		if(rows	!= null){
			for(Map<String,Object> row : rows){
				detail.put((String) row.get("U_KEY"), (String) row.get("U_VALUE"));
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_U001,ErrorMaster.get(ErrorConstants.TCH_U001));
		}
		logger.debug("Exiting dao impl enquiry");
		return detail;
		
	}
}
