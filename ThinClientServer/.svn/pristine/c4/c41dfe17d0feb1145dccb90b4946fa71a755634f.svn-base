package com.awl.tch.upi.dao;

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

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.TchProcConstant;
import com.awl.tch.dao.GenericDaoImpl;
import com.awl.tch.model.QRRequestDTO;
import com.awl.tch.server.TcpServer;

@Repository("qrDao")
public class QRDaoImpl extends GenericDaoImpl<QRRequestDTO> implements QRDao{

	private static Logger logger = LoggerFactory.getLogger(QRDaoImpl.class);
	@Override
	public void qrCodeDetails(Payment input) {
		
		logger.debug("Entering in QR CODE DETAILS");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT_ACK", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName(TchProcConstant.TCH_GET_BATCH)
		.declareParameters(inTerminalSerialNumber, outSqlCode, outAppErrorCode, outDebugPoint, outBatchNumber)
		.withoutProcedureColumnMetaDataAccess()
		.compile();

		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim()); 
		
		logger.info("Calling store procedure for " +TchProcConstant.TCH_GET_BATCH);
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String oBatchNumber = (String) output.get("O_BATCH_NUMBER");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("oBatchNumber from SP :" + oBatchNumber);
		
		if(oBatchNumber == null){
			input.setBatchNumber("00001");
		}
		
		input.setBatchNumber(oBatchNumber);
	}
}
