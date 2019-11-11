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

@Component("errorMaster")
public class ErrorMaster {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorMaster.class);
	
	private static ConcurrentHashMap<String, String> errorMap = new ConcurrentHashMap<String, String>();
	
	
	public static void load(){
		
		try{
		List<Map<String,Object>> errorMstr = getErrorMaster();
		
		for(Map<String,Object> row : errorMstr)
		{
			errorMap.put((String)row.get("EM_ERROR_CODE"),(String) row.get("EM_ERROR_DISPLAY_MSG"));
		}
		logger.debug("Error messages loaded successfully..");
		}catch(Exception e){
			logger.debug("Exception while loading error messages");
		}
	}
	
	private static  List<Map<String, Object>> getErrorMaster()
	    {
	    	try{
	    		ApplicationContext appContext = TcpServer.getContext();
	    		DataSource ds =  (DataSource) appContext.getBean("dataSource");
	    		JdbcTemplate jdbcTemplate = (JdbcTemplate) TcpServer.getContext().getBean("jdbcTemplate",ds);
				String query = "SELECT EM_ERROR_CODE ,  EM_ERROR_DISPLAY_MSG FROM TCH_ERROR_MASTER";
				
				if(Property.isShowSql())
				{
					logger.debug("Query :"+ query);
				}
			
				logger.debug("Fetching data for error messages");
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
				logger.debug("Data fetched");
				return rows;
			}
			catch (DataAccessException e) {
				e.printStackTrace();
				logger.error("DataAccessException while getting data for Error message:"+e,e);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("DataAccessException while getting data for Error message:"+e,e);
			}
			
			return null;	
	    }
	
	public static String get(String key)
	{
		return errorMap.get(key);
	}
	
	public static ConcurrentHashMap<String, String> getMap(){
		return errorMap;
	}
}
