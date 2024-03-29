
package com.awl.tch.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.awl.tch.adaptor.brandemi.AbstractBrandEmiAdaptor;
import com.awl.tch.bean.DataPrintObject;
import com.awl.tch.bean.EMIObject;
import com.awl.tch.bean.HealthObject;
import com.awl.tch.bean.Payment;
import com.awl.tch.bean.ProgramObject;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.bean.TenureObject;
import com.awl.tch.bridge.ExternalEntityBridge;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.constant.TchProcConstant;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.AxisEpayDTO;
import com.awl.tch.model.GBSSDto;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.model.TerminalParameterDTO;
import com.awl.tch.model.UtilityDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.AppConfigMaster;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;
import com.awl.tch.util.UtilityHelper;
import com.tch.irctc.model.IrctcCacheObject;
import com.tch.sbiepay.exception.TCHSbiepayException;
/**
* <h1>Database layer class</h1>
* SaleDaoImpl program is used to do all
* <tt>CRUD</tt> related operation for storing the information in database.
* In which its going to store the information related to <tt>SALE</tt> transaction.
*  
* 
* <p>
* 1. Based on the requirement sometime it is used to only update flags.
* 2. For calling store procedure related to get data based on the <tt>Terminal Serial Number</tt>.
* 3. Get handshake parameters based on the client id and terminal serial number.  
*
* @author  Ashish Bhavsar
* @version 1.0
* @since   2016-09-21
*/
@Repository(Constants.TCH_SALE_DAO)
public class SaleDaoImpl extends GenericDaoImpl<PaymentDTO> implements SaleDao{
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	private static final Logger logger = LoggerFactory.getLogger(SaleDaoImpl.class);
	
	@Autowired
	@Qualifier(Constants.TCH_REPORT_DAO)
	private ReportDaoImpl epayDao;
	
