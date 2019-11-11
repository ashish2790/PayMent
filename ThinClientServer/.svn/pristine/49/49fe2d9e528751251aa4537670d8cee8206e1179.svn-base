package com.awl.tch.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import com.awl.tch.constant.Constants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.SbiepayDTO;
import com.awl.tch.server.TcpServer;

@Repository(Constants.TCH_REPORT_DAO)
public class ReportDaoImpl extends GenericDaoImpl<SbiepayDTO>{

	private static Logger logger = LoggerFactory.getLogger(ReportDaoImpl.class);
	
	public List<StringBuilder> getTransactionDetails(String appName, String date) throws TCHServiceException{
		
		logger.debug("Entering in getTransaction details");
		
		SqlParameter inAppName = new SqlParameter("I_APP_NAME", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		
		SqlParameter cursorReportDetail = new SqlOutParameter("C_REPORT_DETAIL", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
		
		simpleJdbcCall.withProcedureName("TCH_REPORT")
		.declareParameters(inAppName, outSqlCode, outAppErrorCode, outDebugPoint, cursorReportDetail)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_APP_NAME", appName); 
		
		logger.info("Calling store procedure for sale TCH_REPORT");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		
		logger.debug("sqlCode :" + sqlCode);
		logger.debug("appErrorCode :" + appErrorCode);
		logger.debug("oDebugPoint :" + oDebugPoint);
		if(appErrorCode != null){
			throw new TCHServiceException("RE-01", "Error while generating report");
		}
		List<HashMap<String,Object>> rows = (List<HashMap<String, Object>>) output.get("C_REPORT_DETAIL");
		@SuppressWarnings("unchecked")
		List<StringBuilder> data = Collections.EMPTY_LIST;
		if(rows != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
			data= new ArrayList<StringBuilder>();
			for(Map row : rows){
				StringBuilder sb = new StringBuilder("CLIENT_REPORT DT.");
				sb.append("|"+(String)row.get("S_RRN") != null?(String)row.get("S_RRN") :" ")
				.append("|"+(String) row.get("S_ATRN")).append("|SBIePAY");
				if((String)row.get("S_AMOUNT") != null)
					sb.append("|"+new BigDecimal((String)row.get("S_AMOUNT")).movePointLeft(2).toPlainString());
				try {
					String tempdate = ((String)row.get("S_TRASACTIONDATE")).split(" ")[0];
					String time = ((String)row.get("S_TRASACTIONDATE")).split(" ")[1];
					sb.append("|"+sdf1.format(sdf.parse(tempdate)) +" "+time);
				} catch (ParseException e) {
					logger.debug("Exception while getting date from transaction ATRN[" + (String) row.get("S_ATRN")+"]");
				}
				data.add(sb);
			}
		}
		return data;
	}
}
