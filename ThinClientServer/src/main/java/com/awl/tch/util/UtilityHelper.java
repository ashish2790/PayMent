package com.awl.tch.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.awl.tch.aab.model.AABEntity;
import com.awl.tch.annotation.Mandate;
import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.dao.AbstractGenericDao;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentAuthDTO;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.model.WalletDTO;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;

public class UtilityHelper {


	private UtilityHelper(){}
	private static final Logger logger  = LoggerFactory.getLogger(UtilityHelper.class);

	private static ConcurrentHashMap<String, String> cardEntryModeMap = new ConcurrentHashMap<String, String>();
	private static ConcurrentHashMap<String, String> accountEntryModeMap = new ConcurrentHashMap<String, String>();
	public static void load(){
		/*
		 * 	KEYENTER(11),
			SWIPE(21),
			EMV(51),
			EMVFALLBK(801),
			CTLS(71),
			CTLSSWIPE(911);
		 */
		if(cardEntryModeMap.size() == 0){
			cardEntryModeMap.put("11", Constants.TCH_KEYENTER);
			cardEntryModeMap.put("21", Constants.TCH_SWIPE);
			cardEntryModeMap.put("51", Constants.TCH_EMV);
			cardEntryModeMap.put("801", Constants.TCH_EMVFALLBK);
			cardEntryModeMap.put("71", Constants.TCH_CTLS);
			cardEntryModeMap.put("911", Constants.TCH_CTLSW);
			cardEntryModeMap.put("CC", "C");
			cardEntryModeMap.put("DC", "D");
		}
		
		
		/*
		 *	SAVING("1000"),
			CURRENT("1000"),
			CHECKING("2000"),
			CREDIT_FACILIT("3000"),
			UNIVERSAL_ACC("4000");
			
		*/
		if(accountEntryModeMap.size() == 0){
			accountEntryModeMap.put("1000", Constants.TCH_SAVING);
			accountEntryModeMap.put("2000", Constants.TCH_CHECKING);
			accountEntryModeMap.put("3000", Constants.TCH_CREDIT_FACILIT);
			accountEntryModeMap.put("4000", Constants.TCH_UNIVERSAL_ACC);
		}
	}
	private static final ThreadLocal<SimpleDateFormat> sdfDate = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("ddMMyyyy");
		}
	};
	
	private static final ThreadLocal<SimpleDateFormat> sdfDateHyphen = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("dd-MM-yyyy");
		}
	};
	private static final ThreadLocal<SimpleDateFormat> sdfddMMMyyHHmmss = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("dd-MMM-yy hh.mm.ss");
		}
	};
	private static final ThreadLocal<SimpleDateFormat> sdfHour = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HHmmss");
		}
	};
	private static final ThreadLocal<SimpleDateFormat> sdfHHmmss = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss");
		}
	};
	 
	private static final ThreadLocal<SimpleDateFormat> sdfMMYY = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MMYY");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> sdfMMddyyyy = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MMddyyyy");
		}
	};
	
	public static final ThreadLocal<SimpleDateFormat> sdfdd_mm_yyyy = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("dd/MM/yyyy");
		}
	};
	
	public static final ThreadLocal<SimpleDateFormat> sdfyyMMdd = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyMMdd");
		}
	};
	
	public static String dateInMMddYYYY(){
		Date d = new Date();
		String date = sdfdd_mm_yyyy.get().format(d);
		return date;
	}
	
	public static String dateIndddMMYYYYhyphen(){
		Date d = new Date();
		String date = sdfDateHyphen.get().format(d);
		return date;
	}
	
	public static String dateInddMMyyyy(){
		Date d = new Date();
		String date = sdfDate.get().format(d);
		return date;
	}

	public static String hourInHHmmss(){
		Date d = new Date();
		String date = sdfHour.get().format(d);
		return date;
	}

	public static String MMYY(){
		Date d = new Date();
		String date = sdfMMYY.get().format(d);
		return date;
	}
	
	/**
	 * Convert time stamp and get date and time 
	 * @param fromTable
	 * @return
	 * @throws ParseException
	 */
	public static String timestampInddMMyyyy_HHmmss(String fromTable) throws ParseException{
		StringBuilder input = new StringBuilder()
		.append("DATE :")
		.append(sdfdd_mm_yyyy.get().format(sdfddMMMyyHHmmss.get().parse(fromTable.toString())))
		.append(" TIME :")
		.append(sdfHHmmss.get().format(sdfddMMMyyHHmmss.get().parse(fromTable.toString())));
		return input.toString();
	}
	/**
	 * Get the payment dto list fetched from DB
	 * @author ashish.bhavsar
	 * @param rows
	 * @return List<PaymentDTO>
	 */
	public static List<PaymentDTO> getPaymentDTO(List<? extends Map<String, Object>> rows){
		List<PaymentDTO> listpaymentDTO = new ArrayList<PaymentDTO>();
		for(Map<String, Object> row : rows){
			PaymentDTO p = new PaymentDTO();
			p.setPaymentId(((BigDecimal) row.get("P_ID")).longValue());
			p.setAccountType((String) row.get("P_ACCOUNT_TYPE"));
			p.setAdditionalAmount(((BigDecimal) row.get("P_ADDAMOUNT")).doubleValue());
			p.setAuthorizationId((String) row.get("P_AUTH_ID"));
			p.setBatchNumber(((BigDecimal) row.get("P_BATCH_NUMBER")).intValue());
			p.setBinNumber((String) row.get("P_BINNUMBER"));
			p.setCardEntryMode((String) row.get("P_CARD_ENTRYMODE"));
			p.setCardLabel((String) row.get("P_CARD_LABEL"));
			p.setClientId((String) row.get("P_CLIENTID"));
			p.setRequestType((String) row.get("P_REQUEST_TYPE"));
			p.setCurrencyCode((Integer.valueOf((String)row.get("P_CURRENCY_CODE"))));
			p.setCurrencyName((String) row.get("P_CURRENCY_NAME"));
			p.setDccAmount(((BigDecimal) row.get("P_DCC_AMOUNT")).doubleValue());
			p.setDecisionFlag((String) row.get("P_DECISION_FLAG"));
			p.setExchangeRate((Double.valueOf((String) row.get("P_EXCHANGE_RATE"))));
			p.setExpiryDate((String) row.get("P_EXPIRYDATE"));
			p.setField55((String) row.get("P_FIELD55"));
			p.setIssuerField55((String) row.get("P_ISSUE_FIELD55"));
			p.setInvoiceNumber((String)row.get("P_INVOICENUMBER"));
			p.setMarkup((String) row.get("P_MARKUP"));
			p.setMerchantId((String) row.get("P_MERCHANTID"));	
			p.setOriginalAmount((Double.valueOf((String) row.get("P_ORIGINAL_AMOUNT"))));
			p.setPanNumber((String) row.get("P_PANNUMBER"));
			p.setPinBlock((String) row.get("P_PIN_BLOCK"));
			p.setSaleCompletionFlag((String) row.get("P_SALE_COMP_FLAG"));
			p.setReferenceValue((String) row.get("P_REFERENCE_VALUE"));
			p.setResponseCode((String) row.get("P_RESPONSE_CODE"));
			p.setRetrivalRefNumber((String) row.get("P_RETRIVAL_REF_NUMBER"));
			p.setSchemaTransactionId((String) row.get("P_TRANSACTION_ID"));
			p.setSessionKey((String) row.get("P_SESSION_KEY"));
			p.setSettlementFlag((String)row.get("P_SETTLEMENT_FLAG"));
			p.setStanNumber((String) row.get("P_STAN_NUMBER"));
			p.setStatus((String) row.get("P_STATUS"));
			p.setTerminalId((String) row.get("P_TERMINALID"));
			p.setTerminalSerialNumber((String) row.get("P_TERMINAL_SERIAL_NUMBER"));
			p.setTime((String) row.get("P_TIME"));
			p.setTrack2((String) row.get("P_TRACK2"));
			p.setVoidApprovedFlag(((BigDecimal)row.get("P_VOID_APPROVED")).intValue());
			p.setTipApproved((String) row.get("P_TIP_APPROVED"));
			p.setLastFourDigitValue((String)row.get("P_LAST_FOUR_DIGIT"));
			p.setProcessingCode((String) row.get("P_PROCESSING_CODE"));
			p.setMTI((String) row.get("P_MTI"));
			p.setBranchCode((String) row.get("P_BRANCH_CODE"));
			p.setField63((String) row.get("P_FIELD63"));
			p.setTipPercent((String) row.get("P_TIP_PERCENT"));
			p.setRefundFlag(Integer.parseInt((String) row.get("P_REFUND_FLAG")));
			p.setAppName((String) row.get("P_APP_NAME"));
			p.setTransactionChannel((String) row.get("P_TXN_CHANNEL"));
			p.setBankCode((String) row.get("P_BANK_CODE"));
			p.setEmiIndicator((String) row.get("P_EMI_INDICATOR"));
			p.setInternationalFlag((String) row.get("P_INTNT_FLAG"));
			listpaymentDTO.add(p);
			p=null;
		}
		return listpaymentDTO;
	}
	
	public static List<WalletDTO> getWalletDTOFc(List<? extends Map<String,Object>> rows){
		List<WalletDTO> listPaymentDTO = new ArrayList<WalletDTO>();
		for(Map<String,Object> row : rows){
			WalletDTO p = new WalletDTO();
			p.setTermSerNum((String) row.get("W_TERM_SER_NUM"));
			p.setInvNumber((String)row.get("W_INV_NUMBER"));
			p.setMid((String)row.get("W_MID"));
			p.setTid((String)row.get("W_TID"));
			p.setTxnRefId((String)row.get("W_TXN_REF_ID"));
			p.setWalletId((String)row.get("W_WALLET_ID"));
			p.setTxnAmount((String)row.get("W_TXN_AMOUNT"));
			p.setTxnDateNTime((String)row.get("W_TXN_DATENTIME"));
			p.setProcCode((String)row.get("W_PROC_CODE"));
			p.setAuthToken((String)row.get("W_AUTH_TOKEN"));
			p.setAdditionalInfo((String)row.get("W_ADDITIONAL_INFO"));
			p.setResponseCode((String)row.get("W_RESPONSE_CODE"));
			p.setResponseDesc((String)row.get("W_RESPONSE_DESC"));
			p.setServerTxnId((String)row.get("W_TXN_ID"));
			p.setReqType((String)row.get("W_REQUEST_TYPE"));
			p.setRefundApproved((String)row.get("W_REFUND_APPROVED"));
			p.setTchSettledFlag((String)row.get("W_TCH_SETTLED_FLAG"));
			p.setIsVoid("Y");
			listPaymentDTO.add(p);
			p=null;
		}
		
		return listPaymentDTO;
		
		
	}
	
	public static List<WalletDTO> getSettledDTOFc(List<? extends Map<String, Object>> rows){
		List<WalletDTO> listPaymentDTO = new ArrayList<WalletDTO>();
		for(Map<String,Object> row : rows){
			WalletDTO p = new WalletDTO();
			p.setServerTxnId((String)row.get("P_REFERENCE_VALUE"));
			p.setTxnAmount((String)row.get("P_ORIGINAL_AMOUNT"));
			p.setTxnRefId((String)row.get("P_RETRIVAL_REF_NUMBER"));
			p.setIsVoid("N");
			listPaymentDTO.add(p);
			p=null;
		}
		return listPaymentDTO;

		
	}
	
	public static List<PaymentAuthDTO> getPaymentAuthDTO(List<? extends Map<String, Object>> rows){
		List<PaymentAuthDTO> listpaymentDTO = new ArrayList<PaymentAuthDTO>();
		for(Map<String, Object> row : rows){
			PaymentAuthDTO p = new PaymentAuthDTO();
			p.setPaymentId(((BigDecimal) row.get("P_ID")).longValue());
			p.setAccountType((String) row.get("P_ACCOUNT_TYPE"));
			p.setAdditionalAmount(((BigDecimal) row.get("P_ADDAMOUNT")).doubleValue());
			p.setAuthorizationId((String) row.get("P_AUTH_ID"));
			if(((String) row.get("P_BATCH_NUMBER")) != null)
				p.setBatchNumber(Integer.parseInt((String) row.get("P_BATCH_NUMBER")));
			p.setBinNumber((String) row.get("P_BINNUMBER"));
			p.setCardEntryMode((String) row.get("P_CARD_ENTRYMODE"));
			p.setCardLabel((String) row.get("P_CARD_LABEL"));
			p.setClientId((String) row.get("P_CLIENTID"));
			p.setRequestType((String) row.get("P_REQUEST_TYPE"));
			p.setCurrencyCode((Integer.valueOf((String)row.get("P_CURRENCY_CODE"))));
			p.setCurrencyName((String) row.get("P_CURRENCY_NAME"));
			p.setDccAmount(((BigDecimal) row.get("P_DCC_AMOUNT")).doubleValue());
			p.setDecisionFlag((String) row.get("P_DECISION_FLAG"));
			p.setExchangeRate((Double.valueOf((String) row.get("P_EXCHANGE_RATE"))));
			p.setExpiryDate((String) row.get("P_EXPIRYDATE"));
			p.setField55((String) row.get("P_FIELD55"));
			p.setIssuerField55((String) row.get("P_ISSUE_FIELD55"));
			p.setInvoiceNumber((String)row.get("P_INVOICENUMBER"));
			p.setMarkup((String) row.get("P_MARKUP"));
			p.setMerchantId((String) row.get("P_MERCHANTID"));	
			p.setOriginalAmount((Double.valueOf((String) row.get("P_ORIGINAL_AMOUNT"))));
			p.setPanNumber((String) row.get("P_PANNUMBER"));
			p.setPinBlock((String) row.get("P_PIN_BLOCK"));
			p.setReferenceValue((String) row.get("P_REFERENCE_VALUE"));
			p.setResponseCode((String) row.get("P_RESPONSE_CODE"));
			p.setRetrivalRefNumber((String) row.get("P_RETRIVAL_REF_NUMBER"));
			p.setSchemaTransactionId((String) row.get("P_TRANSACTION_ID"));
			p.setSessionKey((String) row.get("P_SESSION_KEY"));
			p.setSettlementFlag((String)row.get("P_SETTLEMENT_FLAG"));
			p.setStanNumber((String) row.get("P_STAN_NUMBER"));
			p.setStatus((String) row.get("P_STATUS"));
			p.setSaleCompletionFlag((String) row.get("P_SALE_COMP_FLAG"));
			p.setTerminalId((String) row.get("P_TERMINALID"));
			p.setTerminalSerialNumber((String) row.get("P_TERMINAL_SERIAL_NUMBER"));
			p.setTime((String) row.get("P_TIME"));
			p.setTrack2((String) row.get("P_TRACK2"));
			p.setVoidApprovedFlag(((BigDecimal)row.get("P_VOID_APPROVED")).intValue());
			p.setTipApproved((String) row.get("P_TIP_APPROVED"));
			p.setLastFourDigitValue((String)row.get("P_LAST_FOUR_DIGIT"));
			p.setProcessingCode((String) row.get("P_PROCESSING_CODE"));
			p.setMTI((String) row.get("P_MTI"));
			p.setBranchCode((String) row.get("P_BRANCH_CODE"));
			p.setField63((String) row.get("P_FIELD63"));
			p.setTipPercent((String) row.get("P_TIP_PERCENT"));
			p.setRefundFlag(Integer.parseInt((String) row.get("P_REFUND_FLAG")));
			p.setAppName((String) row.get("P_APP_NAME"));
			p.setBankCode((String) row.get("P_BANK_CODE"));
			p.setEmiIndicator((String) row.get("P_EMI_INDICATOR"));
			p.setInternationalFlag((String) row.get("P_INTNT_FLAG"));
			listpaymentDTO.add(p);
			p=null;
		}
		return listpaymentDTO;
	}
	public static String leftPad(String str, int length){

		if(str.length() == length)
			return str;
		if(length <= 0){
			return str;
		}
		if(str.length() < length){
			StringBuilder sb = new StringBuilder();
			for(int i=0 ; i < (length - str.length()) ; i++){
				sb.append("0");
			}
			sb.append(str);
			return sb.toString();
		}
		else
		{
			return str;
		}

	}
	
	public static String byteArrayToHexString(byte[] byteArray){
		if(byteArray == null || byteArray.length == 0)
        {
            return ("");
        }
		StringBuilder hexString = new StringBuilder(byteArray.length * 2);

		for(int i = 0; i < byteArray.length; i++){
			hexString.append(byteToHex(byteArray[i]));
		}

		return (hexString.toString());
	}
	
	
	
	static public String byteToHex(byte b) {
		// Returns hex String representation of byte b
		String hexDigit[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		 return  (hexDigit[(b >>> 4) & 0x0f] + hexDigit[b & 0x0f]);
	}
	
	public static String unHex(String arg) {        

		String str = "";
		for(int i=0;i<arg.length();i+=2)
		{
			String s = arg.substring(i, (i + 2));
			int decimal = Integer.parseInt(s, 16);
			str = str + (char) decimal;
		}       
		return str;
	}

	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public static MessageFactory<IsoMessage> config(String path) throws IOException{
		MessageFactory<IsoMessage> mfact = new MessageFactory<IsoMessage>();
		mfact.setConfigPath(path);
		return mfact;
	}
	
	public static <T> T setDefaultMandatoryValue(T obj) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
		
	 	Class<?> type = obj.getClass();
	 	//
		for(Field field : type.getDeclaredFields()){
			if("java.lang.String".equals(field.getType().getName()) && field.isAnnotationPresent(Mandate.class)){
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
				Method getter = pd.getReadMethod();
				
				if(getter.invoke(obj)  == null){
					Method setter = pd.getWriteMethod();
					setter.invoke(obj,"NULL");
				}
			}
		}
		return obj;
	}
	
	/**
	 * Applies the specified mask to the card number.
	 *
	 * @param cardNumber The card number in plain format
	 * @param mask The number mask pattern. Use # to include a digit from the
	 * card number at that position, use x to skip the digit at that position
	 *
	 * @return The masked card number
	 */
	public static String maskCardNumber(String cardNumber, String mask) {

	    // format the number
	    int index = 0;
	    StringBuilder maskedNumber = new StringBuilder();
	    for (int i = 0; i < mask.length(); i++) {
	        char c = mask.charAt(i);
	        if (c == '#') {
	            maskedNumber.append(cardNumber.charAt(index));
	            index++;
	        } else if (c == 'x') {
	            maskedNumber.append(c);
	            index++;
	        } else {
	            maskedNumber.append(c);
	        }
	    }

	    // return the masked number
	    return maskedNumber.toString();
	}
	public static Map<String, String> parseTLV(String tlv) {
	    /*if (tlv == null || tlv.length()%2!=0) {
	        throw new RuntimeException("Invalid tlv, null or odd length");
	    }*/
	    HashMap<String, String> hashMap = new HashMap<String, String>();
	    for (int i=0; i<tlv.length();) {
	        try {
	            String key = tlv.substring(i, i=i+3);
	            
	            /*if ((Integer.parseInt(key,16) & 0x1F) == 0x1F) {
	                // extra byte for TAG field
	                key += tlv.substring(i, i=i+2);
	            }*/
	            String len = tlv.substring(i, i=i+3);
	            //int length = Integer.parseInt(len,16);
	            int length = Integer.parseInt(len);
	            /*if (length > 127) {
	                // more than 1 byte for lenth
	                int bytesLength = length-128;
	                len = tlv.substring(i, i=i+(bytesLength*2));
	                length = Integer.parseInt(len,16);
	            }
	            length*=2;*/

	            String value = tlv.substring(i, i=i+length);
	            //System.out.println(key+" = "+value);
	            hashMap.put(key, value);
	        } catch (NumberFormatException e) {
	            throw new RuntimeException("Error parsing number",e);
	        } catch (IndexOutOfBoundsException e) {
	            throw new RuntimeException("Error processing field",e);
	        }
	    }

	    return hashMap;
	}
	
	public static Connection getConnection(){
		/*
		 * <add name="OracleConnString" connectionString="Data Source=(DESCRIPTION =    
		 * (ADDRESS_LIST =      (ADDRESS = (PROTOCOL = TCP)(HOST = 10.10.11.103)(PORT = 1521))    )    
		 * (CONNECT_DATA = (SERVICE_NAME = dbtest1)(SERVER = DEDICATED))  );Password=insight;User ID=insight"/>

		 */Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.10.11.103:1521:dbtest1","insight","insight");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}  
		return con;
	}
	
	
	public static String reverseCardEntryMode(String key){
		load();
		return cardEntryModeMap.get(key);
	}
	public static String reverseAccountNumber(String key){
		load();
		return accountEntryModeMap.get(key);
	}
	
	public static String[] parseString(String str){
		return str.split(",");
	}
	
	/**
	 * Get the mid and tid from the se number
	 * @param seNumber
	 * @return
	 * @throws TCHServiceException
	 */
	public static String[] getMIDTIDFromSeNumber(String seNumber) throws TCHServiceException{
		String mid,tid = "";
		if(seNumber.length() == 23){
			tid = seNumber.substring(15);
			mid = seNumber.substring(0,15);
		}else if(seNumber.length() < 23){
			BigInteger senumber = new BigInteger(seNumber);
			String tempSeNumber = String.format("%023d", senumber);
			tid = tempSeNumber.substring(15);
			mid = tempSeNumber.substring(0,15);
		}else{
			throw new TCHServiceException(ErrorConstants.TCH_S021, ErrorMaster.get(ErrorConstants.TCH_S021));
		}
		
		return new String[]{mid,tid};
	}
	
	public static String getDateTime(){
		 Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		    String strDate = sdf.format(cal.getTime());
		    return strDate;
	}
	
	public static String getFCDateTime(){
		 Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		    String strDate = sdf.format(cal.getTime());
		    return strDate;
	}
	
	
	public static String getWalletDateTime(String fcDate){
		SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyyhhmmss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date1;
		try {
			date1 = format1.parse(fcDate);
			String date2 = format2.format(date1);
			return date2;

		} catch (ParseException e) {
			logger.debug("Unable to parse date",e.getMessage());
			return null;
		}
}
	
	
	/**
	 * To maintain backward compatibility
	 * @param versionNumber
	 * @return true if version is greater than 14 NOV 2017
	 * @throws ParseException
	 */
	public static boolean isVersionGreaterThan14Nov(String versionNumber) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		if(versionNumber != null){
			versionNumber = versionNumber.substring(versionNumber.length() - 6 , versionNumber.length());
			return sdf.parse(versionNumber).after(sdf.parse("131117"));
		}
		return false;
	}
	
	
	/**
	 * CHECK FOR TEST TRANSACTION OR NOT
	 * @param input
	 * @return boolean true if transaction is test else false
	 */
	public static boolean isTestTransction(Payment input){
		return //Constants.TCH_TEST_BIN.equals(input.getBinNumber()) && 
				//Constants.TCH_TEST_EXPDATE.equals(input.getExpiryDate()) && 
				Constants.TCH_TEST_AMT.equals(input.getOriginalAmount());
	}
	
	public static String maskedMobileNumber(String mobileNumber) {
	    return  new StringBuilder().append("XXXXXX").
	    		append(mobileNumber.substring(mobileNumber.length()-4)).toString();
	 
	}
}
