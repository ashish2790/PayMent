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
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;
import com.awl.tch.util.UtilityHelper;

@Repository("tipAdjustmentDao")
public class TipAdjustmentDaoImpl extends GenericDaoImpl<PaymentDTO> implements TipAdjustmentDao{

	
	private static final Logger logger  = LoggerFactory.getLogger(TipAdjustmentDaoImpl.class);
	
	@Override
	public PaymentDTO getSaleParameter(Payment input) throws TCHQueryException {
		
		String invoiceNumber = input.getReferenceValue().trim();
		String termSerialNumber  = input.getTerminalSerialNumber().trim();
		
		logger.debug("Invoice number : " + invoiceNumber);
		logger.debug("Terminal serial number : " + termSerialNumber);
		
		String query = "SELECT TPT.* from TCH_PAYMENT_TXN TPT JOIN TCH_HANDSHAKES TH ON TPT.P_MERCHANTID = TH.H_CARD_ACQUIRING_ID AND "+
						"TPT.P_TERMINALID  = TH.H_TID AND TPT.P_CLIENTID = TH.H_CLIENT_ID AND TPT.P_TERMINAL_SERIAL_NUMBER = TH.H_TERMINAL_SERIAL_NUMBER "+
						"WHERE TPT.P_INVOICENUMBER = ? AND TH.H_TERMINAL_SERIAL_NUMBER = ?  AND (P_REQUEST_TYPE='SALE' OR P_REQUEST_TYPE='SALECSHBK' OR P_REQUEST_TYPE='CSHBK')";
		
		if(Property.isShowSql()){
			logger.debug("Query for fetching original amount [" + query  + "]");
		}
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query,invoiceNumber, termSerialNumber);
		if(rows != null){
			List<PaymentDTO> listpaymentDTO =  UtilityHelper.getPaymentDTO(rows);
			if(listpaymentDTO.size() > 1){
				
			} 
			if(listpaymentDTO.size() == 1){
				return listpaymentDTO.get(0);
			}else{
				throw new TCHQueryException(ErrorConstants.TCH_T004,ErrorMaster.get(ErrorConstants.TCH_T004));
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_T004,ErrorMaster.get(ErrorConstants.TCH_T004));
		}
	}
	
	@Override
	public PaymentDTO getSaleParameterWithSp(Payment input) throws TCHQueryException{
		
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inInvoiceNumber = new SqlParameter("I_INV_NUMBER", Types.VARCHAR);
		SqlParameter intxnCount = new SqlParameter("I_TXN_COUNT", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outHostId = new SqlOutParameter("O_HOST_ID", Types.VARCHAR);
		SqlParameter cursorTipData = new SqlOutParameter("C_TIP_DATA", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorExstData = new SqlOutParameter("C_EXST_TIP", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds) ;
		
		
		simpleJdbcCall.withProcedureName("TCH_TIP_PROC")	
		.declareParameters(inTerminalSerialNumber,inInvoiceNumber,intxnCount,outSqlCode,outAppErrorCode,outDebugPoint,outHostId,cursorTipData,cursorExstData)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("Calling store procedure for sale TCH_TIP_PROC ");
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber().trim()  +"]" );
		logger.info("I_INV_NUMBER: [" + input.getReferenceValue().trim()  +"]" );
		if(input.getNoOfCount() != null)
			if(Integer.parseInt(input.getNoOfCount()) == 0){
				input.setNoOfCount(null);
			}
		
		logger.info("I_TXN_COUNT: [" + input.getNoOfCount() +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_INV_NUMBER", input.getReferenceValue().trim())
				.addValue("I_TXN_COUNT",  input.getNoOfCount()); 
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String sqlCode = (String) output.get("O_SQL_CODE");
		String debugPt = (String) output.get("O_DEBUG_POINT");
		String oHostId = (String) output.get("O_HOST_ID");
		logger.debug("appErrorCode :" + appErrorCode);
		logger.debug("sqlCode :" + sqlCode);
		logger.debug("debugPt :" + debugPt);
		logger.debug("host id :" +oHostId);
		
		//if transaction count is present and original trasaction not found 
		// then dont throw exception instead of that 
		// proceed with tranasaction
		input.setHostId(oHostId);
		if(!"1AB".equals(appErrorCode)){
			if(appErrorCode != null){
				throw new TCHQueryException(appErrorCode, ErrorMaster.get(appErrorCode));
			}else if(debugPt != null && !"2".equals(debugPt)){
				throw new TCHQueryException(appErrorCode, ErrorMaster.get(appErrorCode));
			}
		}
		
		if(input.getNoOfCount() != null){
			
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> tipExstRows = (List<HashMap<String,Object>>) output.get("C_EXST_TIP");
			if(tipExstRows != null){
				List<PaymentDTO> listpaymentDTO =  UtilityHelper.getPaymentDTO(tipExstRows);
				logger.info("Size of the list :" + listpaymentDTO.size());
				if(listpaymentDTO.size() == 1){
					logger.debug("Original transction found");
					return listpaymentDTO.get(0);
				}else{
					logger.debug("Original transction not found");
					throw new TCHQueryException(ErrorConstants.TCH_T004,ErrorMaster.get(ErrorConstants.TCH_T004));
				}
			}else{
				logger.debug("Original transction not found");
				throw new TCHQueryException(ErrorConstants.TCH_T004,ErrorMaster.get(ErrorConstants.TCH_T004));
			}
		}else{
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> tipRows = (List<HashMap<String,Object>>) output.get("C_TIP_DATA");
			
			if(tipRows != null){
				List<PaymentDTO> listpaymentDTO =  UtilityHelper.getPaymentDTO(tipRows);
				if(listpaymentDTO.size() == 1){
					return listpaymentDTO.get(0);
				}else{
					throw new TCHQueryException(ErrorConstants.TCH_T004,ErrorMaster.get(ErrorConstants.TCH_T004));
				}
			}else{
				throw new TCHQueryException(ErrorConstants.TCH_T004,ErrorMaster.get(ErrorConstants.TCH_T004));
			}
		}
	}
}
