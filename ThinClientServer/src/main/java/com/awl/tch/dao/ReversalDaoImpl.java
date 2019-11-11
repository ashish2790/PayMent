package com.awl.tch.dao;

import java.sql.Types;
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

@Repository("paymentReversalDao")
public class ReversalDaoImpl extends GenericDaoImpl<PaymentDTO> implements ReversalDao{

	private static Logger logger = LoggerFactory.getLogger(ReversalDaoImpl.class); 
	
	@Override
	public PaymentDTO checkForActualTxn(final Payment input) throws TCHQueryException {
		logger.debug("Inside reversal dao");
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inClientId = new SqlParameter("I_CLIENT_ID", Types.VARCHAR);
		SqlParameter inRefValue = new SqlParameter("I_REF_VALUE", Types.VARCHAR);
		SqlParameter inInvoiceNumber = new SqlParameter("I_INVOICE_NUMBER", Types.VARCHAR);
		SqlParameter inBinNumber = new SqlParameter("I_BIN_NUMBER", Types.VARCHAR);
		
		SqlParameter outMid = new SqlOutParameter("O_MERCHANT_ID", Types.VARCHAR);
		SqlParameter outTid = new SqlOutParameter("O_TERMINAL_ID", Types.VARCHAR);
		SqlParameter outReversalCount = new SqlOutParameter("O_REV_COUNT", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outTableId = new SqlOutParameter("O_TABLE_ID", Types.VARCHAR);
		SqlParameter outBankCode = new SqlOutParameter("O_BANK_CODE", Types.VARCHAR);
		
		
		SqlParameter cursorPaymentDetails = new SqlOutParameter("C_PAYMENT_DETAIL", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds =  (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds); 
		
		simpleJdbcCall.withProcedureName("TCH_REVERSAL_PROC")
		.declareParameters(inTerminalSerialNumber,inClientId,inRefValue,inInvoiceNumber
				,inBinNumber,outMid,outTid,outReversalCount,outDebugPoint,outTableId,outSqlCode,outBankCode,outAppErrorCode,cursorPaymentDetails)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		
		logger.debug("Store procedure : " + simpleJdbcCall.getCallString());
		
		String refValuePresent = "N";
		String invoiceNumberPresent = "N";
		
		if(input.getInvoiceNumber() != null){
			invoiceNumberPresent = input.getInvoiceNumber();
		}
		if(input.getReferenceValue() != null && input.getAppName() == null){
			refValuePresent = input.getReferenceValue();
		}
		
		
		logger.debug("I_TERMINAL_SERIAL_NUMBER ["+input.getTerminalSerialNumber().trim()+"]");
		logger.debug("I_CLIENT_ID ["+input.getClientId().trim()+"]");
		logger.debug("I_REF_VALUE ["+refValuePresent+"]");
		logger.debug("I_INVOICE_NUMBER ["+invoiceNumberPresent+"]");
		logger.debug("I_BIN_NUMBER ["+input.getBinNumber()+"]");
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
											.addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
											.addValue("I_CLIENT_ID", input.getClientId().trim())
											.addValue("I_REF_VALUE", refValuePresent)
											.addValue("I_INVOICE_NUMBER", invoiceNumberPresent)
											.addValue("I_BIN_NUMBER", input.getBinNumber());
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String oMID = (String) output.get("O_MERCHANT_ID");
		String oTID = (String) output.get("O_TERMINAL_ID");
		String oReversalCount = (String) output.get("O_REV_COUNT"); 
		String oTableId = (String) output.get("O_TABLE_ID");
		String oBankCode = (String) output.get("O_BANK_CODE");
		logger.debug("oDebugPoint :" + oDebugPoint);
		logger.debug("sqlCode :" + sqlCode);
		logger.debug("appErrorCode :" + appErrorCode);
		logger.debug("merchatId :" + oMID);
		logger.debug("terminalId :" + oTID);
		logger.debug("terminalId :" + oTID);
		logger.debug("oTableId :" + oTableId);
		logger.debug("BankCode :" + oBankCode);
		logger.debug("Reversal count :" + oReversalCount);
		
		input.setMerchantId(oMID);
		input.setTerminalId(oTID);
		input.setTableId(oTableId);
		input.setBankCode(oBankCode);
		
		if(oTableId != null){
			 String label = oTableId.substring(oTableId.lastIndexOf('-'));
			 logger.debug("label :" + label);
			 if(label!= null && label.contains(Constants.TCH_AMEX)){
				 String midtid[] = getMidTids(input.getTerminalSerialNumber(),Constants.TCH_AMEX);
					if(midtid != null && midtid.length > 0){
						input.setMerchantId(midtid[0]);
						input.setTerminalId(midtid[1]);
					}
					logger.debug("AMEX MID" + input.getMerchantId());
					logger.debug("AMEX MID" + input.getTerminalId());
			 }
		}
		
		if(oReversalCount != null){
			input.setReversalCount(Integer.valueOf(oReversalCount));
		}else
			input.setReversalCount(3);
			
		
		if("1A".equals(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_R002, ErrorMaster.get(ErrorConstants.TCH_R002));
		}
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		if(!"2".equals(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
		}
		
		@SuppressWarnings("unchecked")
		PaymentDTO paymentDto = null;
		List<Map<String,Object>> rows = (List<Map<String, Object>>) output.get("C_PAYMENT_DETAIL");
		if(rows != null){
			List<PaymentDTO> listpaymentDTO  = UtilityHelper.getPaymentDTO(rows);
			logger.debug("Size of the list :" + listpaymentDTO.size());
			if(listpaymentDTO.size() == 0){
				throw new TCHQueryException(ErrorConstants.TCH_V004,ErrorMaster.get(ErrorConstants.TCH_V004));
			}else if(listpaymentDTO.size() == 1){
				paymentDto = listpaymentDTO.get(0);
			}else{
				throw new TCHQueryException(ErrorConstants.TCH_V007,ErrorMaster.get(ErrorConstants.TCH_V007));
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_V004,ErrorMaster.get(ErrorConstants.TCH_V004));
		}
		
		logger.debug("Exiting reversal dao");
		return paymentDto;
	}
}
