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

import com.awl.tch.bean.ImagePrint;
import com.awl.tch.bean.PromoObject;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.UtilityHelper;

@Repository("imageDaoImpl")
public class ImageDaoImpl implements ImageDao {
	private static Logger logger = LoggerFactory.getLogger(ImageDaoImpl.class);
	
	public void getBankLogo(final ImagePrint input) throws TCHQueryException{
		
		logger.debug("Inside get logo dao");
		
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_TERMINAL_SERIAL_NUMBER", Types.VARCHAR);
		SqlParameter outBytes = new SqlOutParameter("O_BYTE_RANGE", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		
		SqlParameter cursorBankInfo = new SqlOutParameter("C_BANK_INFO", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_BANK_INFO")
		.declareParameters(inTerminalSerialNumber,outBytes,outSqlCode,outAppErrorCode,outDebugPoint,cursorBankInfo)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_TERMINAL_SERIAL_NUMBER",input.getTerminalSerialNumber().trim());
		
		logger.info("Calling store procedure for sale TCH_BANK_INFO	");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		Integer byteRange = Integer.parseInt((String) output.get("O_BYTE_RANGE"));
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);logger.info("Byte range :" + byteRange);
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_BANK_INFO");
		Integer i = 0; 
		if(input.getPacketNumber() != null)
		 i = Integer.parseInt(input.getPacketNumber());
		else 
			i = 0; 
		if(rows != null){
			for(HashMap<String,Object> row : rows){
				byte[] bytes = (byte[])  row.get("B_BANK_LOGO");
				int startIndex = i * byteRange;
				int endIndex = (i+1) * byteRange;
				logger.debug("start index : " + startIndex);
				logger.debug("end index : " + endIndex);
				String hexString = UtilityHelper.byteArrayToHexString(bytes);
				logger.debug("Length : " + hexString.length());
				PromoObject prtObj = new PromoObject();
				if(hexString.length() > endIndex){
					prtObj.setPrintBMP(hexString.substring(startIndex, endIndex));
					input.setDecisionFlag("CONT");
				}
				else{
					prtObj.setPrintBMP(hexString.substring(startIndex, hexString.length()));
					input.setDecisionFlag(null);
				}
				input.setPrintPromoObject(prtObj);
			}
		}
		
		input.setPacketNumber(null);
		input.setDate(null);
		input.setTerminalSerialNumber(null);
		input.setClientId(null);
		input.setTime(null);
		logger.debug("exiting get logo dao");
	}
}
