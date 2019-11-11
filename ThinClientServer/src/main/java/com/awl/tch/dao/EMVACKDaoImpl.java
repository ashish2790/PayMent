package com.awl.tch.dao;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.EMVACKBean;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;

@Repository("emvDaoImpl")
public class  EMVACKDaoImpl extends GenericDaoImpl<PaymentDTO>{

	private static Logger logger = LoggerFactory.getLogger(EMVACKDaoImpl.class);
	
	public void updateSaleParameter(EMVACKBean input) throws TCHQueryException{
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inRRN = new SqlParameter("I_RRN", Types.VARCHAR);
		SqlParameter inInvoiceNumber = new SqlParameter("I_INVOICE", Types.VARCHAR);
		SqlParameter inClientId = new SqlParameter("I_CLIENT_ID", Types.VARCHAR);
		SqlParameter inIssuerField = new SqlParameter("I_FIELD55", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		
		
		logger.info("I_TERMINAL_SERIAL_NUMBER ["+input.getTerminalSerialNumber()+"]");
		logger.info("I_RRN ["+input.getRetrivalRefNumber()+"]");
		logger.info("I_INVOICE ["+input.getInvoiceNumber()+"]");
		
		logger.info("I_CLIENT_ID ["+input.getClientId()+"]");
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds) ;
		
		simpleJdbcCall.withProcedureName("TCH_EMV_ACK").declareParameters(inTerminalSerialNumber,inIssuerField,inClientId,inInvoiceNumber,inRRN,outAppErrorCode,outDebugPoint,outSqlCode)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_FIELD55", input.getField55())
				.addValue("I_RRN", input.getRetrivalRefNumber())
				.addValue("I_INVOICE", input.getInvoiceNumber())
				.addValue("I_CLIENT_ID", input.getClientId()); 
		
		Map<String, Object> output =  simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		if(!"3".equals(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_EM002,"CONTACT AWL");
		}
		
		logger.debug("Exiting from emv ack dao");
		
	}
}
