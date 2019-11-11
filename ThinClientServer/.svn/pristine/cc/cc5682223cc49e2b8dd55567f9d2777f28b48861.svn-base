package com.awl.tch.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.HealthObject;
import com.awl.tch.bean.Settlement;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentSettleDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.TerminalParameterCacheMaster;

@Repository("settlementDao")
public class SettlementDaoImpl extends GenericDaoImpl<PaymentSettleDTO> implements SettlementDao{
	private static Logger logger = LoggerFactory.getLogger(SettlementDaoImpl.class);

	@Override
	public void getSettlementDetails(Settlement input)
			throws TCHQueryException {
		logger.debug("inside get settlement dao");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter cursorHealthObject = new SqlOutParameter("C_HEALTH_OBJECT", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_SETTELEMENT_PROC1")
		.declareParameters(inTerminalSerialNumber,outSqlCode,outAppErrorCode,outDebugPoint,cursorHealthObject)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber()); 
		
		logger.info("Calling store procedure for sale TCH_SETTELEMENT_PROC1 ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		
		if(Constants.TCH_Y.equals(input.getSettlementNew())){
			if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()) != null){
				if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isAmexFlag()	&& input.getHostId() == null){			// check condition for amex
					input.setHostId(Constants.TCH_AMEX);
					input.setDecisionFlag(Constants.TCH_SSETL);
				}else if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isDccFlag()
						&& input.getHostId().equals(Constants.TCH_AMEX)){ // check condition for dcc
					input.setHostId(Constants.TCH_DCC);
					input.setDecisionFlag(Constants.TCH_SSETL);
				}else{
					input.setDecisionFlag(Constants.TCH_END);
					input.setHostId(null);
				}
			}else{
				input.setDecisionFlag(Constants.TCH_END);
				input.setHostId(null);
			}
		}
		
		
		if(!"3AA".equals(appErrorCode)){
			if(appErrorCode != null){
				if(ErrorConstants.TCH_S888.equalsIgnoreCase(appErrorCode) &&  !Constants.TCH_END.equals(input.getDecisionFlag())){
					return ;
				}else{
					throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
				}
				
			}
			if(!"4".equalsIgnoreCase(oDebugPoint)){
				if(ErrorConstants.TCH_S888.equalsIgnoreCase(appErrorCode) &&  !Constants.TCH_END.equals(input.getDecisionFlag())){
					return ;
				}else{
					throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> healthObjectRows = (List<HashMap<String,Object>>) output.get("C_HEALTH_OBJECT");
		if(healthObjectRows != null){
			for(Map<String,Object> row : healthObjectRows){
				HealthObject h = new HealthObject();
				h.setApn((String) row.get("APN"));
				SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
				Calendar cal = Calendar.getInstance();
				Date d;
				try {
					d = sdf.parse(input.getTime());
					cal.setTime(d);
					cal.add(Calendar.MINUTE, 1);
				} catch (ParseException e) {
				}
				h.setDownLoadtime(sdf.format(cal.getTime()));				
				h.setNacVal(((BigDecimal) row.get("NAC_VAL")).toPlainString());
				h.setRtmFlag(((BigDecimal) row.get("RTM_FLAG")).toPlainString());
				h.setTmsIp((String) row.get("TMS_IP"));
				h.setTmsPort((String) row.get("TMS_PORT"));
				h.setTrnxTimeOut(((BigDecimal) row.get("TXN_TIMEOUT")).toPlainString());
				input.setHealthObject(h);
			}
		}
	}
//
	@Override
	public void doAutoSettlement() throws TCHQueryException{
		
			logger.debug("inside do autosettlement dao");
			SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
			SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
			SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
			SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);

			DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
			
			simpleJdbcCall.withProcedureName("TCH_AUTOSETTLEMENT_PROC")
			.declareParameters(inTerminalSerialNumber,outSqlCode,outAppErrorCode,outDebugPoint)//,cursorTermSerialList,cursorPaymentTxnData)
			.withoutProcedureColumnMetaDataAccess()
			.compile();
			
			logger.info("Calling store procedure for sale TCH_AUTOSETTLEMENT_PROC ");
			 
			SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER","AD"); 
			Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
			logger.debug("O_DEBUG_POINT :" + (String) output.get("O_DEBUG_POINT"));
			logger.debug("O_SQL_ERROR_CODE :" + (String) output.get("O_SQL_ERROR_CODE"));
			logger.debug("O_APP_ERROR_CODE :" + (String) output.get("O_APP_ERROR_CODE"));
			
			logger.debug("Autosettlement...");
			
		
	}
	
	@Override
	public void getSettlementDetailsNew(Settlement input) throws TCHQueryException {

		logger.debug("inside get settlement dao");
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter cursorHealthObject = new SqlOutParameter("C_HEALTH_OBJECT", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_SETTELEMENT_AMEX_PROC")
		.declareParameters(inTerminalSerialNumber,outSqlCode,outAppErrorCode,outDebugPoint,cursorHealthObject)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber()); 
		
		logger.info("Calling store procedure for sale TCH_SETTELEMENT_AMEX_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		if(!"3AA".equals(appErrorCode)){
			if(appErrorCode != null){
				throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
			}
			if(!"4".equalsIgnoreCase(oDebugPoint)){
				throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
			}
		}
		if(Constants.TCH_Y.equals(input.getSettlementNew())){
			if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()) != null){
				if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isAmexFlag()	&& input.getHostId() == null){			// check condition for amex
					input.setHostId(Constants.TCH_AMEX);
					input.setDecisionFlag(Constants.TCH_SSETL);
				}else if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isDccFlag()
						&& input.getHostId().equals(Constants.TCH_AMEX)){ // check condition for dcc
					input.setHostId(Constants.TCH_DCC);
					input.setDecisionFlag(Constants.TCH_SSETL);
				}else{
					input.setDecisionFlag(Constants.TCH_END);
					input.setHostId(null);
				}
			}else{
				input.setDecisionFlag(Constants.TCH_END);
				input.setHostId(null);
			}
		}
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> healthObjectRows = (List<HashMap<String,Object>>) output.get("C_HEALTH_OBJECT");
		if(healthObjectRows != null){
			for(Map<String,Object> row : healthObjectRows){
				HealthObject h = new HealthObject();
				h.setApn((String) row.get("APN"));
				SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
				Calendar cal = Calendar.getInstance();
				Date d;
				try {
					d = sdf.parse(input.getTime());
					cal.setTime(d);
					cal.add(Calendar.MINUTE, 1);
				} catch (ParseException e) {
				}
				h.setDownLoadtime(sdf.format(cal.getTime()));				
				h.setNacVal(((BigDecimal) row.get("NAC_VAL")).toPlainString());
				h.setRtmFlag(((BigDecimal) row.get("RTM_FLAG")).toPlainString());
				h.setTmsIp((String) row.get("TMS_IP"));
				h.setTmsPort((String) row.get("TMS_PORT"));
				h.setTrnxTimeOut(((BigDecimal) row.get("TXN_TIMEOUT")).toPlainString());
				input.setHealthObject(h);
			}
		}
	}
}