	@Autowired
	@Qualifier(Constants.TCH_GBSS_DAO)
	GBSSDaoImpl gbssDao;
	
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
	public List<TerminalParameterDTO> getTerminalParameter(final Payment input) throws TCHServiceException, TCHQueryException{
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inAppName = new SqlParameter("I_APP_NAME", Types.VARCHAR);
		SqlParameter outMerchantId = new SqlOutParameter("O_MERCHANT_ID", Types.VARCHAR);
		SqlParameter outTxnCount = new SqlOutParameter("O_TXN_COUNT", Types.VARCHAR);
		SqlParameter outTerminalId = new SqlOutParameter("O_TERMINAL_ID", Types.VARCHAR);
		SqlParameter outClientId = new SqlOutParameter("O_CLIENT_ID", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outStanNumber = new SqlOutParameter("O_STAN_NUMBER", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		SqlParameter outTerminalSwapp = new SqlOutParameter("O_TERMINAL_SWAPP", Types.VARCHAR);
		SqlParameter outDailyLimit = new SqlOutParameter("O_DAILY_LIMIT", Types.VARCHAR);
		SqlParameter outRtmFlag = new SqlOutParameter("O_RTM_FLAG", Types.NUMERIC);
		SqlParameter oDownloadtime = new SqlOutParameter("O_DOWNLOAD_TIME", Types.VARCHAR);
		
		SqlParameter cursorTerminalParameter = new SqlOutParameter("C_TERMINAL_PARAM", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorAppConfig = new SqlOutParameter("C_APP_CONFIG", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorHealthObject = new SqlOutParameter("C_HEALTH_OBJECT", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorUtilityInfo = new SqlOutParameter("C_UTILITY_INFO", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_PAYMENT_SALE_PROC")
		.declareParameters(inTerminalSerialNumber,inAppName,outMerchantId,outTerminalId,outTerminalSwapp,outClientId,outStanNumber,outBatchNumber,outSqlCode,
				outAppErrorCode,outDebugPoint,outTxnCount,outDailyLimit,outRtmFlag,oDownloadtime,cursorTerminalParameter,cursorAppConfig,cursorHealthObject,cursorUtilityInfo)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber().trim()  +"]" );
		
		String appName = null;
		if(input.getAppName() != null){
			appName = input.getAppName().trim();
		}
		logger.info("I_APP_NAME: [" + appName  +"]" );
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_APP_NAME", appName); 
		
		logger.info("Calling store procedure for sale TCH_PAYMENT_SALE_PROC ");
		
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
		Integer oTxnCount = 0;
		Integer dailyLimit = 10;
		if((String) output.get("O_TXN_COUNT") != null)
			oTxnCount = Integer.valueOf((String) output.get("O_TXN_COUNT"));
		if((String) output.get("O_DAILY_LIMIT") != null)
		dailyLimit = Integer.valueOf((String) output.get("O_DAILY_LIMIT"));
		BigDecimal rtmFlag = (BigDecimal) output.get("O_RTM_FLAG");
		String downloadTime = (String) output.get("O_DOWNLOAD_TIME");
		
		logger.info("MID from SP :" + mid);
		logger.info("TID from SP :" + tid);
		logger.info("Client id from SP :" + clientId);
		//logger.info("Stan number :" + oStanNumber);
		logger.info("oTerminal swapp :"  + oTerminalSwapp) ;
		logger.info("Batch number :" + oBatchNumber);
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("Rtm flag :" + rtmFlag);
		//logger.info("Stan Number :" + oStanNumber);
		logger.info("Txn count :" + oTxnCount);
		logger.info("Download time in Min: " + downloadTime);
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		if(!"7".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
		}
		
		if("1".equalsIgnoreCase(oTerminalSwapp))
			input.setDecisionFlag("HNDSHK");
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> appConfRows = (List<HashMap<String,Object>>) output.get("C_APP_CONFIG");
		
		for(Map<String,Object> row : appConfRows){
			if(Constants.TCH_BINRANGE.endsWith((String) row.get("AC_NAME")))
				input.setBinRangeValue(Integer.parseInt((String) row.get("AC_VALUE")));
			if(Constants.TCH_DSPMSG80.equals((String) row.get("AC_NAME")) && oTxnCount > (dailyLimit - 10) && oTxnCount < dailyLimit){
				String dspMsg = "You have exceeded the transaction limit after "+(dailyLimit - oTxnCount)+ " transaction Server will initiate Auto settlement";
				input.setDisplayMessage(dspMsg);
			}
			if(Constants.TCH_DSPMSG100.equals((String) row.get("AC_NAME")) && oTxnCount == dailyLimit){
				input.setDisplayMessage("Your previous batch transactions are marked for settlement.");
			}
		}
		
		logger.info("Bin range value :" + input.getBinRangeValue());
		
		//input.setStanNumber(oStanNumber);
		input.setStanNumber(input.getStanNumber());
		input.setMerchantId(mid);
		input.setTerminalId(tid);
		input.setClientId(clientId);
		input.setBatchNumber(oBatchNumber);
		
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> terParamRows = (List<HashMap<String,Object>>) output.get("C_TERMINAL_PARAM");
		List<TerminalParameterDTO> listTermParam = new ArrayList<TerminalParameterDTO>();
		
		if(terParamRows != null){
			for(Map<String,Object> row : terParamRows){
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
				termDto.setCurrencyCode(String.valueOf((BigDecimal) row.get("CURRENCYCODE")));
				termDto.setPreauthFlag((String) row.get("PREAUTH"));
				termDto.setDccFlag((String) row.get("DCCFLAG"));
				termDto.setDccMid((String) row.get("DCCMID"));
				termDto.setDccTid((String) row.get("DCCTID"));
				termDto.setBalanceInquiryAllowed((String) row.get("BAL_INQ_ALWD"));
				if((BigDecimal) row.get("SE_NUMBER") != null)
					termDto.setSeNumber(((BigDecimal) row.get("SE_NUMBER")).toPlainString());
				termDto.setKeyEntryMode((String) row.get("REFUND"));
				listTermParam.add(termDto);
			}
			logger.debug("Term parameter list size : " + listTermParam.size());
		}
		if(rtmFlag != null){
			if(BigDecimal.ONE.compareTo(rtmFlag) == 0){
				
				@SuppressWarnings("unchecked")
				List<HashMap<String,Object>> healthObjectRows = (List<HashMap<String,Object>>) output.get("C_HEALTH_OBJECT");
				if(healthObjectRows != null){
					logger.info("Setting values in health object");
					for(Map<String,Object> row : healthObjectRows){
						HealthObject h = new HealthObject();
						h.setApn((String) row.get("APN"));
						//h.setCommMode((String) row.get("COMMON_MODE"));
						//h.setCommonIp((String) row.get("10.10.11.111"));
						//h.setCommonPort((String) row.get("9001"));
						//h.setDisplayStat((String) row.get("DISPLAY_STAT"));
						SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
						Calendar cal = Calendar.getInstance();
						Date d;
						try {
							d = sdf.parse(input.getTime());
							cal.setTime(d);
							if(downloadTime != null && !downloadTime.isEmpty()){
								cal.add(Calendar.MINUTE, Integer.parseInt(downloadTime));
								h.setDownLoadtime(sdf.format(cal.getTime()));
							}
						} catch (ParseException e) {
							throw new TCHQueryException( ErrorConstants.TCH_S111,"UNABLE TO PARSE DATE");
						}
										
						//h.setFailTrnxToHost(((BigDecimal) row.get("FAIL_TXN_TO_HOST")).toPlainString());
						//h.setIMIENumber((String) row.get("IMEI_NUMBER"));
						//h.setIpNum((String) row.get("IP_NUMBER"));
						//h.setModemStat((String) row.get("MODEM_STAT"));
						if((BigDecimal) row.get("NAC_VAL") != null)
							h.setNacVal(((BigDecimal) row.get("NAC_VAL")).toPlainString());
						//h.setPortNum((String) row.get("PORT_NUMBER"));
						if((BigDecimal) row.get("RTM_FLAG") != null)
							h.setRtmFlag(((BigDecimal) row.get("RTM_FLAG")).toPlainString());
						//h.setPrintStat((String) row.get("PRINT_STAT"));
						h.setTmsIp((String) row.get("TMS_IP"));
						h.setTmsPort((String) row.get("TMS_PORT"));
						if((BigDecimal) row.get("TXN_TIMEOUT") != null)
							h.setTrnxTimeOut(((BigDecimal) row.get("TXN_TIMEOUT")).toPlainString());
						//h.setUserProcTime(((BigDecimal) row.get("USER_PROC_TIME")).toPlainString());
						input.setHealthObject(h);
					}
				}
			}
		}
		
		/*
		 * External Utility related changes
		 */
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
		/*
		 * End of changes for SE number
		 */
		
		
		return listTermParam;
	}
	
	
	
	/**
	   * Get Sale parameter based on <tt>Terminal Serial Number</tt>.  
	   * <p>
	   * This is the call for store procedure <tt>TCH_SALE_ACK_PROC</tt>.
	   * 
	   * <p>
	   * Check whether already same sale request already received or not.
	   * if yes then check whether response from switch is present or not,
	   *  	1 if response from switch present then send the response without 
	   *  	  initiating Switch request.
	   *  	2 if response from switch is absent then proceed with switch request.
	   * if no then
	   *  	send the request as it is to switch as new request.   
	   * 
	   * @param input Fixed reference of <tt>Payment</tt> object with required field present in it.
	   * @return <tt>int</tt> zero if request already found in DB with response code 
	   * 					  -1 if request not found in DB	
	   * @exception TCHQueryException On output error.
	   * @exception TCHServiceException On output error.
	   * @see TCHQueryException
	   * @see TCHServiceException
	   */
	public PaymentDTO getExistSaleParamter(final Payment input) throws TCHServiceException, TCHQueryException{
		
		logger.debug("Entering in getExistSaleParamter");
		
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
		
		simpleJdbcCall.withProcedureName(TchProcConstant.TCH_SALE_ACK_PROC)
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
				 
		
		logger.info("Calling store procedure for sale TCH_SALE_ACK_PROC ");
		
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
			logger.debug("Rows got null from TCH_SALE_ACK_PROC");
			return null;
		}
		return paymentDto;
		
	}
	
	@Override
	public List<String> getEmiBin() throws TCHQueryException {
		List<String> ls = new ArrayList<String>();
		String sql = "SELECT distinct ISSUER_BIN_CODE  from TC_EMI_TABLECODE_002";
		
		if(Property.isShowSql()){
			logger.debug("Query for fetching information for EMI bin : " + sql);
		}
		List<Map<String, Object>> lst = getJdbcTemplate().queryForList(sql);
		if(lst != null){
			for(Map<String,Object> map : lst){
				ls.add(((String) map.get("ISSUER_BIN_CODE")));
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_S804,ErrorMaster.get(ErrorConstants.TCH_S804));
		}
		return ls;
	}
	
	
	public List<TerminalParameterDTO> getTerminalParameter1(final Payment input) throws TCHQueryException{

		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inAppName = new SqlParameter("I_APP_NAME", Types.VARCHAR);
		SqlParameter inBinNumber = new SqlParameter("I_BIN_NUMBER", Types.VARCHAR);
		SqlParameter outMerchantId = new SqlOutParameter("O_MERCHANT_ID", Types.VARCHAR);
		SqlParameter outTxnCount = new SqlOutParameter("O_TXN_COUNT", Types.VARCHAR);
		SqlParameter outTerminalId = new SqlOutParameter("O_TERMINAL_ID", Types.VARCHAR);
		SqlParameter outClientId = new SqlOutParameter("O_CLIENT_ID", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		SqlParameter outTerminalSwapp = new SqlOutParameter("O_TERMINAL_SWAPP", Types.VARCHAR);
		SqlParameter outDailyLimit = new SqlOutParameter("O_DAILY_LIMIT", Types.VARCHAR);
		SqlParameter outRtmFlag = new SqlOutParameter("O_RTM_FLAG", Types.NUMERIC);
		SqlParameter oDownloadtime = new SqlOutParameter("O_DOWNLOAD_TIME", Types.VARCHAR);
		SqlParameter oSummFlag = new SqlOutParameter("O_SUMM_FLAG", Types.VARCHAR);
		SqlParameter oTableId = new SqlOutParameter("O_TABLE_ID", Types.VARCHAR);
		SqlParameter oBankCode = new SqlOutParameter("O_BANK_CODE", Types.VARCHAR);
		//SqlParameter oBinInfo = new  SqlOutParameter("O_BIN_INFO", Types.VARCHAR);
		SqlParameter cursorTerminalParameter = new SqlOutParameter("C_TERMINAL_PARAM", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorAppConfig = new SqlOutParameter("C_APP_CONFIG", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorHealthObject = new SqlOutParameter("C_HEALTH_OBJECT", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorUtilityInfo = new SqlOutParameter("C_UTILITY_INFO", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName(TchProcConstant.TCH_PAYMENT_SALE_PROC)
		.declareParameters(inTerminalSerialNumber,inAppName,inBinNumber,outMerchantId,outTerminalId,outTerminalSwapp,outClientId,outBatchNumber,outSqlCode,
				outAppErrorCode,outDebugPoint,outTxnCount,outDailyLimit,outRtmFlag,oDownloadtime,oTableId,oBankCode,oSummFlag
				,cursorTerminalParameter,cursorAppConfig,cursorHealthObject,cursorUtilityInfo)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber().trim()  +"]" );
		
		String appName = null;
		if(input.getAppName() != null){
			appName = input.getAppName().trim();
		}
		logger.info("I_APP_NAME: [" + appName  +"]" );
		logger.debug("I_BIN_NUMBER :[" + input.getBinNumber()+"]");
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_BIN_NUMBER", input.getBinNumber())
				.addValue("I_APP_NAME", appName); 
		
		logger.info("Calling store procedure for sale " + TchProcConstant.TCH_PAYMENT_SALE_PROC);
		
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
		Integer oTxnCount = 0;
		Integer dailyLimit = 10;
		if((String) output.get("O_TXN_COUNT") != null)
			oTxnCount = Integer.valueOf((String) output.get("O_TXN_COUNT"));
		if((String) output.get("O_DAILY_LIMIT") != null)
		dailyLimit = Integer.valueOf((String) output.get("O_DAILY_LIMIT"));
		BigDecimal rtmFlag = (BigDecimal) output.get("O_RTM_FLAG");
		String downloadTime = (String) output.get("O_DOWNLOAD_TIME");
		String tableId = (String) output.get("O_TABLE_ID");
		String bankCode = (String) output.get("O_BANK_CODE");
		String summaryFlag = (String) output.get("O_SUMM_FLAG");
		/*String binInfo = null;
		if(output.get("O_BIN_INFO") != null)
			 binInfo = (String) output.get("O_BIN_INFO");*/
		
		
		logger.info("MID from SP :" + mid);
		logger.info("TID from SP :" + tid);
		logger.info("Client id from SP :" + clientId);
		//logger.info("Stan number :" + oStanNumber);
		logger.info("oTerminal swapp :"  + oTerminalSwapp) ;
		logger.info("Batch number :" + oBatchNumber);
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("Rtm flag :" + rtmFlag);
		//logger.info("Stan Number :" + oStanNumber);
		logger.info("Txn count :" + oTxnCount);
		logger.info("Download time in Min: " + downloadTime);
		logger.debug("Table id :" + tableId);
		logger.debug("bank code :"+bankCode);
		logger.debug("Summary flag :" + summaryFlag);
		//logger.debug("Bininfo :" + binInfo);
		//B-MAST-CC-D
		input.setMerchantId(mid);
		input.setTerminalId(tid);
		input.setStanNumber(input.getStanNumber());
		input.setClientId(clientId);
		input.setBatchNumber(oBatchNumber);
		input.setBankCode(bankCode);
		if(summaryFlag != null){
			String arrSummaryFlag[] = summaryFlag.split("~");
			if(arrSummaryFlag != null && arrSummaryFlag.length>1){
				String binInfo = arrSummaryFlag[1];
				//B-MAST-CC-D
				//changes for gettng bin card type credit or debit
				if(null != binInfo){
					String arr[] = binInfo.split("-");
					input.setCardTypeValue(UtilityHelper.reverseCardEntryMode(arr[arr.length-2]));
					input.setBinType(arr[arr.length-1]); // set international flag
					/* SBI E-Pay New Requirement--Ganesh Misal's Changes--END */
					
					if(Constants.TCH_MPOS.equals(appName)){
						if("CC".equals(arr[arr.length-2])){
							logger.debug("Exception while validating the card type :");
							throw new TCHQueryException("MP-00", "Credit card not allowed!");
						}
					}
					
					if("SBIEPAY".equals(appName))
						try {
							validateCardType(input.getReferenceValue(), arr[arr.length-2]);
						} catch (TCHSbiepayException e) {
							logger.debug("Exception while validating the card type :" + e.getMessage());
							throw new TCHQueryException(e.getErrorCode(), ErrorMaster.get(e.getErrorCode()));
						}
					/* SBI E-Pay New Requirement--Ganesh Misal's Changes--END */
					logger.debug("Card type value : " + input.getCardTypeValue());
				}
			}
			summaryFlag = arrSummaryFlag[0];
		}
		
		// set summary flag to print summary report only before settling the batch
		
		if(Constants.TCH_1.equals(summaryFlag) && !(Constants.TCH_EMI_SALE.equals(input.getDecisionFlag()) || Constants.TCH_BRDSALE.equals(input.getDecisionFlag()))){
			input.setDecisionFlag(Constants.TCH_SUMMARY_REPORT);
			input.setHundredTxn(Constants.TCH_Y);
		}else if(Constants.TCH_2.equals(summaryFlag)){
			if(isNewVersionPresent(input.getTerminalSerialNumber()))
				throw new TCHQueryException(ErrorConstants.TCH_FRSET, ErrorMaster.get(ErrorConstants.TCH_FRSET));
		}
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		if(!"7".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
		}
		
		if("1".equalsIgnoreCase(oTerminalSwapp))
			input.setDecisionFlag("HNDSHK");
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> appConfRows = (List<HashMap<String,Object>>) output.get("C_APP_CONFIG");
		
		for(Map<String,Object> row : appConfRows){
			if((String) row.get("AC_NAME") != null){
				if(Constants.TCH_BINRANGE.endsWith((String) row.get("AC_NAME")))
					input.setBinRangeValue(Integer.parseInt((String) row.get("AC_VALUE")));
				if(Constants.TCH_DSPMSG80.equals((String) row.get("AC_NAME")) && oTxnCount > (dailyLimit - 10) && oTxnCount < dailyLimit){
					String dspMsg = "SETTLELMENT INITIATED AFTER "+(dailyLimit - oTxnCount)+ " TXN";
					input.setDisplayMessage(dspMsg);
				}
				if(Constants.TCH_DSPMSG100.equals((String) row.get("AC_NAME")) && oTxnCount == dailyLimit){
					input.setDisplayMessage("BATCH SETTLEMENT WILL BE INTIATED");
				}
			}
		}
		
		logger.info("Bin range value :" + input.getBinRangeValue());
		//input.setStanNumber(oStanNumber);
		
		input.setTableId(tableId);
		if(input.getPinBypass() != null){
			tableId = tableId !=null ? tableId.substring(3) : null;
			if(tableId != null && input.getPinBlock() == null){
				// check if pin block from terminal is blank and pin is mandatory 12/04/2017 by ashish
				if(!tableId.contains(Constants.TCH_Y)){
					throw new TCHQueryException(ErrorConstants.TCH_S018, ErrorMaster.get(ErrorConstants.TCH_S018));
				}
			}
		}
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> terParamRows = (List<HashMap<String,Object>>) output.get("C_TERMINAL_PARAM");
		List<TerminalParameterDTO> listTermParam = new ArrayList<TerminalParameterDTO>();
		
		if(terParamRows != null){
			for(Map<String,Object> row : terParamRows){
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
				termDto.setCurrencyCode(String.valueOf((BigDecimal) row.get("CURRENCYCODE")));
				termDto.setPreauthFlag((String) row.get("PREAUTH"));
				termDto.setDccFlag((String) row.get("DCCFLAG"));
				termDto.setDccMid((String) row.get("DCCMID"));
				termDto.setDccTid((String) row.get("DCCTID"));
				termDto.setBalanceInquiryAllowed((String) row.get("BAL_INQ_ALWD"));
				if((BigDecimal) row.get("SE_NUMBER") != null)
					termDto.setSeNumber(((BigDecimal) row.get("SE_NUMBER")).toPlainString());
				termDto.setKeyEntryMode((String) row.get("REFUND"));
				listTermParam.add(termDto);
			}
			logger.debug("Term parameter list size : " + listTermParam.size());
		}
		logger.debug("RTM flag value :" + rtmFlag);
		if(rtmFlag != null){
			if(BigDecimal.ONE.compareTo(rtmFlag) == 0){
				@SuppressWarnings("unchecked")
				List<HashMap<String,Object>> healthObjectRows = (List<HashMap<String,Object>>) output.get("C_HEALTH_OBJECT");
				logger.debug("health object value:" + healthObjectRows);
				if(healthObjectRows != null){
					logger.info("Setting values in health object");
					for(Map<String,Object> row : healthObjectRows){
						HealthObject h = new HealthObject();
						h.setApn((String) row.get("APN"));
						SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
						Calendar cal = Calendar.getInstance();
						Date d;
						try {
							d = sdf.parse(input.getTime());
							cal.setTime(d);
							if(downloadTime != null && !downloadTime.isEmpty()){
								cal.add(Calendar.MINUTE, Integer.parseInt(downloadTime));
								h.setDownLoadtime(sdf.format(cal.getTime()));
							}
						} catch (ParseException e) {
							throw new TCHQueryException( ErrorConstants.TCH_S111,"UNABLE TO PARSE DATE");
						}
										
						if((BigDecimal) row.get("NAC_VAL") != null)
							h.setNacVal(((BigDecimal) row.get("NAC_VAL")).toPlainString());
						if((BigDecimal) row.get("RTM_FLAG") != null)
							h.setRtmFlag(((BigDecimal) row.get("RTM_FLAG")).toPlainString());
						h.setTmsIp((String) row.get("TMS_IP"));
						h.setTmsPort((String) row.get("TMS_PORT"));
						if((BigDecimal) row.get("TXN_TIMEOUT") != null)
							h.setTrnxTimeOut(((BigDecimal) row.get("TXN_TIMEOUT")).toPlainString());
						input.setHealthObject(h);
					}
				}
			}
		}
		
		/*
		 * External Utility related changes
		 */
		if(input.getAppName() != null){
			//List<HashMap<String,Object>> utilityInfoRows = (List<HashMap<String,Object>>) output.get("C_UTILITY_INFO");
			// changing code to get from cache and avoid db dip.
			UtilityDTO utilityInfoRow = AppConfigMaster.getUtilInfoValue(input.getAppName());
			
			if(utilityInfoRow != null){
				List<DataPrintObject> listPrintObj = new ArrayList<DataPrintObject>(); 
				//for(HashMap<String,Object> row : utilityInfoRows){
					
					if(Constants.TCH_IRCTC.equals(input.getAppName())){
						DataPrintObject d = new DataPrintObject();
						String[] a = utilityInfoRow.getPrintLabels().split(",");
						d.setPrintLabel(a[0]);
						d.setPrintVal("");
						listPrintObj.add(d);
						
						IrctcCacheObject cacheObject = ExternalEntityBridge.irctcCache.get(input.getTerminalSerialNumber());
						d = new DataPrintObject();
						d.setPrintLabel(" ");
						d.setPrintVal(cacheObject.getTxnId());
						logger.debug("txn value :" + cacheObject.getTxnId());
						listPrintObj.add(d);
						
					}else if(Constants.TCH_AXIS_EPAY.equals(input.getAppName())){
						DataPrintObject d = new DataPrintObject();
						String[] a = utilityInfoRow.getPrintLabels().split(",");
						
						AxisEpayDTO axisDTO = getExistingTransactionDetails(input.getReferenceValue());
						
						d.setPrintLabel(a[0]);
						d.setPrintVal(axisDTO.getCustomerName());
						listPrintObj.add(d);
						

						d = new DataPrintObject();
						d.setPrintLabel(a[1]);
						d.setPrintVal(axisDTO.getUrn());
						listPrintObj.add(d);
						
						d = new DataPrintObject();
						d.setPrintLabel(a[2]);
						d.setPrintVal(axisDTO.getCrn());
						listPrintObj.add(d);
						
					}else{
						DataPrintObject d = new DataPrintObject();
						String[] a = utilityInfoRow.getPrintLabels().split(",");
						d.setPrintLabel(a[0]);
						d.setPrintVal(input.getBranchCode() != null ? input.getBranchCode() : input.getReferenceValue());
						listPrintObj.add(d);
						if(a.length > 1){				
							d = new DataPrintObject();
							d.setPrintLabel(a[1]);
							d.setPrintVal(input.getReferenceValue());
							listPrintObj.add(d);
						}
						if(Constants.TCH_GBSS.equals(input.getAppName())){
							
							/*
							 * CONVENIENCE FEE RELATED CHANGES BY ASHISH 28/03/2018
							 */
							GBSSDto gbssDto = gbssDao.getGBSSinfo(input.getReferenceValue());
							/*
							 * END OF CONVENIENCE FEE RELATED CHANGES BY ASHISH 28/03/2018
							 */
							// ADDED TO AVOID TWO TIME DEDUCTION OF THE SAME CHALLEN NUMBER
							if(Constants.TCH_GBSS_STATUS_PROCESSED.equals(gbssDto.getStatus())){
								throw new TCHQueryException("GB-14", "CHALLAN ALREADY UTILISED");
							}
							d = new DataPrintObject();
							d.setPrintLabel("");
							d.setPrintVal("-----------------------------------------------");
							listPrintObj.add(d);
							
							d = new DataPrintObject();
							d.setPrintLabel(Constants.TCH_GBSS_BASE_AMT);
							d.setPrintVal(new BigDecimal(gbssDto.getAmount()).movePointLeft(2).toPlainString());
							listPrintObj.add(d);
							
							if(gbssDto.getFee() != null){
								d = new DataPrintObject();
								
								if(gbssDto.getFeePer() == null){	
									d.setPrintLabel(Constants.TCH_GBSS_CONV_FLAT_AMT);
								}else{
									d.setPrintLabel(Constants.TCH_GBSS_CONV_AMT + gbssDto.getFeePer() + Constants.TCH_GBSS_REMAINING);
								}
								String fee = new BigDecimal(gbssDto.getFee()).movePointLeft(2).toPlainString();
								d.setPrintVal(fee);
								input.setFeeFlag("C");
								input.setFeeValue(gbssDto.getFee());
								listPrintObj.add(d);
							}
							
							if(gbssDto.getTax1() != null){
								d = new DataPrintObject();
								d.setPrintLabel(Constants.TCH_GBSS_CGST + gbssDto.getTax1Per() + Constants.TCH_GBSS_REMAINING);
								String cgst = new BigDecimal(gbssDto.getTax1()).movePointLeft(2).toPlainString();
								d.setPrintVal(cgst);
								input.setCgsttax(gbssDto.getTax1());
								listPrintObj.add(d);
							}
							
							if(gbssDto.getTax2() != null){
								d = new DataPrintObject();
								d.setPrintLabel(Constants.TCH_GBSS_SGST + gbssDto.getTax2Per() + Constants.TCH_GBSS_REMAINING);
								String igst = new BigDecimal(gbssDto.getTax2()).movePointLeft(2).toPlainString();
								d.setPrintVal(igst);
								input.setIgsttax(gbssDto.getTax2());
								listPrintObj.add(d);
								d = new DataPrintObject();
								d.setPrintLabel("");
								d.setPrintVal("-----------------------------------------------");
								listPrintObj.add(d);
							}
							
							d = new DataPrintObject();
							d.setPrintLabel(Constants.TCH_GBSS_TOTAL);
							d.setPrintVal(new BigDecimal(input.getOriginalAmount()).movePointLeft(2).toPlainString());
							listPrintObj.add(d);
						}
					}
				//}
				DataPrintObject[] prntObjArray = new DataPrintObject[listPrintObj.size()];
				input.setDataPrintObject(listPrintObj.toArray(prntObjArray));
			}
		}
		/*
		 * End of changes for SE number
		 */
		return listTermParam;
	
	}
	
	
	/**
	 * @param payment object
	 * @author ashish.bhavsar
	 */
	@Override
	public Payment getEMIEnquiry(Payment input) throws TCHQueryException {

		
		logger.debug("Entering in get EMI enquiry");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inBinCode = new SqlParameter("I_BIN_CODE", Types.VARCHAR);
		SqlParameter inProgramCode = new SqlParameter("I_PROGRAM_CODE", Types.VARCHAR);
		SqlParameter inTenureCode = new SqlParameter("I_TENURE_CODE", Types.VARCHAR);
		SqlParameter inManufatureCode = new SqlParameter("I_MFG_CODE", Types.VARCHAR);
		SqlParameter inProductCode = new SqlParameter("I_PRODUCT_CODE", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		
		SqlParameter cursorProgramDetail= new SqlOutParameter("C_PROGRAM_DETAIL", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorTenureDetail = new SqlOutParameter("C_TENURE_DETAIL", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorCashBackDetails = new SqlOutParameter("C_CASHBACK_DETAIL",OracleTypes.CURSOR, new ColumnMapRowMapper());
		SqlParameter cursorProductInfo = new SqlOutParameter("C_PRODUCT_DETAIL",OracleTypes.CURSOR, new ColumnMapRowMapper());
		SqlParameter camountInfo = new SqlOutParameter("C_AMOUNT_DETAILS",OracleTypes.CURSOR, new ColumnMapRowMapper()); // changes for amount check
		
		DataSource ds =  (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		SuperMenuObject superObj  = AbstractBrandEmiAdaptor.brandEmiFinalCache.get(input.getTerminalSerialNumber());
		if(superObj == null){
			AbstractBrandEmiAdaptor.brandEmiFinalCache.put(input.getTerminalSerialNumber(),new SuperMenuObject());
			superObj = AbstractBrandEmiAdaptor.brandEmiFinalCache.get(input.getTerminalSerialNumber());
		}else if(superObj != null && !Constants.TCH_BRDSKU.equals(input.getDecisionFlag()) && !Constants.TCH_BRDENQ.equals(input.getDecisionFlag()) && !Constants.TCH_BRDTNR.equals(input.getDecisionFlag()) && !Constants.TCH_BRDSALE.equals(input.getDecisionFlag())){
			logger.debug("setting product details Null");
			superObj.setProductDetails(null);
			superObj.setProductDesc(null);
			superObj.setManuFactureName(null);
		}
		simpleJdbcCall.withProcedureName("TCH_EMI_DETAILS_PROC")
		.declareParameters(inTerminalSerialNumber, inBinCode, inProgramCode, inTenureCode, inManufatureCode, inProductCode, outSqlCode, outAppErrorCode, outDebugPoint, cursorProgramDetail, cursorTenureDetail,cursorCashBackDetails,cursorProductInfo,camountInfo)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.debug("Store procedure called :" + simpleJdbcCall.getCallString());
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		String programCode = "A";
		String tenure = "A";
		if(input.getEmiObject() != null){
			if(input.getEmiObject().getProgramObject() != null){
				programCode = input.getEmiObject().getProgramObject()[0].getProgramCode(); 
			}
			if(input.getEmiObject().getTenureObject() != null){
				tenure = input.getEmiObject().getTenureObject()[0].getTenure(); 
			}
		}
		logger.debug("Bin value" +input.getBinNumber());
		//Integer binCode = Integer.valueOf(input.getBinNumber().trim().substring(0, input.getBinRangeValue())) ;
		BigInteger binCode = new BigInteger(input.getBinNumber()); //changes to support bin value 7,8,9
		logger.info("I_PROGRAM_CODE : [" + programCode +"]");
		logger.info("I_TENURE_CODE : [" + tenure +"]");
		logger.info("I_BIN_CODE : [" +binCode +"]");
		logger.info("I_MFG_CODE : [" +input.getManufacturerCode() +"]");
		logger.info("I_PRODUCT_CODE : [" +input.getProductCode() +"]");
		
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
				.addValue("I_BIN_CODE", binCode)
				.addValue("I_PROGRAM_CODE", programCode)
				.addValue("I_TENURE_CODE", tenure)
				.addValue("I_MFG_CODE", input.getManufacturerCode())
				.addValue("I_PRODUCT_CODE", input.getProductCode());
				 
		
		logger.info("Calling store procedure for sale TCH_EMI_DETAILS_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		simpleJdbcCall = null;
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		if(appErrorCode != null)
			if(ErrorConstants.TCH_E001.equals(appErrorCode) && Constants.TCH_2AA.equals(oDebugPoint)){
				logger.debug("Proceeding with normal sale as no programs found");
				input.setNormaltxn(Constants.TCH_Y);
				logger.debug("NORMAL TXN FLAG :" + input.getNormaltxn());
				return input;
			}else{
				throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
			}
		if(oDebugPoint != null && !"3".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S016, ErrorMaster.get(ErrorConstants.TCH_S016));
		}
		
		//changes done by ashish b 12/12/2017
		// check condition for EMI enable basis on transaction amount and bin range
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> amountDetails = (List<HashMap<String,Object>>) output.get("C_AMOUNT_DETAILS");
		logger.debug("amountDetails :" + amountDetails);
		if(amountDetails != null){
			boolean emiApplicable = false;
			for(Map<String,Object> row : amountDetails){
				if(input.getBinNumber() != null) {
					if(null != row.get("ISSUER_BIN_CODE") && input.getBinNumber().substring(0,6).equals((String)row.get("ISSUER_BIN_CODE"))){
						superObj.setIssuerBankName((String)row.get("ISSUER_BANK_NAME"));
						Integer maxValue = 0;
						Integer minValue = 0;
						if((String) row.get("EMI_MAX_TRN_CAP") != null)
							maxValue = Integer.valueOf((String) row.get("EMI_MAX_TRN_CAP"));
						if((String) row.get("EMI_MIN_TRN_CAP") != null)
							minValue = Integer.valueOf((String) row.get("EMI_MIN_TRN_CAP"));
						if(Integer.valueOf(input.getOriginalAmount()) >= minValue ){
							emiApplicable = true;
							break;
						}
					}
				}
			}
			if(!emiApplicable){
				logger.debug("Not applicatble for EMI due to BIN/AMOUNT");
				input.setNormaltxn(Constants.TCH_Y);
				return input;
			}
		}
		
		//end of  changes  by ashish b 12/12/2017
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> productDetails = (List<HashMap<String,Object>>) output.get("C_PRODUCT_DETAIL");
		logger.debug("Checking product max min");
		
		//check if the product is mapped to program
		if(input.getEmiObject() != null){
			if(input.getEmiObject().getPrductCode() != null && !input.getEmiObject().getPrductCode().isEmpty()){
				logger.debug("PRODUCT CODE : "+input.getEmiObject().getPrductCode());
				checkProductCodeByAmountComparison(input.getEmiObject().getPrductCode().toUpperCase(),programCode,input.getOriginalAmount(),input.getMerchantId(),input.getTerminalId());
			}
		}
		
		
		if(productDetails != null){
			for(Map<String,Object> row : productDetails){
				Integer prodMax = null;
				Integer prodMin = null;
				if(((BigDecimal) row.get("PRODUCTMAXMRP")) != null){
					prodMax =  ((BigDecimal) row.get("PRODUCTMAXMRP")).intValue();
				}
				if(((BigDecimal) row.get("PRODUCTMINMRP")) != null){
					prodMin =  ((BigDecimal) row.get("PRODUCTMINMRP")).intValue();
				}
				logger.debug("prodMin [" + prodMin + "] prodMax [" + prodMax +"]");
				logger.debug("product code :" + (String) row.get("PRODUCT_CODE"));
				if(superObj == null){
					logger.debug("SuperObj is Null");
					AbstractBrandEmiAdaptor.brandEmiFinalCache.put(input.getTerminalSerialNumber(),new SuperMenuObject());
					superObj = AbstractBrandEmiAdaptor.brandEmiFinalCache.get(input.getTerminalSerialNumber());
				}else if(superObj != null && !Constants.TCH_BRDSKU.equals(input.getDecisionFlag()) && !Constants.TCH_BRDENQ.equals(input.getDecisionFlag()) && !Constants.TCH_BRDTNR.equals(input.getDecisionFlag()) && !Constants.TCH_BRDSALE.equals(input.getDecisionFlag())){
					logger.debug("setting product details Null");
					superObj.setProductDetails(null);
				}
				superObj.setManuFactureName((String) row.get("MANUFACTURER_NAME"));
				superObj.setManufatureDisclaimer((String) row.get("MANUFACTURERDESCLAIMER"));
				superObj.setManifactureCode((String) row.get("MANUFACTURER_CODE"));
				superObj.setProductDesc((String) row.get("PRODUCT_DESCRIPTION"));
				//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_PRODUCT_CODE, (String) row.get("PRODUCT_CODE"));
				//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_MANF_CODE, (String) row.get("MANUFACTURER_CODE"));
				//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_MANUFACTURE_DISC, (String) row.get("MANUFACTURERDESCLAIMER"));
				//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_PRODUCT_DESC, (String) row.get("PRODUCT_DESCRIPTION"));
				
				if(prodMax != null && Integer.parseInt(input.getOriginalAmount()) > prodMax){
					throw new TCHQueryException(ErrorConstants.TCH_E003, ErrorMaster.get(ErrorConstants.TCH_E003));
				}else if(prodMin != null && Integer.parseInt(input.getOriginalAmount()) < prodMin){
					throw new TCHQueryException(ErrorConstants.TCH_E004, ErrorMaster.get(ErrorConstants.TCH_E004));
				}
			}
		}
		
		//logger.info("Manufacture disclaimer :" +input.getManufatureDisclaimer());
		
		logger.debug("end of Checking product max min");
		if("A".equals(programCode)){
			logger.debug("Fetching details for Programs");
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_PROGRAM_DETAIL");
			
			if(rows != null){
				logger.info("No of programs :["+rows.size()+"]");
				List<ProgramObject> prgObjList = new ArrayList<ProgramObject>();
				for(Map<String,Object> row : rows){
					if(!isExpire((String) row.get("ENDDATE"))){				
						ProgramObject p = new ProgramObject();
						p.setProductCodeFlag((String) row.get("PRODUCTFLAG"));
						p.setProgramCode((String) row.get("PROGRAMCODE"));
						p.setProgramShortName((String) row.get("PROGRAMSHORTNAME"));
						prgObjList.add(p);
						p=null;
					}
				}
				EMIObject e = new EMIObject();
				ProgramObject[] prgArr = new ProgramObject[rows.size()];
				e.setProgramObject(prgObjList.toArray(prgArr));
				input.setDecisionFlag("EMIPRG");
				input.setEmiObject(e);
			}else{
				logger.debug("Rows are null");
			}
		}else if("A".equals(tenure)){
			EMIObject e = input.getEmiObject();
			logger.debug("Fetching details for Tenure");
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_TENURE_DETAIL");
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> cashbackrows = (List<HashMap<String,Object>>) output.get("C_CASHBACK_DETAIL");
			
			if(rows != null){
				List<TenureObject> tenObjList = new ArrayList<TenureObject>();
				for(Map<String,Object> row : rows){
					
					String emiMinCap = (String) row.get("EMI_MIN_TRN_CAP");
					String emiMaxCap = (String) row.get("EMI_MAX_TRN_CAP");
					if(emiMaxCap != null && emiMaxCap.contains(".")){
						emiMaxCap = new BigDecimal(emiMaxCap).movePointRight(2).toPlainString();
					}
					if(emiMinCap != null && emiMinCap.contains(".")){
						emiMinCap = new BigDecimal(emiMinCap).movePointRight(2).toPlainString();
					}
						
					checkForValidEmiAmount(input, emiMinCap, emiMaxCap);
					
					TenureObject t = new TenureObject();
					superObj.setIssuerBankName((String) row.get("ISSUER_BANK_NAME"));
					superObj.setIssuerDisclaimer((String) row.get("ISSUERDISCLAIMER"));
					superObj.setAcqDisclaimer((String) row.get("ACQUIERERDISCLAIMER"));
					//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_BANK_NAME, );
					//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_ACQ_BANK_DISC, );
					//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_BANK_DISC, );
						
					t.setRoi((String) row.get("INTERESTS_RATE"));
					t.setTenure((String) row.get("TENURE_CODE"));
					t.setPerFeeAmount((String) row.get("PROCESS_FEE_AMT")); // IN PAISE
					logger.debug("Processing fee amount from db :" + (String) row.get("PROCESS_FEE_AMT")+ " | " + t.getPerFeeAmount());
					t.setPerFeePercentage((String) row.get("PROCESS_FEE_PERCENT")); 
					
					logger.debug("Discount flag :" + (String) row.get("DISCOUNT_FLAG"));
					superObj.setDiscountFlag((String) row.get("DISCOUNT_FLAG"));
					t.setDiscountFlag((String) row.get("DISCOUNT_FLAG"));
					BigDecimal orgAmount = new BigDecimal(input.getOriginalAmount()).movePointLeft(2);
					logger.debug("Original amount in decimal :" + orgAmount);
					logger.debug("EMI_CAL_METHOD :" + (String) row.get("EMI_CAL_METHOD"));
					Double emiAmount = 0d;
					if("2".equals((String) row.get("EMI_CAL_METHOD"))){
						logger.info("Method 2 calculation");
						orgAmount = calculateOriginalAmountAfterCashBackAmount(orgAmount, input, t, cashbackrows, superObj);
						logger.info("Calculated org amount :" + orgAmount);
						
						emiAmount = calcEmi(orgAmount.doubleValue(), Double.valueOf(t.getRoi()), Double.valueOf(t.getTenure())); // calculate amount for each tenure
						/*
						 * changes to add processing fee by ashish.bhavsar 11/09/2017
						 */
						
						// calculate  processing fee in original value
						BigDecimal processingFee = BigDecimal.valueOf(calculateAmountAfterProcessingFee(t, new BigDecimal(input.getOriginalAmount()).movePointLeft(2).doubleValue()));
								
						
						logger.debug("Processing fee :" + processingFee);
						t.setPerFeeAmount(String.format("%.02f", processingFee));
						logger.debug("Processing fee amount ater setting :" + t.getPerFeeAmount());
						/*
						 * end of changes to add processing fee by ashish.bhavsar 11/09/2017
						 */
						t.setEmiAmount(String.format("%.2f",emiAmount));
						logger.debug("Emi amount :" + t.getEmiAmount());
						BigDecimal totalAmount = new BigDecimal(emiAmount * Integer.parseInt(t.getTenure()));
						totalAmount = totalAmount.add(processingFee);
						t.setEmiTotalAmount(String.format("%.2f", totalAmount));
						logger.debug("Total amount :" +t.getEmiTotalAmount());
					}else{
						logger.info("Method 1 calculation");
						//In case of MBO manufacture will borrow the rate of interest
						emiAmount = calcEmi(orgAmount.doubleValue(), Double.valueOf(t.getRoi()), Double.valueOf(t.getTenure()));
						/*
						 * changes to add processing fee by ashish.bhavsar
						 */
							// calculate  processing fee in original value
						BigDecimal processingFee = BigDecimal.valueOf(calculateAmountAfterProcessingFee(t, new BigDecimal(input.getOriginalAmount()).movePointLeft(2).doubleValue()));
						t.setPerFeeAmount(String.format("%.02f", processingFee));
						/*
						 * end of changes to add processing fee by ashish.bhavsar
						 */
						t.setEmiAmount(String.format("%.2f",emiAmount));
						BigDecimal totalAmount = new BigDecimal(emiAmount * Integer.parseInt(t.getTenure()));
						t.setEmiTotalAmount(String.format("%.2f", totalAmount));
						totalAmount = calculateOriginalAmountAfterCashBackAmount(totalAmount, input, t, cashbackrows, superObj);
						totalAmount = totalAmount.add(processingFee);
					}
					logger.debug("Tenure : " + t);
					tenObjList.add(t);
					t = null;
/*					if(e.getInstanceDiscData() == null)
						logger.debug("ISSUERDISCLAIMER :" + (String) row.get("ISSUERDISCLAIMER"));
						e.setInstanceDiscData((String) row.get("ISSUERDISCLAIMER"));
*/				}
				
				TenureObject[] tenArr = new TenureObject[tenObjList.size()];
				e.setTenureObject(tenObjList.toArray(tenArr));
				
				input.setDecisionFlag("EMITEN");
				input.setEmiObject(e);
				tenObjList = null;
				tenArr = null;
			}else{
				throw new TCHQueryException(ErrorConstants.TCH_E002,ErrorMaster.get(ErrorConstants.TCH_E002));
			}
		}else{
			logger.debug("Fetching deails for cashback");
			@SuppressWarnings("unchecked")
			List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_CASHBACK_DETAIL");
			TenureObject t = input.getEmiObject().getTenureObject()[0];
			input.getEmiObject().setTenureObject(null);
			List<TenureObject> listTenure = new ArrayList<TenureObject>();
			if(rows != null){
				logger.debug("cash back info :size :" + rows.size());
				for(HashMap<String,Object> row : rows){
					t.setCashBackAmount((String) row.get("CASH_BACK_FLAT_AMOUNT"));
					t.setCashBackFlag((String) row.get("CASH_BACK_FLAG"));
					t.setCashbackPercent((String) row.get("CASH_BACK_PERCENT"));
					t.setCapAmount((String) row.get("CASH_BACK_CAP"));
				}
				listTenure.add(t);
				TenureObject[] tArray = new TenureObject[listTenure.size()];
				input.getEmiObject().setTenureObject(listTenure.toArray(tArray));
			}
		}
		return input;
	}
	
	public void checkProductCodeByAmountComparison(String prodCode, String programCode, String originalAmount, String mid, String tid) throws TCHQueryException{
		String sql = "SELECT * FROM TC_EMI_TABLECODE_004 TE004 LEFT JOIN TC_EMI_TABLECODE_005 TE005 ON TE004.PROGRAM_CODE = TE005.PROGRAM_CODE "
				+ "WHERE TE004.PRODUCT_CODE = ? and TE004.PROGRAM_CODE = ? and TE004.STATUS = 'A' and TE005.MERCHANT_ID = ? and TE005.TERMINAL_ID = ?";
		
		if(Property.isShowSql()){
			logger.debug("Query for fetching information ["+sql+"]" );
		}
		logger.debug("Query parameters : ["+prodCode+", "+programCode+", "+mid+", "+tid+"]");
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,prodCode,programCode,mid,tid);
		if(rows != null && rows.size() >= 1){
			logger.debug("rows size : " + rows.size());
			for(Map<String,Object> row : rows){
				Integer minValue = ((BigDecimal)row.get("PRODUCTMINMRP")).intValue();  
				Integer maxValue = ((BigDecimal)row.get("PRODUCTMAXMRP")).intValue();
				logger.debug("PRODUCTMINMRP ["+minValue+"] : PRODUCTMAXMRP ["+maxValue+"]");
				if(maxValue != null && Integer.parseInt(originalAmount) > maxValue){
					throw new TCHQueryException(ErrorConstants.TCH_E003, ErrorMaster.get(ErrorConstants.TCH_E003));
				}else if(minValue != null && Integer.parseInt(originalAmount) < minValue){
					throw new TCHQueryException(ErrorConstants.TCH_E004, ErrorMaster.get(ErrorConstants.TCH_E004));
				}
			}
		}else{
			logger.error("Error occured while fetching values by Product code");
			throw new TCHQueryException(ErrorConstants.TCH_E011,ErrorMaster.get(ErrorConstants.TCH_E011));
		}
	}
	
	private boolean isExpire(String date){
	    if(date.isEmpty() || date.trim().equals("")){
	        return false;
	    }else{
	    	SimpleDateFormat sdf =  new SimpleDateFormat("dd-MMM-yy");  // changing date format for emi db date
	    	Date d=null;
	    	Date d1=null;
            String today = getToday("dd-MMM-yy").toUpperCase();
            try {
                d = sdf.parse(date);
                d1 = sdf.parse(today);
                if(d1.compareTo(d) < 0){// not expired
                    return false;
                }else if(d.compareTo(d1)==0){// both date are same
                	return false;
                }else{//expired
                    return true;
                }
            } catch (ParseException e) {
                logger.debug("Exception in date expire check :" + e.getMessage());               
                return false;
            }
	    }
	}

	private String getToday(String format){
	     Date date = new Date();
	     return new SimpleDateFormat(format).format(date);
	 }
/**
 * Calculate emi amount
 * @author ashish.bhavsar
 * @param p principal amount
 * @param r rate of interest
 * @param n tenure
 * @return
 */
public Double calcEmi(double p, double r, double n) {
        double R = (r / 12) / 100;
        double e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));
        
        logger.info(" Principal-> " + p);
        logger.info(" Rate of Interest-> " + r);
        logger.info(" Number of Months-> " + n);
        if(R == 0){
        	e = p/n;
        }
        logger.info(" EMI -> " + e +" Tenure :" + n);
        logger.debug("Total Amount -> " + e * n);
       	return e;
        	
}

	public void calcEmiAllMonths(double p, double r, double n) {
	
	        double R = (r / 12) / 100;
	        double P = p;
	        double e = calcEmi(P, r, n);
	        double totalInt = Math.round((e * n) - p);
	        double totalAmt = Math.round((e * n));
	        
	        System.out.println("***************************");
	        System.out.println();
	        System.out.println();
	        System.out.println();
	        System.out.println();
	        System.out.println(" Total Interest -> " + totalInt);
	        System.out.println(" Total Amount -> " + totalAmt);
	        System.out.println("***************************");
	        double intPerMonth = Math.round(totalInt / n);
	
	        for (double i = 1; i <= n; i++) {
	                intPerMonth = (P * R);
	                P = ((P) - ((e) - (intPerMonth)));
	                System.out.println(" Month -> " + (int) i);
	                System.out.println(" Interest per month -> "
	                                + Math.round(intPerMonth));
	                System.out.println(" Principal per month -> "
	                                + Math.round((e) - intPerMonth));
	                System.out.println(" Balance Principal -> " + Math.round(P));
	                System.out.println("***************************");
	        }
	}
	
	/**
	 * Calculation for EMI booking amount basis on the cash back percentage or cash back amount 
	 * And then if subvention is available subtract subvention amount from amount calculated after cashback
	 * finally calculate actual cashback by making calculation for amount after subvention and amount after cashback
	 * 
	 * @author ashish.bhavsar
	 * @param orgAmount in rupess
	 * @param input
	 * @param tenureCode
	 * @param cashbackrows
	 * @return EMI booking amount with cash back applied on it and also subvention applied on it
	 * @throws TCHServiceException 
	 * @throws TCHQueryException 
	 */
	private BigDecimal calculateOriginalAmountAfterCashBackAmount(BigDecimal orgAmount, final Payment input, TenureObject t, List<HashMap<String,Object>> cashbackrows, final SuperMenuObject superObj) throws TCHQueryException{
		String programCode = input.getEmiObject().getProgramObject()[0].getProgramCode();
		BigDecimal bookingAmt = orgAmount;
		BigDecimal totalCashbackPercentage = new BigDecimal(0.00);
		BigDecimal totalCashBackAmount = new BigDecimal(0.00);
		BigDecimal totalSubventionPercentage = new BigDecimal(0.00);
		BigDecimal totalCashBackCap = new BigDecimal(0.00);
		BigDecimal cashBackAmtBasedOnTenureType = new BigDecimal(0.00);
		boolean cashBackApplicable = false;
		Map<String,TenureObject> tenureMap =  superObj.getTenureMap();
		TenureObject tenureObj = t; 
		if(tenureMap == null){
			tenureMap = new HashMap<String, TenureObject>();
		}
		//String tenureCode = input.getEmiObject().getTenureObject()[0].getTenure();
		logger.info("in calculateCashBackAmount :Program code :" + programCode);
		logger.info("in calculateCashBackAmount :Tenure code :" + t.getTenure());
		if(cashbackrows != null){
			for(Map<String, Object> cashbackrow : cashbackrows){
				if(programCode.equals((String)cashbackrow.get("PROGRAM_CODE"))){ // match program code
					if(t.getTenure().equals((String) cashbackrow.get("TENURE_CODE"))){ // match tenure code
						// storing cash back percentage and cash back amount
						String tenureType = (String) cashbackrow.get("TENURE_TYPE");
						BigDecimal cashBack = null;
						if((String) cashbackrow.get("CASH_BACK_PERCENT") != null)
							cashBack = new BigDecimal((String) cashbackrow.get("CASH_BACK_PERCENT"));
						else{
							cashBack = new BigDecimal(0.0);
						}
						String cashBackLowerLimit = (String) cashbackrow.get("CASH_BACK_MIN_AMT_LMT");
						logger.debug("Cahback amt limit in paise[" +cashBackLowerLimit+"]");
						BigDecimal cashBackLimitAmount = new BigDecimal(0.00);
						if(cashBackLowerLimit != null && !cashBackLowerLimit.isEmpty() && !cashBackLowerLimit.equals("0")){
							cashBackLimitAmount = new BigDecimal(cashBackLowerLimit).movePointLeft(2);
							logger.debug("Cahback amt limit in RS[" +cashBackLimitAmount+"]");
						}
						logger.debug("cashBackLimitAmount |" + cashBackLimitAmount+" orgAmount |"+orgAmount);
						if(orgAmount.compareTo(cashBackLimitAmount) >= 0){ // check if cash back min limit is meet then apply cashback
							cashBackApplicable = true;
							if("P".equals((String) cashbackrow.get("CASH_BACK_FLAG"))) { // check for flag P or F
								BigDecimal cashBackPerncetage = new BigDecimal((String) cashbackrow.get("CASH_BACK_PERCENT"));
								logger.debug("Cashback cap limit from db |" + (String) cashbackrow.get("CASH_BACK_CAP"));
								BigDecimal cashBackCap = new BigDecimal((String) cashbackrow.get("CASH_BACK_CAP")).movePointLeft(2);
								totalCashBackCap = totalCashBackCap.add(cashBackCap);
								logger.debug("Cashback cap limit in rs |" + cashBackCap);
								totalCashbackPercentage = totalCashbackPercentage.add(cashBackPerncetage);
								logger.debug("total cashback per |" + totalCashbackPercentage);
								if(cashBackPerncetage != null && cashBackCap != null && cashBackCap.compareTo(BigDecimal.ZERO) > 0 && 
										cashBackPerncetage.compareTo(BigDecimal.ZERO) > 0){
									BigDecimal amount =  cashBackPerncetage.multiply(orgAmount).divide(ONE_HUNDRED);
									if(amount.compareTo(cashBackCap) >= 0){ // check cap condition for each cashback applicable  
										totalCashBackAmount = totalCashBackAmount.add(cashBackCap);  //set cap amount as cashback amount
										cashBackAmtBasedOnTenureType = cashBackCap;
									} else {
										totalCashBackAmount = totalCashBackAmount.add(amount); // set calculated  % amount as cashback amount
										cashBackAmtBasedOnTenureType = amount;
									}
								}
							}else{ // if flat discount instead of percent
								logger.debug("cashback flat amount from db |" + (String) cashbackrow.get("CASH_BACK_FLAT_AMOUNT"));
								BigDecimal cashBackAmt = new BigDecimal((String) cashbackrow.get("CASH_BACK_FLAT_AMOUNT")).movePointLeft(2);
								logger.debug("cashback flat amount in rs |" + cashBackAmt);
								if(orgAmount.compareTo(cashBackAmt) >= 0) 
									totalCashBackAmount = totalCashBackAmount.add(cashBackAmt); // set flat cashback amount as cashnback amount
									cashBackAmtBasedOnTenureType = cashBackAmt;
							}
							switch (tenureType) {
							case "MANUFACTURER":
								tenureObj.setCashBackAmtMfg(String.format("%.2f", cashBackAmtBasedOnTenureType));
								tenureObj.setCashBackPerMfg(String.format("%.2f", cashBack));
								break;
							case "ISSUER":
								tenureObj.setCashBackAmtIssuer(String.format("%.2f", cashBackAmtBasedOnTenureType));
								tenureObj.setCashBackPerIssuer(String.format("%.2f", cashBack));
								break;
							case "MID":
								tenureObj.setCashBackAmtMID(String.format("%.2f", cashBackAmtBasedOnTenureType));
								tenureObj.setCashBackPerMID(String.format("%.2f", cashBack));
								break;
							default:
								break;
							}
						}
					}
				}
			}
			
			// store cash back amount and percentage in cache for future reference
			
			logger.debug("Total cashback percentage ["+totalCashbackPercentage+"]");
			
			tenureObj.setCashBackFinalPer(String.format("%.2f", totalCashbackPercentage));
			//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_CASHBACK_PER+AbstractBrandEmiAdaptor.brdEmiMapping.get(tenureCode), String.format("%.2f", totalCashbackPercentage));
			
			BigDecimal cashBackPercentageAmt =  orgAmount.multiply(totalCashbackPercentage).divide(ONE_HUNDRED);
			cashBackPercentageAmt  = cashBackPercentageAmt.add(totalCashBackAmount);
			logger.debug("Total cashback amount ["+totalCashBackAmount+"]");
			tenureObj.setCashBackFinalAmt(String.format("%.2f", totalCashBackAmount));
			//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_CASHBACK_AMOUNT+AbstractBrandEmiAdaptor.brdEmiMapping.get(tenureCode), String.format("%.2f", totalCashBackAmount));
			tenureObj.setCapAmount(String.format("%.2f", totalCashBackCap));
			logger.debug("Total cashback cap amount" + tenureObj.getCapAmount());
			
			logger.debug("Cash back applicable flag :" + cashBackApplicable);
			if(cashBackApplicable){
				//if(cashBackPercentageAmt < orgAmount){
				if(totalCashBackAmount.compareTo(orgAmount) < 0){
				//	orgAmount = orgAmount.subtract(totalCashBackAmount); // CASHBACK AMOUNT IS NOT USED FOR CALCULATION AS PER ARUN NM 10/11/2017
				}
			}
			logger.debug("Amount after cash back ["+orgAmount+"]");
			
			BigDecimal finalSubventionAmount = new BigDecimal(0.00); 
			if(cashbackrows != null){
				for(Map<String, Object> cashbackrow : cashbackrows){
					if(programCode.equals((String)cashbackrow.get("PROGRAM_CODE"))){ // match program code
						if(t.getTenure().equals((String) cashbackrow.get("TENURE_CODE"))){
							String subVention = (String) cashbackrow.get("SUBVENTION_PER");
							
							String tenureType = (String) cashbackrow.get("TENURE_TYPE");
							// Storing subvention percentage 
							if(subVention != null && !subVention.equals("0")){ // check subvention is present or not
								BigDecimal subventionPer = new BigDecimal(subVention);
								totalSubventionPercentage = totalSubventionPercentage.add(subventionPer);
								BigDecimal subventionAmt = orgAmount.multiply(new BigDecimal(subVention)).divide(ONE_HUNDRED);
								finalSubventionAmount = finalSubventionAmount.add(subventionAmt);
								logger.debug("Subvention percentage ["+subventionPer+"]");
								logger.debug("Subvention amount ["+subventionAmt+"]");
								logger.debug("tenureType :" + tenureType);
								
								switch (tenureType) {
								case "MANUFACTURER":
									tenureObj.setSubventionAmtMfg(String.format("%.2f", subventionAmt));
									tenureObj.setSubventionPerMfg(String.format("%.2f",subventionPer));
									break;
								case "ISSUER":
									tenureObj.setSubventionAmtIssuer(String.format("%.2f", subventionAmt));
									tenureObj.setSubventionPerIssuer(String.format("%.2f",subventionPer));
									break;
								case "MID":
									tenureObj.setSubventionAmtMID(String.format("%.2f", subventionAmt));
									tenureObj.setSubventionPerMID(String.format("%.2f",subventionPer));
									break;
								default:
									break;
								}
										
								//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_SUBVENTION_AMT+tenureType+AbstractBrandEmiAdaptor.brdEmiMapping.get(tenureCode), );
								//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_SUBVENTION+tenureType+AbstractBrandEmiAdaptor.brdEmiMapping.get(tenureCode), );
							}
						}
					}
				}
			}
			// store final subvention amount in  cache for future reference
			tenureObj.setSubventionFinalAmt(String.format("%.2f", finalSubventionAmount));
			tenureObj.setSubventionFinalPer(String.format("%.2f",totalSubventionPercentage));
			//AbstractBrandEmiAdaptor.generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_SUBVENTION_FINAL_AMT+AbstractBrandEmiAdaptor.brdEmiMapping.get(tenureCode), String.format("%.2f", finalSubventionAmount));
			if(orgAmount.compareTo(finalSubventionAmount) > 0){
				logger.debug("Deducting subvention amount from actual amount..");
				orgAmount = orgAmount.subtract(finalSubventionAmount);
			}
			
			logger.debug("final amount after subvention amount deducted [" +orgAmount+"]");
			//get the average of the cashback after all calculation
			//afterCashBackTotalAmount = 9800 orgAmount(aftersubvention) =9604
			logger.debug("bookingAmt :" + bookingAmt + " orgAmount :"  +orgAmount);
			if(bookingAmt.compareTo(orgAmount) > 0){ 
				BigDecimal differenceInAmt = bookingAmt.subtract(orgAmount);
				totalCashbackPercentage = differenceInAmt.divide(ONE_HUNDRED);
				logger.debug("Average cash back % after calculation :" + totalCashbackPercentage);
				//tenureObj.setCashBackFinalPer( String.format("%.2f", totalCashbackPercentage));	NOT USED AS PER ARUN NM
				
				logger.debug("Average cash back amount ["+differenceInAmt+"]");
				//tenureObj.setCashBackFinalAmt(String.format("%.2f", differenceInAmt));		NOT USED AS PER ARUN NM
			}
			tenureMap.put(t.getTenure(),tenureObj);
			superObj.setTenureMap(tenureMap);
		}
		return orgAmount;
	}
	
	private void checkForValidEmiAmount(final Payment input, String emiMin, String emiMax) throws TCHQueryException{
		logger.info("Check for emi valid amount IN PAISE " + "emimin [" + emiMin + "]| emimax [" + emiMax + "] original amount [" + input.getOriginalAmount() +"]");
		
		if(emiMin != null && emiMax != null && !"0".equals(emiMin) && !"0".equals(emiMax) ){
			if(Integer.parseInt(input.getOriginalAmount()) > Integer.parseInt(emiMax)){
				throw new TCHQueryException(ErrorConstants.TCH_E003, ErrorMaster.get(ErrorConstants.TCH_E003));
			}else if(Integer.parseInt(input.getOriginalAmount()) < Integer.parseInt(emiMin)){
				throw new TCHQueryException(ErrorConstants.TCH_E004, ErrorMaster.get(ErrorConstants.TCH_E004));
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_E005, ErrorMaster.get(ErrorConstants.TCH_E005));
		}
	}
	
	/**
	 * calculate processing fee percentage and basis on that final amount
	 * @param input
	 * @param proFeePercentage
	 * @param amount
	 * @return final amount after processing fee percentage applied
	 */
	private static Double calculateAmountAfterProcessingFee(final TenureObject t, Double amount){
		Double proFeeAmount = 0d;
		logger.debug("Prcessing fee percentage ["+t.getPerFeePercentage()+"]");
		logger.debug("Prcessing fee amount ["+t.getPerFeeAmount()+"]");
		if(t.getPerFeePercentage() != null && !t.getPerFeePercentage().isEmpty() && !t.getPerFeePercentage().equals("0")){
			proFeeAmount = (amount * Double.valueOf(t.getPerFeePercentage()))/100;
			//proFeeAmount = BigDecimal.valueOf(proFeeAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			t.setPerFeeAmount(String.format("%.2f", proFeeAmount));
			//amount += proFeeAmount;
			logger.debug("Prcessing fee amount  ["+t.getPerFeeAmount()+"]");
		}else{
			proFeeAmount =  new BigDecimal(t.getPerFeeAmount()).movePointLeft(2).doubleValue();
			logger.debug("Prcessing fee amount  ["+proFeeAmount+"]");
		}
		return proFeeAmount;
	}
	
	
public AxisEpayDTO getExistingTransactionDetails(String urn) throws TCHQueryException {
		//String sql = "select mid,tid from TC_TID_HWSR_MAPPING where hwsrno='676121421111'";//+676121421111+"'";
		String sql = "select A_URN, A_CRN, A_RID, A_RRN, A_AMOUNT, A_CUSTNAME, A_TRASACTIONDATE from TCH_AXISEPAY_REPORT where A_URN='"+urn+"'";
			
		if(Property.isShowSql()){
			logger.debug("Query for fetch CID and RID [" + sql + "]");
		}
		AxisEpayDTO result = null;
		result = getJdbcTemplate().query(sql, new ResultSetExtractor<AxisEpayDTO>(){
		    @Override
		    public AxisEpayDTO extractData(ResultSet rs) throws SQLException ,DataAccessException {
		    	AxisEpayDTO epayDTO = new AxisEpayDTO();
		        while(rs.next()){
		        	epayDTO.setCrn(rs.getString("A_CRN"));
		        	epayDTO.setRid(rs.getString("A_RID"));
		        	epayDTO.setRrn(rs.getString("A_RRN"));
		        	epayDTO.setAmount(rs.getString("A_AMOUNT"));
		        	epayDTO.setUrn(rs.getString("A_URN"));
		          	epayDTO.setCustomerName(rs.getString("A_CUSTNAME"));
		         	epayDTO.setTransactionDate(rs.getString("A_TRASACTIONDATE"));
		        }
		        return epayDTO;
		    }
		});
		if(result == null){
			throw new TCHQueryException(ErrorConstants.TCH_Q001 + "Exception occured in geting MID And TID for serial number : " + urn);
		}
		return result;
	}

	/* SBI E-Pay New Requirement--Ganesh Misal's Changes--START */
	/**
	 * validates Payment card type with payment method
	 * @param ATRN
	 * @param cardType
	 * @throws TCHSbiepayException 
	 * @throws TCHQueryException 
	 */
	private void validateCardType(String ATRN, String cardType) throws TCHSbiepayException, TCHQueryException{
		
		String sql = "select S_PAYMENT_PROCESSOR from TCH_SBIEPAY_REPORT where S_ATRN = ?";
		String paymentProcessor  = "";
		
		
		if(Property.isShowSql())
			logger.debug("Query for getting pcr flag value :" + sql + "[" + ATRN +"]");
		
		try {
			paymentProcessor = getJdbcTemplate().queryForObject(sql, String.class, new Object[]{ATRN});
		}catch(DataAccessException dae){
			dae.getStackTrace();
			logger.debug("Exception in fetching data : " +dae.getMessage());
			throw new TCHQueryException(ErrorConstants.TCH_S009, ErrorMaster.get(ErrorConstants.TCH_S009));
		}catch (Exception e){
			logger.debug("Exception while fetching data :" + e.getMessage());
			throw new TCHQueryException(ErrorConstants.TCH_S009, ErrorMaster.get(ErrorConstants.TCH_S009));
		}
		
		if(null != paymentProcessor) {
			if(null != paymentProcessor && !paymentProcessor.equals(cardType)) {
				logger.debug("Invalid Card Type: " + cardType);
				if("CC".equals(paymentProcessor)) {
					throw new TCHSbiepayException(ErrorConstants.TCH_SB05, "Invalid Card!! Please use Credit Card for payment");
				} else {
					throw new TCHSbiepayException(ErrorConstants.TCH_SB06, "Invalid Card!! Please use Debit Card for payment");
				}
			}
		} else {
			logger.debug("No Data found for ATRN: " +ATRN);
			throw new TCHSbiepayException(ErrorConstants.TCH_SB07, "No Records found");
		}
		
	}
	/* SBI E-Pay New Requirement--Ganesh Misal's Changes--END */

	
	
	public static void main(String[] args) {
		BigDecimal p = new BigDecimal("14.5");
		BigDecimal perAmount = new BigDecimal("10000").multiply(p).divide(ONE_HUNDRED); // convenience fee amount
		Map<String,BigDecimal> taxValues = SaleDaoImpl.applyTax(perAmount,"9","9");
		System.out.println(taxValues.get(Constants.TCH_CGST).toPlainString());
		System.out.println(taxValues.get(Constants.TCH_IGST).toPlainString());
		BigDecimal cgstValue = taxValues.get(Constants.TCH_CGST);
		BigDecimal igstValue = taxValues.get(Constants.TCH_IGST);
		
		BigInteger finalamount = perAmount.add(new BigDecimal("10000")).add(cgstValue).add(igstValue).toBigInteger();
		System.out.println(finalamount);
	}
}