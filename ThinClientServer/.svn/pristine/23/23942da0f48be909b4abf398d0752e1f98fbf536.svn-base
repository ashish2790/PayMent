package com.awl.tch.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.awl.tch.bean.PrintObject;
import com.awl.tch.bean.SummaryReport;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.TerminalParameterCacheMaster;

@Repository("summaryReportDaoImpl")
public class SummaryReportDaoImpl extends GenericDaoImpl<SummaryReport> implements SummaryReportDao {

	private static final Logger logger  = LoggerFactory.getLogger(SummaryReportDaoImpl.class);
	@Override
	public void getRequestWiseCountAndAmount(SummaryReport input)throws TCHQueryException {
		logger.debug("Entering in getRequestWiseCountAndAmount");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outTipAmount = new SqlOutParameter("O_TIP_AMOUNT", Types.VARCHAR);
		SqlParameter outTipCount = new SqlOutParameter("O_TIP_COUNT", Types.VARCHAR);
		
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		SqlParameter outMerchantId= new SqlOutParameter("O_MERCHANT_ID", Types.VARCHAR);
		SqlParameter outTerminalId = new SqlOutParameter("O_TERMINAL_ID", Types.VARCHAR);
		SqlParameter outStartDate= new SqlOutParameter("O_START_DATE", Types.VARCHAR);
		SqlParameter outEndDate = new SqlOutParameter("O_END_DATE", Types.VARCHAR);
		SqlParameter cursorSettlementRequest = new SqlOutParameter("C_SETTLEMENT_DATA_REQUEST", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorSettlementScheme = new SqlOutParameter("C_SETTLEMENT_DATA_SCHEME", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_SUMMARY_REPORT_PROC")
		.declareParameters(inTerminalSerialNumber,outMerchantId,outTerminalId,outStartDate,outEndDate, outSqlCode, outAppErrorCode, outBatchNumber, outDebugPoint, outTipAmount, outTipCount, cursorSettlementRequest,cursorSettlementScheme)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim());
		
		logger.info("Calling store procedure for sale TCH_SUMMARY_REPORT_PROC");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String oTipAmount = (String) output.get("O_TIP_AMOUNT");
		String oTipCount = (String) output.get("O_TIP_COUNT");
		String oStartDate = (String) output.get("O_START_DATE");
		String oEndDate = (String) output.get("O_END_DATE");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("Tip amount from SP :" + oTipAmount);
		logger.info("Tip count from SP :" + oTipCount);
		logger.info("Tip count from SP :" + oStartDate);
		logger.info("Tip count from SP :" + oEndDate);
		
		if(oStartDate != null && oEndDate != null){
			setStartAndEndDate(input,oStartDate, oEndDate);
		}
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode, ErrorMaster.get(appErrorCode));
		}
		if(!"3".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S616, ErrorMaster.get(ErrorConstants.TCH_S616));
		}
		input.setBatchNumber( (String) output.get("O_BATCH_NUMBER"));
		input.setMerchantId( (String) output.get("O_MERCHANT_ID"));
		input.setTerminalId( (String) output.get("O_TERMINAL_ID"));
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("SALE", null);
		map.put("VOID", null);
		map.put("REFUND", null);
		map.put("VOIDREFUND", null);
		//map.put("TOTAL", null);
		
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_SETTLEMENT_DATA_REQUEST");
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows1 = (List<HashMap<String,Object>>) output.get("C_SETTLEMENT_DATA_SCHEME");
		
		List<PrintObject> listPrintObject = new ArrayList<PrintObject>();
		PrintObject printObject  = new PrintObject();
		// set default value for request wise
		printObject.setPrintValueName("BATCH REPORT");
		printObject.setPrintValueCount("NULL");
		printObject.setPrintValueData("NULL");
		listPrintObject.add(printObject);
		
		for(Map<String, Object> row : rows){
			String value= (String.valueOf(((BigDecimal) row.get("COUNT_VAL")).toPlainString())) + ","+
					(String.valueOf(((BigDecimal) row.get("TOTAL_AMOUNT")).toPlainString()));
			map.put((String) row.get("REQUEST_TYPE"), value);
		}
		Long amount = 0L;
		Long countTotal =  0l;
		for(String str : map.keySet()){
			printObject  = new PrintObject();
			printObject.setPrintValueName(str);
			if(map.get(str) != null){
				String printValueCount = map.get(str).split(",")[0];
				String printValueData  = map.get(str).split(",")[1];
				
				if(printValueCount != null){
					/*if("SALE".equals(str)){
						saleAmount = saleAmount + Long.valueOf(printValueData); // storing sale amount need to be display on batch summary charge slip
					}*/
					printObject.setPrintValueCount(printValueCount);
					
					if(!"VOID".equals(str) && !"VOIDREFUND".equals(str) && !"TIP".equals(str))
						countTotal = countTotal + Long.valueOf(printValueCount);
				}else{
					printObject.setPrintValueCount("0");
				}

				if(printValueData != null){
					if("REFUND".equals(str)){
						//while fetching information refund amount already deducted from original sale
						// hence no need to deduct refund amount from original sale
						amount = amount - Long.valueOf(printValueData); 
					} else if("SALE".equals(str)){
						amount = amount + Long.valueOf(printValueData);
					}
					printObject.setPrintValueData((new BigDecimal(printValueData).movePointLeft(2)).toString());
				}else{
					printObject.setPrintValueData("0.00");
				}
				listPrintObject.add(printObject);
			}else{
				printObject.setPrintValueCount("0");
				printObject.setPrintValueData("0.00");
				listPrintObject.add(printObject);
			}
			if(Constants.TCH_SUMMARY_REPORT.equals(input.getDecisionFlag()) || Constants.TCH_Y.equals(input.getHundredTxn())){
				input.setDecisionFlag(Constants.TCH_SETL);
			}
			input.setHundredTxn(null);
			
			// Include refund in sale amount as refund happened on that sale 01/08/2017
			
			/*if(map.get(str) != null)
				printObject.setPrintValueCount(map.get(str).split(",")[0]);
			else
				printObject.setPrintValueCount("0");
			
			if(map.get(str) != null){
				if(str == "REFUND")
					amount = amount - Long.valueOf(map.get(str).split(",")[1]);
				else if(str != "VOID" && str != "VOIDREFUND")
					amount = amount + Long.valueOf(map.get(str).split(",")[1]);
				printObject.setPrintValueData(map.get(str).split(",")[1]);
			}
			else 
				printObject.setPrintValueData("0");
			listPrintObject.add(printObject);*/
		}
		
		/*for(PrintObject printObj : listPrintObject){
			if("SALE".equals(printObj.getPrintValueName())){
				printObj.setPrintValueData((new BigDecimal(saleAmount).movePointLeft(2)).toString());					
			}
		}*/
		
		if(oTipAmount != null && oTipCount != null){
			printObject  = new PrintObject();
			printObject.setPrintValueName("TIP");
			printObject.setPrintValueCount(oTipCount);
			printObject.setPrintValueData(new BigDecimal(oTipAmount).movePointLeft(2).toPlainString());
			listPrintObject.add(printObject);
		}
		
		printObject  = new PrintObject();
		printObject.setPrintValueName("TOTAL");
		printObject.setPrintValueCount(countTotal.toString());
		printObject.setPrintValueData((new BigDecimal(amount.toString()).movePointLeft(2)).toString());
		listPrintObject.add(printObject);
		countTotal = 0l;	
		
		// SET SCHEME WISE VALUES
		printObject  = new PrintObject();
		printObject.setPrintValueName("SCHEME REPORT");
		printObject.setPrintValueCount("NULL");
		printObject.setPrintValueData("NULL");
		listPrintObject.add(printObject);
		
		if(rows != null){
			for(Map<String, Object> row : rows1){
				amount = 0l;
				printObject  = new PrintObject();
				printObject.setPrintValueName((String) row.get("SHEME_NAME"));
				printObject.setPrintValueCount((String.valueOf(((BigDecimal) row.get("COUNT_VAL")).toPlainString())));
				amount  = amount + Long.valueOf(((BigDecimal) row.get("TOTAL_AMOUNT")).toPlainString());
				if((BigDecimal) row.get("TOTAL_REFUND_AMOUNT") != null){
					Long refundAmount = Long.valueOf(((BigDecimal) row.get("TOTAL_REFUND_AMOUNT")).toPlainString());
					//amount  = amount - (2 * refundAmount); // delete 2 times as result got refund amount add in it
					amount  = amount - refundAmount; // correct value printed
				}
				printObject.setPrintValueData((new BigDecimal(amount.toString()).movePointLeft(2)).toString());
					listPrintObject.add(printObject);
			}
		}
		
		int arrSize = rows.size()+rows1.size();
		PrintObject[] printObjArr = new PrintObject[arrSize];
		input.setPrintObject(listPrintObject.toArray(printObjArr));
	}
	
	
	
	@Override
	public void getRequestWiseCountAndAmountNew(SummaryReport input)
			throws TCHQueryException {
		
		//getMidTids(input.getTerminalSerialNumber());
		
		String midtid[] = getMidTids(input.getTerminalSerialNumber(),input.getHostId());
		if(midtid != null && midtid.length > 0){
			input.setMerchantId(midtid[0]);
			input.setTerminalId(midtid[1]);
		}
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter inMid = new SqlParameter("I_MID", Types.VARCHAR);
		SqlParameter inTid = new SqlParameter("I_TID", Types.VARCHAR);
		SqlParameter inHostId = new SqlParameter("I_HOST_ID", Types.VARCHAR);
		SqlParameter inSU99 = new SqlParameter("I_SUMM_FLAG", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outTipAmount = new SqlOutParameter("O_TIP_AMOUNT", Types.VARCHAR);
		SqlParameter outTipCount = new SqlOutParameter("O_TIP_COUNT", Types.VARCHAR);
		SqlParameter outStartDate= new SqlOutParameter("O_START_DATE", Types.VARCHAR);
		SqlParameter outEndDate = new SqlOutParameter("O_END_DATE", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		SqlParameter outFinalCount = new SqlOutParameter("O_FINAL_COUNT", Types.VARCHAR);
		SqlParameter cursorSettlementRequest = new SqlOutParameter("C_SETTLEMENT_DATA_REQUEST", OracleTypes.CURSOR,new ColumnMapRowMapper());
		SqlParameter cursorSettlementScheme = new SqlOutParameter("C_SETTLEMENT_DATA_SCHEME", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_SUMMARY_REPORT_PROC1")
		.declareParameters(inTerminalSerialNumber,inMid,inTid,inHostId,inSU99, outSqlCode,outStartDate,outEndDate, 
				outAppErrorCode, outBatchNumber, outDebugPoint, outTipAmount, outTipCount,outFinalCount, cursorSettlementRequest,cursorSettlementScheme)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		logger.info("I_MID: [" + input.getMerchantId()  +"]" );
		logger.info("I_TID: [" + input.getTerminalId()  +"]" );
		logger.info("I_HOST_ID: [" + input.getHostId()  +"]" );
		logger.info("I_SUMM_FLAG: [" + input.getDecisionFlag()  +"]" );
		
		
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
		.addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim())
		.addValue("I_MID",input.getMerchantId())
		.addValue("I_TID",input.getTerminalId())
		.addValue("I_HOST_ID",input.getHostId())
		.addValue("I_SUMM_FLAG", input.getDecisionFlag());
		
		
		logger.info("Calling store procedure for sale TCH_SUMMARY_REPORT_PROC1");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String oTipAmount = (String) output.get("O_TIP_AMOUNT");
		String oTipCount = (String) output.get("O_TIP_COUNT");
		String oFinalCount = (String) output.get("O_FINAL_COUNT");
		String oStartDate = (String) output.get("O_START_DATE");
		String oEndDate = (String) output.get("O_END_DATE");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("Tip amount from SP :" + oTipAmount);
		logger.info("Tip count from SP :" + oTipCount);
		logger.info("Final count from SP :" + oFinalCount);
		logger.info(" Start date:" + oStartDate);
		logger.info(" End date:" + oEndDate);
		
		if(oStartDate != null && oEndDate != null){
			setStartAndEndDate(input,oStartDate, oEndDate);
		}
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_SETTLEMENT_DATA_REQUEST");
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows1 = (List<HashMap<String,Object>>) output.get("C_SETTLEMENT_DATA_SCHEME");
		
		if(appErrorCode != null){
			if(input.getHostId() != null){
				throw new TCHQueryException(appErrorCode, input.getHostId()+" "+ErrorMaster.get(appErrorCode));
			}else if(oFinalCount != null && "0".equals(oFinalCount)){
				throw new TCHQueryException(appErrorCode, ErrorMaster.get(appErrorCode));
			}else{
				if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()) != null && ErrorConstants.TCH_R202.equals(appErrorCode)
						&& TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isAmexFlag() && input.getHostId() == null){
					logger.debug("continue without error... or checking amex..");
				}else{
					throw new TCHQueryException(appErrorCode, ErrorMaster.get(appErrorCode));
				}
			}
		}
		
		if(Constants.TCH_Y.equals(input.getSettlementSummary())){
			input.setDecisionFlag(null);
		}else{
			// check condition for setting continuity flag and host id
			if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()) != null){
				if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isAmexFlag() && input.getHostId() == null){			// check condition for amex 
					input.setHostId(Constants.TCH_AMEX);
					input.setDecisionFlag(Constants.TCH_SUMMARY_REPORT);
				}else if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isDccFlag() 
						&& (input.getHostId().equals(Constants.TCH_AMEX) || input.getHostId() == null)){ // check condition for dcc
					input.setHostId(Constants.TCH_DCC);
					input.setDecisionFlag(Constants.TCH_END);
				}else if(Constants.TCH_SUMMARY_REPORT.equals(input.getDecisionFlag())){
					input.setDecisionFlag(Constants.TCH_ONL_SETL);
				}
				else{
					if(input.getHostId() != null)
						input.setDecisionFlag(Constants.TCH_END);
					else
						input.setDecisionFlag(null);
				}
			}else if(Constants.TCH_SUMMARY_REPORT.equals(input.getDecisionFlag())){
				input.setDecisionFlag(Constants.TCH_ONL_SETL); 
			}else{
				input.setDecisionFlag(Constants.TCH_END);
			}
		}
		
		
		if(!"3".equalsIgnoreCase(oDebugPoint)){
			if(TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()) != null &&
					(Constants.TCH_1BA.equals(oDebugPoint) || Constants.TCH_1A.equals(oDebugPoint))
					&& TerminalParameterCacheMaster.terminalParameterCache.get(input.getTerminalSerialNumber()).isAmexFlag()){
				logger.debug("avoid exception....");
			}else{
				throw new TCHQueryException(ErrorConstants.TCH_S616, ErrorMaster.get(ErrorConstants.TCH_S616));
			}
		}
		input.setBatchNumber((String) output.get("O_BATCH_NUMBER"));
		
		
		
		
		//map.put("TOTAL", null);
		
		
		
		
		if(rows != null && rows.size() > 0){
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("SALE", null);
			map.put("VOID", null);
			map.put("REFUND", null);
			map.put("VOIDREFUND", null);
			
			List<PrintObject> listPrintObject = new ArrayList<PrintObject>();
			PrintObject printObject  = new PrintObject();
			// set default value for request wise
			printObject.setPrintValueName("BATCH REPORT");
			printObject.setPrintValueCount("NULL");
			printObject.setPrintValueData("NULL");
			listPrintObject.add(printObject);
			
			for(Map<String, Object> row : rows){
				String value= (String.valueOf(((BigDecimal) row.get("COUNT_VAL")).toPlainString())) + ","+
						(String.valueOf(((BigDecimal) row.get("TOTAL_AMOUNT")).toPlainString()));
				map.put((String) row.get("REQUEST_TYPE"), value);
			}
			Long amount = 0L;
			Long countTotal =  0l;
			for(String str : map.keySet()){
				printObject  = new PrintObject();
				printObject.setPrintValueName(str);
				if(map.get(str) != null){
					String printValueCount = map.get(str).split(",")[0];
					String printValueData  = map.get(str).split(",")[1];
					
					if(printValueCount != null){
						printObject.setPrintValueCount(printValueCount);
						if(!"VOID".equals(str) && !"VOIDREFUND".equals(str) && !"TIP".equals(str))
							countTotal = countTotal + Long.valueOf(printValueCount);
					}else{
						printObject.setPrintValueCount("0");
					}

					if(printValueData != null){
						if("REFUND".equals(str)){
							//while fetching information refund amount already deducted from original sale
							// hence no need to deduct refund amount from original sale
							amount = amount - Long.valueOf(printValueData); 
						} else if("SALE".equals(str)){
							amount = amount + Long.valueOf(printValueData);
						}
						printObject.setPrintValueData((new BigDecimal(printValueData).movePointLeft(2)).toString());
					}else{
						printObject.setPrintValueData("0.00");
					}
					listPrintObject.add(printObject);
				}else{
					printObject.setPrintValueCount("0");
					printObject.setPrintValueData("0.00");
					listPrintObject.add(printObject);
				}
			}
			
			if(oTipAmount != null && oTipCount != null){
				printObject  = new PrintObject();
				printObject.setPrintValueName("TIP");
				printObject.setPrintValueCount(oTipCount);
				printObject.setPrintValueData(new BigDecimal(oTipAmount).movePointLeft(2).toPlainString());
				listPrintObject.add(printObject);
			}
			
			printObject  = new PrintObject();
			printObject.setPrintValueName("TOTAL");
			printObject.setPrintValueCount(countTotal.toString());
			printObject.setPrintValueData((new BigDecimal(amount.toString()).movePointLeft(2)).toString());
			listPrintObject.add(printObject);
			countTotal = 0l;	
			
			// SET SCHEME WISE VALUES
			printObject  = new PrintObject();
			printObject.setPrintValueName("SCHEME REPORT");
			printObject.setPrintValueCount("NULL");
			printObject.setPrintValueData("NULL");
			listPrintObject.add(printObject);
			
			if(rows != null){
				for(Map<String, Object> row : rows1){
					amount = 0l;
					printObject  = new PrintObject();
					printObject.setPrintValueName((String) row.get("SHEME_NAME"));
					printObject.setPrintValueCount((String.valueOf(((BigDecimal) row.get("COUNT_VAL")).toPlainString())));
					amount  = amount + Long.valueOf(((BigDecimal) row.get("TOTAL_AMOUNT")).toPlainString());
					if((BigDecimal) row.get("TOTAL_REFUND_AMOUNT") != null){
						Long refundAmount = Long.valueOf(((BigDecimal) row.get("TOTAL_REFUND_AMOUNT")).toPlainString());
						//amount  = amount - (2 * refundAmount); // delete 2 times as result got refund amount add in it
						amount  = amount - refundAmount; // correct value printed
					}
					printObject.setPrintValueData((new BigDecimal(amount.toString()).movePointLeft(2)).toString());
						listPrintObject.add(printObject);
				}
			}
			
			int arrSize = rows.size()+rows1.size();
			PrintObject[] printObjArr = new PrintObject[arrSize];
			input.setPrintObject(listPrintObject.toArray(printObjArr));
		}
	}
}
