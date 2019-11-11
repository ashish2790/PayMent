package com.awl.tch.brandemi.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.awl.tch.brandemi.model.ValidationParameter;
import com.awl.tch.brandemi.util.QueryConstant;

@Repository("merchantDao")
public class MerchantDaoImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantDaoImpl.class);
	
	@Autowired
	@Qualifier("jdbcTemplateBrandEmi")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("simpleJdbcCallBrandEmi")
	private SimpleJdbcCall simpleJdbcCall;
	
	
	public ValidationParameter getValidationParameter(String mid, Integer ids[])
	{
		String sql = QueryConstant.VALIDATION_QUERY;		
		List<Map<String, Object>> list = null;
		logger.debug("Query :" + sql);
			logger.debug("db parameter mid :"+mid);
			logger.debug("db parameter ids :"+Arrays.toString(ids));
			list = jdbcTemplate.queryForList(sql, mid , ids[0] , ids[1] , ids[2] );
			Map<String, Object> row = list.get(0);
			String validationType = (String) row.get("IS_VALIDATION");
			String numberOfDays = ((BigDecimal) row.get("NO_OF_DAYS")).toString();
			String velocityCount = ((BigDecimal) row.get("VELCOUNT")).toString();		
			String velocityNumberOfDays = ((BigDecimal) row.get("VELOCITY_NOOFDAYS")).toString();
			String velocityType = (String) row.get("VELOCITYCHECK");
			String velocity = (String) row.get("VELOCITY");
			boolean isVelocity = velocity!=null && "Y".equals(velocity) ? true : false;
			ValidationParameter parameter = new ValidationParameter(validationType, numberOfDays, velocityType ,  velocityCount, velocityNumberOfDays, isVelocity);
			return parameter;
	}
	
	public String getDealerCode(String mid, Integer id)
	{
		String sql = QueryConstant.DEALERCODE_QUERY;		 		
		String dealerCode = null;
		logger.debug("db parameter mid :"+mid);
		dealerCode = jdbcTemplate.queryForObject(sql , String.class, mid , id );
		return dealerCode;
	}
	
	public String[] getManufacturerDetails(String mid, Integer manufacturerId)
	{
		String sql = QueryConstant.MANUFACTURER_DETAILS; 
		List<Map<String, Object>> manufacturerDetails = null;
		
		logger.debug("db parameter mid :"+mid);
		manufacturerDetails = jdbcTemplate.queryForList(sql , mid,manufacturerId);
		String[] data = new String[manufacturerDetails.get(0).size()];
		for(Map<String, Object> row : manufacturerDetails)
		{
			data[0] = (String) row.get("MANUFACTURERCODE");
			data[1] = (String) row.get("MANUFACTURERNAME");
			data[2] = (String) row.get("MANUFACTURERDESCLAIMER");
		}
		return data;
	}
	
	
	
	
}
