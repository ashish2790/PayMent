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

import com.awl.tch.bean.Carddetail;
import com.awl.tch.bean.Carddetail.CardDetailData;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.CardDetailDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
/**
* <h1>Database layer class</h1>
* CardDetailsDaoImpl program is used to do all
* <tt>CRUD</tt> related operation for storing the information in database.
* In which its going to store the information related to <tt>CARD DETAILS</tt>.
*  
* 
* <p>
* 1. For calling store procedure related to get data based on the <tt>Terminal Serial Number</tt>.
*
* @author  Ashish Bhavsar
* @version 1.0
* @since   2016-09-21
*/
@Repository("cardDetailDao")
public class CardDetailsDaoImpl extends GenericDaoImpl<CardDetailDTO> implements CardDetailDao {
	
	private static final Logger logger = LoggerFactory.getLogger(CardDetailsDaoImpl.class);
	
	@Override
	public void getCardDetails(Carddetail input) throws TCHQueryException {
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_ERROR_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		
		SqlParameter cursorTerminalParameter = new SqlOutParameter("C_CARD_DETAILS", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_CARD_DETAILS_PROC")
		.declareParameters(inTerminalSerialNumber,outSqlCode,outAppErrorCode,outDebugPoint,cursorTerminalParameter)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + input.getTerminalSerialNumber()  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber()); 
		
		logger.info("Calling store procedure for card details TCH_CARD_DETAILS_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_ERROR_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		
		if(appErrorCode != null && appErrorCode.startsWith("C")){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		if(!"2".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
		}
		
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> cardDetailsRows = (List<HashMap<String,Object>>) output.get("C_CARD_DETAILS");
		List<CardDetailDTO> listCardDetailsRows = new ArrayList<CardDetailDTO>();
		
		if(cardDetailsRows != null){
			for(Map<String,Object> row : cardDetailsRows){
				CardDetailDTO cardDto = new CardDetailDTO();
				cardDto.setApplicationId((String) row.get("NFC_AID"));
				cardDto.setEmvTagOnline((String) row.get("EMV_TAC_ONLINE"));
				cardDto.setEmvTacDefault((String) row.get("EMV_TAC_DEFAULT"));
				cardDto.setEmvTagDecline((String) row.get("EMV_TAC_DECLINE"));
				cardDto.setNfcTacOnline((String) row.get("NFC_TAC_ONLINE"));
				cardDto.setNfcTacDefault((String) row.get("NFC_TAC_DEFAULT"));
				cardDto.setNfcTacDecline((String) row.get("NFC_TAC_DECLINE"));
				cardDto.setIsNFC((String) row.get("ISNFC"));
				cardDto.setEmvACK((String) row.get("ISEMVACK")); 
				//cardDto.setEmvFloorLimit((String)row.get("EMV_FLOORLIMIT"));
				if((BigDecimal) row.get("TARGET") != null)
					cardDto.setTarget(((BigDecimal) row.get("TARGET")).toPlainString());
				if((BigDecimal) row.get("THRESHOLDVALUE") != null)
					cardDto.setThresholdValue(((BigDecimal) row.get("THRESHOLDVALUE")).toPlainString());
				if((BigDecimal) row.get("MAXTARGET") != null)
					cardDto.setMaxTarget(((BigDecimal) row.get("MAXTARGET")).toPlainString());
				cardDto.setNfcCtlsFloorLimit((String) row.get("NFC_CTLS_FLOOR_LIMIT"));
				cardDto.setNfcOdvcTransactionLimit((String) row.get("NFC_ODVC_TRNS_LIMIT"));
				cardDto.setNfcAidActivationFlag((String) row.get("NFC_AIDACTIVATION_FLAG"));
				cardDto.setNfcNonOdvcTransactionLimit((String) row.get("NFC_NON_ODVC_TRNS_LIMIT"));
				cardDto.setNfcCtlsCvmrequiredLimit((String) row.get("NFC_CTLS_CVM_REQ_LIMIT"));
				cardDto.setCardLabel((String) row.get("CARD_LABEL"));
				listCardDetailsRows.add(cardDto);
			}
			logger.debug("Exiting from setting cardDetails DTO : " + listCardDetailsRows.size());
			
			Integer i = Integer.valueOf(input.getPacketNumber().trim());
			Integer size = listCardDetailsRows.size();
			if(i != (size-1)){
				input.setDesFlag("CONT");
			}else{
				input.setDesFlag(null);
			}
			CardDetailData[] cardDataArray  = new CardDetailData[1];
			CardDetailData cardData = input.new CardDetailData();
			//if("Y".equals(listCardDetailsRows.get(i).getNfcAidActivationFlag())){
				cardData.setIsNFC(listCardDetailsRows.get(i).getIsNFC());
				cardData.setApplicationId(listCardDetailsRows.get(i).getApplicationId());
				cardData.setCtlsFloorLimit(listCardDetailsRows.get(i).getNfcCtlsFloorLimit());
				cardData.setNonOdvcTransactionLimit(listCardDetailsRows.get(i).getNfcNonOdvcTransactionLimit());
				cardData.setOdvcTransactionLimit(listCardDetailsRows.get(i).getNfcOdvcTransactionLimit());
				cardData.setCvmLimits(listCardDetailsRows.get(i).getNfcCtlsCvmrequiredLimit());
				cardData.setMaxTargetPercent(listCardDetailsRows.get(i).getMaxTarget());
				if("Y".equals(listCardDetailsRows.get(i).getIsNFC())){
					cardData.setTacDefault(listCardDetailsRows.get(i).getNfcTacDefault());
					cardData.setTacOffline(listCardDetailsRows.get(i).getNfcTacDecline());
					cardData.setTacOnline(listCardDetailsRows.get(i).getNfcTacOnline());
					}
				else{
					cardData.setTacDefault(listCardDetailsRows.get(i).getEmvTacDefault());
					cardData.setTacOffline(listCardDetailsRows.get(i).getEmvTagDecline());
					cardData.setTacOnline(listCardDetailsRows.get(i).getEmvTagOnline());
					
				}
				cardData.setTargetPercent(listCardDetailsRows.get(i).getTarget());
				cardData.setThresholdVal(listCardDetailsRows.get(i).getThresholdValue());
				cardData.setEmvACK(listCardDetailsRows.get(i).getEmvACK());
				cardDataArray[0] = cardData;
			//}else{
				
			//}
			
			
			input.setCardDetailObject(cardDataArray);
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_C101, ErrorMaster.get(ErrorConstants.TCH_C101));
		}
	}
	
	public static void main(String[] args) {
		List<String> sa = new ArrayList<String>();
		
		//sa.add("asdf");
		
		System.out.println(sa.size());
	}
}
