package com.awl.tch.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
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

import com.awl.tch.bean.DataPrintObject;
import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.constant.TchProcConstant;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentAuthDTO;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.model.TerminalParameterDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.UtilityHelper;

@Repository("preAuthDaoImpl")
public class PreAuthDaoImpl extends GenericDaoImpl<PaymentAuthDTO> implements PreAuthDao{

	private static final Logger logger = LoggerFactory.getLogger(PreAuthDaoImpl.class);
	
	/**
	   * Get Sale parameter based on <tt>Terminal Serial Number</tt>.  
	   * <p>
	   * This is the call for store procedure <tt>TCH_PAYMENT_SALE_PROC</tt>.
	   * As in this business logic related to whether MID TID swapping is done?, client id
	   * present or not?, terminal information for respective Terminal serial number is present 
	   * or not?, etc. All these validation are in place in the procedure.
	   * If any of the above mention validation violate , an error code is getting populated.
	   * <p>
	   * Error code is specific for each and every error as per the standard of WL.
	   * For all error code please refer {@link com.awl.tch.util.ErrorMaster}  
	   * 
	   * @param input Fixed reference of <tt>Payment</tt> object with required field present in it.
	   * @return <tt>List<TerminalParameterDTO></tt> if no error contain list of terminals.
	   * 							<p> if error present it contains only error related information.
	   * @exception TCHQueryException On output error.
	   * @exception TCHServiceException On output error.
	   * @see TCHQueryException
	   * @see TCHServiceException
	   */
	public List<TerminalParameterDTO> getPreauthParameter(final Payment input) throws TCHServiceException, TCHQueryException{
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inBinNumber = new SqlParameter("I_BIN_NUMBER", Types.VARCHAR);
		SqlParameter outMerchantId = new SqlOutParameter("O_MERCHANT_ID", Types.VARCHAR);
		SqlParameter outTerminalId = new SqlOutParameter("O_TERMINAL_ID", Types.VARCHAR);
		SqlParameter outClientId = new SqlOutParameter("O_CLIENT_ID", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outStanNumber = new SqlOutParameter("O_STAN_NUMBER", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		SqlParameter outBinRangeValue = new SqlOutParameter("O_BIN_RANGE_VALUE", Types.VARCHAR);
		SqlParameter outTerminalSwapp = new SqlOutParameter("O_TERMINAL_SWAPP", Types.VARCHAR);
		SqlParameter oTableId = new SqlOutParameter("O_TABLE_ID", Types.VARCHAR);
		SqlParameter cursorTerminalParameter = new SqlOutParameter("C_TERMINAL_PARAM", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall();
		
		simpleJdbcCall.withProcedureName("TCH_PAYMENT_PREAUTH_PROC")
		.declareParameters(inTerminalSerialNumber,inBinNumber,outMerchantId,outTerminalId,outTerminalSwapp,outClientId,outStanNumber,outBatchNumber,outSqlCode,
				outAppErrorCode,outDebugPoint,outBinRangeValue,oTableId,cursorTerminalParameter)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		logger.info("I_BIN_NUMBER: [" + input.getBinNumber()  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber())
				.addValue("I_BIN_NUMBER", input.getBinNumber()); 
		
		logger.info("Calling store procedure for PREAUTH :  TCH_PAYMENT_PREAUTH_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String mid = (String) output.get("O_MERCHANT_ID");
		String tid = (String) output.get("O_TERMINAL_ID");
		String clientId = (String) output.get("O_CLIENT_ID");
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		//String oStanNumber  = (String) output.get("O_STAN_NUMBER");
		String oTerminalSwapp = (String) output.get("O_TERMINAL_SWAPP");
		String oBatchNumber = (String) output.get("O_BATCH_NUMBER");
		Integer oBinRangeValue = Integer.valueOf((String) output.get("O_BIN_RANGE_VALUE"));
		String tableId = (String) output.get("O_TABLE_ID");
		
		logger.info("MID from SP :" + mid);
		logger.info("TID from SP :" + tid);	
		logger.info("Client id from SP :" + clientId);
		logger.info("oTerminal swapp :"  + oTerminalSwapp) ;
		logger.info("Batch number :" + oBatchNumber);
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		//logger.info("Stan Number :" + oStanNumber);
		logger.info("Bin range value :" + oBinRangeValue);
		logger.info("tableId :" + tableId);
		
		input.setTableId(tableId);
		input.setSessionKey(tableId);
		tableId = tableId !=null ? tableId.substring(3) : null;
		if(tableId != null && input.getPinBlock() == null){
			// check if pin block from terminal is blank and pin is mandatory 12/04/2017 by ashish
			if(!tableId.contains(Constants.TCH_Y)){
				throw new TCHQueryException(ErrorConstants.TCH_S018, ErrorMaster.get(ErrorConstants.TCH_S018));
			}
			
		}
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		if(!"7".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_P200,ErrorMaster.get(ErrorConstants.TCH_P200));
		}
		
		if("1".equalsIgnoreCase(oTerminalSwapp))
			input.setDecisionFlag("HNDSHK");
		
		input.setStanNumber(input.getStanNumber());
		input.setMerchantId(mid);
		input.setTerminalId(tid);
		input.setClientId(clientId);
		input.setBatchNumber(oBatchNumber);
		input.setBinRangeValue(oBinRangeValue);
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> terParamRows = (List<HashMap<String,Object>>) output.get("C_TERMINAL_PARAM");
		List<TerminalParameterDTO> listTermParam = new ArrayList<TerminalParameterDTO>();
		
		if(terParamRows != null){
			for(Map<String,Object> row : terParamRows){
				input.setBankCode((String) row.get("BANKCODE"));
				TerminalParameterDTO termDto = new TerminalParameterDTO();
				if((BigDecimal) row.get("CARD_TYPE") != null)
					termDto.setCardType(((BigDecimal) row.get("CARD_TYPE")).intValue());
				termDto.setCardRangeLow((String) row.get("CARD_RANGE_LOW"));
				termDto.setCardRangeHigh((String) row.get("CARD_RANGE_HIGH"));
				termDto.setCardLabel((String) row.get("CARD_LABEL"));
				if((BigDecimal)  row.get("TIPPERCENT") != null)
					termDto.setTipPercentage(((BigDecimal)  row.get("TIPPERCENT")).intValue());
				termDto.setReload((String) row.get("RELOAD"));
				termDto.setCashback((String) row.get("CASHBACK"));
				termDto.setCashbackPur((String) row.get("CASHBACKPUR"));
				if((BigDecimal) row.get("TIPPERCENT") != null)
					termDto.setTipPercentage(((BigDecimal) row.get("TIPPERCENT")).intValue());
				termDto.setOfflineCheck((String) row.get("OFFLINECHK"));
				if((BigDecimal) row.get("PAN_ENTRY_MODE") != null)
					termDto.setPanEntryMode(((BigDecimal) row.get("PAN_ENTRY_MODE")).intValue());
				termDto.setTipFlag((String)row.get("TIPFLAG"));
				termDto.setAuthorisation((String)row.get("AUTHORISATION"));
				termDto.setAdjustment((String)row.get("ADJUSTMENT"));		
				termDto.setRefundNew((String)row.get("REFUNDNEW"));
				termDto.setMid((String)row.get("MID"));
				termDto.setTid((String)row.get("TID"));
				termDto.setLineEncryption((String)row.get("LINEENC"));
				if((BigDecimal) row.get("TPDU") != null)
					termDto.setTpdu(((BigDecimal) row.get("TPDU")).toPlainString());
				if((BigDecimal) row.get("NII") != null)
					termDto.setNii(((BigDecimal) row.get("NII")).intValue());
				termDto.setPinPrompt((String)row.get("PINPROMPT"));
				termDto.setCurrencyName((String) row.get("CURRENCYNAME"));
				termDto.setCurrencyName((String) row.get("ALPHACURRENCYCODE"));
				termDto.setCurrencyCode(String.valueOf((BigDecimal) row.get("CURRENCYCODE")));
				termDto.setPreauthFlag((String) row.get("PREAUTH"));
				termDto.setDccFlag((String) row.get("DCCFLAG"));
				termDto.setDccFlag((String) row.get("DCCMID"));
				termDto.setDccFlag((String) row.get("DCCTID"));
				termDto.setBalanceInquiryAllowed((String) row.get("BAL_INQ_ALWD"));
				if((BigDecimal) row.get("SE_NUMBER") != null)
					termDto.setSeNumber(((BigDecimal) row.get("SE_NUMBER")).toPlainString());
				termDto.setKeyEntryMode((String) row.get("REFUND"));			
				listTermParam.add(termDto);
			}
			logger.debug("Term parameter list size : " + listTermParam.size());
		}
		return listTermParam;
	}

	public PaymentDTO getExistPreauthParamter(final Payment input) throws TCHServiceException, TCHQueryException{
		
		logger.debug("Entering in getExistPreauthParamter");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inAppName = new SqlParameter("I_APP_NAME", Types.VARCHAR);
		SqlParameter inClientId = new SqlParameter("I_CLIENT_ID", Types.VARCHAR);
		SqlParameter inInvoiceNumber = new SqlParameter("I_INVOICE_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT_ACK", Types.VARCHAR);
		
		SqlParameter cursorTerminalParameter = new SqlOutParameter("C_EXST_SALE_PARAMETER", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorUtilityInfo = new SqlOutParameter("C_UTILITY_INFO", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName(TchProcConstant.TCH_PREAUTH_ACK_PROC)
		.declareParameters(inTerminalSerialNumber, inAppName, inClientId, inInvoiceNumber, outSqlCode, outAppErrorCode, outDebugPoint, cursorTerminalParameter,cursorUtilityInfo)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		String appName = "";
		if(input.getAppName() != null)
			appName = input.getAppName().trim();
				
		logger.info("I_APP_NAME: [" + appName  +"]" );
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		logger.info("I_CLIENT_ID : [" + input.getClientId() +"]");
		logger.info("I_INVOICE_NUMBER : [" + input.getInvoiceNumber() +"]");
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_APP_NAME",appName)
				.addValue("I_CLIENT_ID", input.getClientId().trim())
				.addValue("I_INVOICE_NUMBER", input.getInvoiceNumber().trim());
				 
		
		logger.info("Calling store procedure for sale TCH_PREAUTH_ACK_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT_ACK");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		
		if("AB".equals(appErrorCode))
			return null;
		
		if(oDebugPoint != null && !"4".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S016, ErrorMaster.get(ErrorConstants.TCH_S016));
		}
		
		if(input.getAppName() != null){
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> utilityInfoRows = (List<HashMap<String,Object>>) output.get("C_UTILITY_INFO");
			
			if(utilityInfoRows != null){
				List<DataPrintObject> listPrintObj = new ArrayList<DataPrintObject>(); 
				for(HashMap<String,Object> row : utilityInfoRows){
					DataPrintObject d = new DataPrintObject();
					String[] a = ((String) row.get("U_PRINT_LABELS")).split(",");
					d.setPrintLabel(a[0]);
					d.setPrintVal(input.getBranchCode());
					listPrintObj.add(d);
					d = new DataPrintObject();
					d.setPrintLabel(a[1]);
					d.setPrintVal(input.getReferenceValue());
					listPrintObj.add(d);
				}
				DataPrintObject[] prntObjArray = new DataPrintObject[listPrintObj.size()];
				input.setDataPrintObject(listPrintObj.toArray(prntObjArray));
			}
		}
		
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_EXST_SALE_PARAMETER");
		
		PaymentDTO paymentDto  = new PaymentDTO();
		
		if(rows != null){
			List<PaymentDTO> listp =UtilityHelper.getPaymentDTO(rows);
			if(listp != null){
				logger.debug("Found actual sale transaction approved.");
				paymentDto = listp.get(0);
			}
			else
				return null;
		}else{
			logger.debug("Rows got null from TCH_PREAUTH_ACK_PROC");
			return null;
		}
		return paymentDto;
		
	}
	
}
