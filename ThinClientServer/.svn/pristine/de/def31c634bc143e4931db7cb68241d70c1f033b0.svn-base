package com.awl.tch.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.awl.tch.server.TcpServer;

@Component("emiMaster")
public class EMIMaster {
	
	private static  ConcurrentHashMap<String, String> emiMap = new ConcurrentHashMap<String, String>();
	
	private static Logger logger = LoggerFactory.getLogger(EMIMaster.class);
		
	public static void load(){
	 
		try{
			List<Map<String,Object>> ls = EMIMaster.getEMIDetails();
			for(Map<String , Object> map : ls){
				emiMap.put((String) map.get("EMI_MSG_CODE"),(String) map.get("EMI_DISPLAY_MSG"));
			}
			logger.debug("Emi details loaded successfully..");
		}
		catch(Exception e){
			logger.debug("Excetion while loading emi details");
		}
	}
	
	private static List<Map<String,Object>> getEMIDetails(){
		try{
			ApplicationContext app = TcpServer.getContext();
			DataSource d = (DataSource) app.getBean("dataSource");
			JdbcTemplate jd = (JdbcTemplate) TcpServer.getContext().getBean("jdbcTemplate",d);
			
			String sql = "SELECT EMI_MSG_CODE,EMI_DISPLAY_MSG from TCH_EMI_DETAILS";
			
			if(Property.isShowSql()){
				logger.debug("Query for setting emi display message :" + sql);
			}
			
			List<Map<String, Object>> lstEmi = jd.queryForList(sql);
			System.out.println("EMI messages loaded....");
			return lstEmi;
			
			
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("DataAccessException while getting data for EMi message:"+e,e);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("DataAccessException while getting data for Emi message:"+e,e);
		}
		return null;
	}
	
	public static String get(String key){
		return emiMap.get(key);
	}
	
	public static ConcurrentHashMap<String, String> getMap(){
		return emiMap;
	}
}
