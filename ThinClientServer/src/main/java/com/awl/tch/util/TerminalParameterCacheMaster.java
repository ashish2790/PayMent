package com.awl.tch.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.awl.tch.bean.TerminalParameter;
import com.awl.tch.server.TcpServer;

public class TerminalParameterCacheMaster {

	public static final ConcurrentHashMap<String, TerminalParameter> terminalParameterCache = new ConcurrentHashMap<String, TerminalParameter>();
	
private static Logger logger = LoggerFactory.getLogger(AppConfigMaster.class);
	
	public static void load(){/*
		ApplicationContext app = TcpServer.getContext();
		DataSource d = (DataSource) app.getBean("dataSource");
		JdbcTemplate jd = (JdbcTemplate) TcpServer.getContext().getBean("jdbcTemplate",d);
		
		String query = "select th.h_tid,th.h_card_acquiring_id,ttp.se_number,"
				+ "th.h_terminal_serial_number,ttp.dccmid,ttp.dcctid, thm.walletflag from "
				+ "tch_handshakes th join tc_term_parameter ttp on th.h_tid = ttp.TID "
				+ "join tc_tid_hwsr_mapping thm on thm.TID = ttp.TID  order by th.h_tid,ttp.se_number desc";
		
		if(Property.isShowSql()){
			logger.debug("SQL query : " + query);
		}
		try{
			List<Map<String,Object>> ls = jd.queryForList(query);
			TerminalParameter termParameter = null;
			for(Map<String , Object> map : ls){
				if(!terminalParameterCache.contains((String)map.get("h_terminal_serial_number"))){
					termParameter = new TerminalParameter();
				}
				termParameter.setTerminalSerialNumber((String)map.get("h_terminal_serial_number"));
				termParameter.setNormalTid((String) map.get("h_tid"));
				termParameter.setNormalMid((String)map.get("h_card_acquiring_id"));
				if(((BigDecimal)map.get("se_number")) != null){
					String seNumber = ((BigDecimal)map.get("se_number")).toPlainString();
					String arr[] = UtilityHelper.getMIDTIDFromSeNumber(seNumber);
					termParameter.setAmexFlag(true);
					termParameter.setAmexMid(arr[0]);
					termParameter.setAmexTid(arr[1]);
				}
				
				if(((String)map.get("walletflag")) != null && !((String)map.get("walletflag")).isEmpty()){
					termParameter.setWalletFlag(true);
				}
				
				if((String)map.get("dccmid") != null && (String)map.get("dcctid") !=null){
					termParameter.setDccFlag(true);
					termParameter.setDccMid((String)map.get("dccmid"));
					termParameter.setDccTid((String)map.get("dcctid"));
				}
				terminalParameterCache.put(termParameter.getTerminalSerialNumber(),termParameter);
			}
			logger.debug("Cache loaded successfully...");
		}
		catch(Exception e){
			logger.debug("Excetion while loading Application config details",e);
		}
		
	*/

		ApplicationContext app = TcpServer.getContext();
		DataSource d = (DataSource) app.getBean("dataSource");
		JdbcTemplate jd = (JdbcTemplate) TcpServer.getContext().getBean("jdbcTemplate",d);
		
		/*String query = "select th.h_tid,th.h_card_acquiring_id,ttp.se_number,"
				+ "th.h_terminal_serial_number,ttp.dccmid,ttp.dcctid, thm.walletflag from "
				+ "tch_handshakes th join tc_term_parameter ttp on th.h_tid = ttp.TID "
				+ "join tc_tid_hwsr_mapping thm on thm.TID = ttp.TID  order by th.h_tid,ttp.se_number desc";*/
		String query ="select distinct thm.TID,thm.MID,thm.hwsrno,thm.walletflag from  tc_tid_hwsr_mapping thm order by thm.TID";
		if(Property.isShowSql()){
			logger.debug("SQL query : " + query);
		}
		try{
			List<Map<String,Object>> ls = jd.queryForList(query);
			TerminalParameter termParameter = null;
			for(Map<String , Object> map : ls){
				if(!terminalParameterCache.contains((String)map.get("hwsrno"))){
					termParameter = new TerminalParameter();
				}
				termParameter.setTerminalSerialNumber((String)map.get("hwsrno"));
				termParameter.setNormalTid((String) map.get("TID"));
				termParameter.setNormalMid((String)map.get("MID"));
				if(((BigDecimal)map.get("se_number")) != null){
					String seNumber = ((BigDecimal)map.get("se_number")).toPlainString();
					String arr[] = UtilityHelper.getMIDTIDFromSeNumber(seNumber);
					termParameter.setAmexFlag(true);
					termParameter.setAmexMid(arr[0]);
					termParameter.setAmexTid(arr[1]);
				}
				
				if(((String)map.get("walletflag")) != null && !((String)map.get("walletflag")).isEmpty()){
					termParameter.setWalletFlag(true);
				}
				
				/*if((String)map.get("dccmid") != null && (String)map.get("dcctid") !=null){
					termParameter.setDccFlag(true);
					termParameter.setDccMid((String)map.get("dccmid"));
					termParameter.setDccTid((String)map.get("dcctid"));
				}*/
				terminalParameterCache.put(termParameter.getTerminalSerialNumber(),termParameter);
			}
			logger.debug("Cache loaded successfully...");
		}
		catch(Exception e){
			logger.debug("Excetion while loading Application config details",e);
		}
		
		
	}
	
	public static void put(String terminalSerialNumber, final TerminalParameter t){
		terminalParameterCache.put(terminalSerialNumber, t);
	}
}
