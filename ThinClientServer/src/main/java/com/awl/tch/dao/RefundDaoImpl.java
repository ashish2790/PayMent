package com.awl.tch.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.constant.TchProcConstant;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.model.WalletDTO;
import com.awl.tch.server.TcpServer;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;
import com.awl.tch.util.UtilityHelper;

import oracle.jdbc.OracleTypes;

@Repository("refundDaoImpl")
public class RefundDaoImpl extends GenericDaoImpl<PaymentDTO> implements RefundDao{

	private static final Logger logger = LoggerFactory.getLogger(RefundDaoImpl.class);

	@Override
	public PaymentDTO getExistingDTO(String rrn) throws TCHQueryException {
		logger.debug("Inside getExistingDTO");
		SqlParameter inTerminalSerialNumber = new SqlParameter("I_RRN", Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outTableId = new SqlOutParameter("O_TABLE_ID", Types.VARCHAR);
		SqlParameter outBatchNumber = new SqlOutParameter("O_BATCH_NUMBER", Types.VARCHAR);
		
		SqlParameter cursorExistingPaymentDTO = new SqlOutParameter("C_PAYMENT_DATA", OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
			
		simpleJdbcCall.withProcedureName("TCH_REFUND_PROC")
		.declareParameters(inTerminalSerialNumber,outSqlCode,
				outAppErrorCode,outDebugPoint,outTableId,outBatchNumber,cursorExistingPaymentDTO)
		.withoutProcedureColumnMetaDataAccess()
		.compile();
		
		logger.info("I_RRN: [" + rrn  +"]" );
		
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_RRN",rrn); 
		
		logger.info("Calling store procedure for sale TCH_REFUND_PROC ");
		
		Map<String,Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String tableId = (String) output.get("O_TABLE_ID");
		String batchnumber = (String) output.get("O_BATCH_NUMBER");
		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("oTableId from SP :" + tableId);
		logger.info("outBatchNumber from SP :" + batchnumber);
		
		//tableId = tableId !=null ? tableId.substring(3) : null;
		// Commented as it is not required in refund only U indicator will be pass in iso
		/*if(tableId != null && input.getPinBlock() == null){
			// check if pin block from terminal is blank and pin is mandatory 12/04/2017 by ashish
			if(!tableId.contains(Constants.TCH_Y)){
				throw new TCHQueryException(ErrorConstants.TCH_S018, ErrorMaster.get(ErrorConstants.TCH_S018));
			}
			
		}*/
		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		if(!"2".equalsIgnoreCase(oDebugPoint)){
			throw new TCHQueryException(ErrorConstants.TCH_S200,ErrorMaster.get(ErrorConstants.TCH_S200));
		}
		List<PaymentDTO> p = new ArrayList<PaymentDTO>(1);
		 
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_PAYMENT_DATA");
		if(rows != null){
			p = UtilityHelper.getPaymentDTO(rows);
			 
			PaymentDTO temp = p.get(0);
			temp.setTempBatchNumber(temp.getBatchNumber());
			if(batchnumber != null)
				temp.setBatchNumber(Integer.parseInt(batchnumber));
			temp.setTableId(tableId);
			return temp;
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_R010,ErrorMaster.get(ErrorConstants.TCH_R010));
		}
	}
	
	/*
	 * change by Saloni Jindal on 21/11/2017
	 */
	
public PaymentDTO getExistRefundParamter(final Payment input) throws TCHServiceException, TCHQueryException{
		
		logger.debug("Entering in getExistRefundParamter");
		
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
		
		simpleJdbcCall.withProcedureName(TchProcConstant.TCH_REFUND_ACK_PROC)
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
				 
		
		logger.info("Calling store procedure for refund TCH_REFUND_ACK_PROC ");
		
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
		List<HashMap<String,Object>> rows = (List<HashMap<String,Object>>) output.get("C_EXST_REFUND_PARAMETER");
		
		PaymentDTO paymentDto  = new PaymentDTO();
		
		if(rows != null){
			List<PaymentDTO> listp =UtilityHelper.getPaymentDTO(rows);
			if(listp != null){
				logger.debug("Found actual refund transaction approved.");
				paymentDto = listp.get(0);
			}
			else
				return null;
		}else{
			logger.debug("Rows got null from TCH_REFUND_ACK_PROC");
			return null;
		}
		return paymentDto;
		
	}
	
	/*
	 * end of change by Saloni Jindal on 21/11/2017
	 */

	@Override
	public PaymentDTO getPaymentDetails(String rrn) throws TCHQueryException {
		String query = "SELECT * FROM TCH_SETTLED_PAYMENT_TXN WHERE P_RETRIVAL_REF_NUMBER = ?";
		
		if(Property.isShowSql()){
			logger.debug("SQL :" + query + "["+rrn+"]");
		}
		List<Map<String,Object>> rows =  getJdbcTemplate().queryForList(query,rrn);
		List<PaymentDTO> listPayment = UtilityHelper.getPaymentDTO(rows);
		
		if(listPayment != null && listPayment.size() != 1){
			logger.debug("Size : "+listPayment.size());
			throw new TCHQueryException("QR-01","RRN NOT FOUND");
		}
		
		return listPayment.get(0);
	}

	@Override
	public WalletDTO getExistingDTOFc(String rrn, String termSrNo) throws TCHQueryException {
		logger.debug("Inside getExistingDTOFc()");
		SqlParameter inRRN=new SqlParameter("I_RRN",Types.VARCHAR);
		SqlParameter inTerminalSrNo=new SqlParameter("I_TERMINAL_SERIAL_NUMBER",Types.VARCHAR);
		SqlParameter outSqlCode = new SqlOutParameter("O_SQL_CODE", Types.VARCHAR);
		SqlParameter outAppErrorCode = new SqlOutParameter("O_APP_ERROR_CODE", Types.VARCHAR);
		SqlParameter outDebugPoint = new SqlOutParameter("O_DEBUG_POINT", Types.VARCHAR);
		SqlParameter outIsVoid = new SqlOutParameter("O_ISVOID", Types.VARCHAR);
		SqlParameter refundDataFcCursor = new SqlOutParameter("C_REFUND_DATA_WALLET",OracleTypes.CURSOR,new ColumnMapRowMapper());
		
		
		DataSource ds = (DataSource) TcpServer.getContext().getBean("dataSource");
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(ds);
	
		simpleJdbcCall.withProcedureName("TCH_REFUND_WALLET_PROC")
		.declareParameters(inRRN,inTerminalSrNo,outSqlCode,outAppErrorCode,outDebugPoint,outIsVoid,refundDataFcCursor)
		.withoutProcedureColumnMetaDataAccess().compile();
		
		
		logger.info("I_RRN: [" + rrn  +"]" );
		
		logger.info("I_TERMINAL_SERIAL_NUMBER: [" + termSrNo +"]");

		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("I_RRN", rrn).addValue("I_TERMINAL_SERIAL_NUMBER", termSrNo);
		logger.info("Calling store procedure for TCH_REFUND_WALLET_PROC ");
		
		Map<String, Object> output = simpleJdbcCall.execute(parameterSource);
		
		String sqlCode = (String) output.get("O_SQL_CODE");
		String appErrorCode = (String) output.get("O_APP_ERROR_CODE");
		String oDebugPoint = (String) output.get("O_DEBUG_POINT");
		String isVoid = (String) output.get("O_ISVOID");

		
		logger.info("SqlCode from Sp :" + sqlCode);
		logger.info("App Error Code from SP :" + appErrorCode);
		logger.info("Debug Point from SP :" + oDebugPoint);
		logger.info("IsVoid Flag from SP :" + isVoid);

		
		if(appErrorCode != null){
			throw new TCHQueryException(appErrorCode,ErrorMaster.get(appErrorCode));
		}
		
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> rows = (List<HashMap<String, Object>>) output.get("C_REFUND_DATA_WALLET");
		
		if(rows != null){
			if( isVoid.equals("Y")){
			List<WalletDTO> p = UtilityHelper.getWalletDTOFc(rows);
			if(p != null){
				WalletDTO temp = p.get(0);
				return temp;
			}
			else
				throw new TCHQueryException(ErrorConstants.TCH_R010, ErrorMaster.get(ErrorConstants.TCH_R010));
			}
			else{
				List<WalletDTO> s = UtilityHelper.getSettledDTOFc(rows);
				if(s != null){
					WalletDTO temp = s.get(0);
					return temp;
				}else
					throw new TCHQueryException(ErrorConstants.TCH_R010, ErrorMaster.get(ErrorConstants.TCH_R010));
				}
			}
		else{
			logger.debug("Rows got null from TCH_REFUND_WALLET_PROC");
			throw new TCHQueryException(ErrorConstants.TCH_R010, ErrorMaster.get(ErrorConstants.TCH_R010));
		}
	}

	@Override
	public void updateSettledPayment(Payment input, String rrn, String refundAppFlag) throws TCHQueryException {
		// TODO Auto-generated method stub
		String query = "update TCH_SETTLED_PAYMENT_TXN set P_REFUND_APPROVED = ? where P_MERCHANTID = ? and P_TERMINALID = ? and P_RETRIVAL_REF_NUMBER = ?";
		if(Property.isShowSql()){
			logger.info(query);
		}
		getJdbcTemplate().update(query,new Object[] {refundAppFlag,input.getMerchantId(),input.getTerminalId(),rrn});
		logger.debug("Refund flag marked as "+refundAppFlag);
	}
	
	
	
}
