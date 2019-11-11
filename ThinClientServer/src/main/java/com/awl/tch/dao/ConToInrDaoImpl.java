package com.awl.tch.dao;

import java.sql.Types;
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

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.UtilityHelper;

@Repository(Constants.TCH_CONVERTTOINR_DAO)
public class ConToInrDaoImpl extends GenericDaoImpl<PaymentDTO> implements ConToInr{

	private static Logger logger = LoggerFactory.getLogger(ConToInrDaoImpl.class);

	@Override
	public PaymentDTO getOriginalSale(Payment input) throws TCHQueryException {
		logger.debug("Convert to inr Dao");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inInvoiceNumber = new SqlParameter("I_INVOICE_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		
		SqlParameter cursorTrnsactionDetails = new SqlOutParameter("C_TRANS_DATA", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_CONV_INR_PROC")
		.declareParameters(inTerminalSerialNumber,inInvoiceNumber,outSqlCode,outAppErrorCode,outDebugPoint,cursorTrnsactionDetails)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber().trim()  +"]" );
		logger.info("I_INVOICE_NUMBER: [" + input.getReferenceValue()  +"]" );
		
		String appName = null;
		if(input.getAppName() != null){
			appName = input.getAppName().trim();
		}
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_INVOICE_NUMBER", input.getReferenceValue()); 
		
		logger.info("Calling store procedure for sale TCH_CONV_INR_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		if(!"2".equals(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_C003,ErrorMaster.get(ErrorConstants.TCH_C003));
		}
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_TRANS_DATA");
		
		if(rows != null){
			List<PaymentDTO> listpaymentDTO =  UtilityHelper.getPaymentDTO(rows);
			if(listpaymentDTO.size() == 1){
				return listpaymentDTO.get(0);
			}else{
				throw new TCHQueryException(ErrorConstants.TCH_C004,ErrorMaster.get(ErrorConstants.TCH_C004));
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_C004,ErrorMaster.get(ErrorConstants.TCH_C004));
		}
	}
}
