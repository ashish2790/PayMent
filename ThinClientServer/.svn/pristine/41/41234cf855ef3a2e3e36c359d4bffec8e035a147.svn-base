package com.awl.tch.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentAuthDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;
import com.awl.tch.util.UtilityHelper;

@Repository("saleCompDao")
public class SaleCompDaoImpl extends GenericDaoImpl<PaymentAuthDTO> implements SaleCompDao{

	private final Logger logger = LoggerFactory.getLogger(SaleCompDaoImpl.class);
	
	@Override
	public void validationForPreauthComp(Payment input) throws TCHServiceException,
			TCHQueryException {
		logger.debug("Inside sale com dao impl check mid mismatch function");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inRetrivalRefNumber = new SqlParameter("I_RETRIVAL_REF_NUMBER", Types.VARCHAR);
		SqlParameter inBinNumber = new SqlOutParameter("I_BIN_NUMBER", Types.VARCHAR);
		
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		SqlParameter outTableId = new SqlOutParameter("O_TABLE_ID", Types.VARCHAR);
		SqlParameter outPreauthPer = new SqlOutParameter("O_PREAUTH_PER", Types.DECIMAL);
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_PREAUTH_COMP_PROC")
		.declareParameters(inTerminalSerialNumber,inRetrivalRefNumber,inBinNumber,outSqlCode,outAppErrorCode,outDebugPoint,outTableId,outBatchNumber,outPreauthPer)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		logger.info("I_RETRIVAL_REF_NUMBER: [" + input.getReferenceValue()  +"]" );
		logger.info("I_BIN_NUMBER: [" + input.getBinNumber()  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
		.addValue("I_BIN_NUMBER",input.getBinNumber())
		.addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber())
		.addValue("I_RETRIVAL_REF_NUMBER",input.getReferenceValue());
		
		logger.info("Calling store procedure for sale TCH_PREAUTH_COMP_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String oBatchNumber = (String) output.get("O_BATCH_NUMBER");
		String oTableId = (String) output.get("O_TABLE_ID");
		BigDecimal oPreauthPer = (BigDecimal) output.get("O_PREAUTH_PER");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("Batch number from SP :" + oBatchNumber);
		logger.info("Table Id from SP :" + oTableId);
		logger.info("Preauth Per from SP :" + oPreauthPer);
		
		
		if(appErrorCode != null)
			throw new TCHQueryException(appErrorCode, ErrorMaster.get(appErrorCode));
		if(!"6".equals(oDebugPoint))
			throw new TCHQueryException(ErrorConstants.TCH_S616,ErrorMaster.get(ErrorConstants.TCH_S616));
		input.setBatchNumber(oBatchNumber);
		input.setTableId(oTableId);
		input.setPreauthPer(oPreauthPer.toPlainString());
		
		logger.debug("Exiting sale com dao impl check mid mismatch function");
	}

	@Override
	public PaymentAuthDTO getDtoBasedOnRequestRRN(Payment input)
			throws TCHQueryException {
		
		String query = "Select * from TCH_PAYMENT_AUTH_TXN WHERE P_REQUEST_TYPE = ? AND P_RETRIVAL_REF_NUMBER = ? AND P_TERMINAL_SERIAL_NUMBER = ?";
		
		if(Property.isShowSql()){
			logger.debug("SQL [" +query+ "]" );
		}
		logger.debug("P_REQUEST_TYPE [PREAUTH]");
		logger.debug("P_RETRIVAL_REF_NUMBER ["+input.getReferenceValue()+"]");
		logger.debug("P_TERMINAL_SERIAL_NUMBER ["+input.getTerminalSerialNumber()+"]");
		List<Map<String,Object>> row = null;
		try {
			 row = getJdbcTemplate().queryForList(query, new Object[]{"PREAUTH",input.getReferenceValue(),input.getTerminalSerialNumber()});
		}catch(DataAccessException dae){
			logger.debug("Exception while fetching data :" + dae
					.getMessage());
			throw new TCHQueryException(ErrorConstants.TCH_S009, ErrorMaster.get(ErrorConstants.TCH_S009));
		}catch (Exception e){
			logger.debug("Exception while fetching data :" + e.getMessage());
			throw new TCHQueryException(ErrorConstants.TCH_S009, ErrorMaster.get(ErrorConstants.TCH_S009));
		}
		
		if(row != null){
			List<PaymentAuthDTO> listDto = UtilityHelper.getPaymentAuthDTO(row);
			return listDto.get(0);
		}else{
			throw new TCHQueryException("P-010","NO DATA FOUND");
		}
	}
	
	

}
