package com.awl.tch.iso8583;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.bean.Payment;
import com.awl.tch.bean.Settlement;
import com.awl.tch.constant.Constants;
import com.awl.tch.util.UtilityHelper;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;

public class ISOSettlement {
	
	private static final Logger logger = LoggerFactory.getLogger(ISOSettlement.class);
	
	public static String createISOMessageForSettlement(Settlement input, String requestType){
		
		MessageFactory<IsoMessage> msgFact = new MessageFactory<IsoMessage>();
		IsoMessage iso = msgFact.newMessage(0x500);
		//iso.setIsoHeader(UtilityHelper.unHex("6001120000"));
		iso.setIsoHeader(UtilityHelper.unHex("49534F383538332D31393837000000010000000500"));
		
		iso.setBinary(true);
		iso.setCharacterEncoding("ISO-8859-1");
		
		
		logger.info("ISO REQUEST FOR 0500");
		
		// 920000 – Direct Settlement
		// 960000 – Settlement after batch upload
		if(Constants.TCH_REQUEST_SETTLEMENT_BATCH.equals(requestType))
			logger.info("Field 3 :["+ 960000 +"]");
		else
			logger.info("Field 3 :["+ 920000 +"]");
		
		logger.info("Field 11 :["+input.getStanNumber()+"]");
		logger.info("Field 24 :["+112+"]");
		logger.info("Field 41 :["+input.getTerminalId()+"]");
		logger.info("Field 42 :["+input.getMerchantId()+"]");
		logger.info("Field 60	 :["+input.getBatchNumber()+"]");
		logger.info("Field 62	 :["+input.getTerminalSerialNumber()+"]");
		if(Constants.TCH_REQUEST_SETTLEMENT_BATCH.equals(requestType))
			iso.setValue(3, 960000 , IsoType.NUMERIC, 6);
		else	
			iso.setValue(3, 920000 , IsoType.NUMERIC, 6);
		iso.setValue(11, Integer.valueOf(input.getStanNumber()), IsoType.NUMERIC, 6); // stan number
		iso.setValue(24, 112 , IsoType.NUMERIC, 3); // NII
		iso.setValue(41, input.getTerminalId(), IsoType.ALPHA, 8); // terminal id		
		iso.setValue(42, input.getMerchantId(), IsoType.ALPHA, 15); // acquiring id or merchant id
		iso.setValue(60, input.getBatchNumber(),new BCDencodeField60() , IsoType.LLLVAR, 6); // batch number
		/*
		 * parameters for field 62
		 * Application Version Number (19 bytes):
		 * Counters (10 bytes):
		 */				 // Version number(3) + //version number application manager(4)	+ //version number or terminal operating system(4) + // fixed string (7) + // 
		String field62  = "100" 		   +	        "0000" 						+ 					"0120" 							   + "abcdefg" +  			"1"
						// counters
						+"$$" + "00" + "00" + "00" + "00"
						//Primary Authorization Phone Number
						+ String.format("%1$-25s","8451064165")
						//Secondary Authorization Phone Number
						+ String.format("%1$-25s","8484578941")
						//IMEI number Number
						+ String.format("%1$-25s","asdfl9u9las9")
						//SIM number
						+ String.format("%1$-25s","a2df4sdgsg3a")
						//Terminal Hardware Serial Number
						+ String.format("%1$-15s",input.getTerminalSerialNumber())
						//Reserved field, padded with zeros
						+String.format("%025d",0)
						//Fifth counter
						+"00";
		

		iso.setValue(62, field62,new BCDencodeField60() , IsoType.LLLVAR, 6); // batch number
		/*
		 * Settlement Totals
		 * Debit Count (3 bytes)
		 * Debit Amount (12 Bytes)
		 * Credit Count (3 bytes)
		 * Credit Amount (12 Bytes)
		 */
		String field63Value = input.getDebitCount() + input.getDebitAmount() + input.getCreditCount() + input.getCreditAmount();
		logger.info("Field 63	 :["+field63Value+"]");
		iso.setValue(63, field63Value,new BCDencodeField60(), IsoType.LLLVAR, 6); //  invoice number new
		
		byte[] isoMsg = iso.writeData();
		ByteArrayOutputStream byteOuts = new ByteArrayOutputStream();
		
		try {
			iso.write(byteOuts, 2);
			isoMsg = byteOuts.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception in Sale transaction :",e);
		}finally{	
			try {
				byteOuts.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block				
				e.printStackTrace();
			}
		}
		
		logger.info("Settlement packet :"+UtilityHelper.byteArrayToHexString(isoMsg));
		
		String str ="";
			
		try {
			str = new String(isoMsg,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception in Settlement packet trancation :",e);
		}
		return str;
	}
	
	public static String createISOForBatchupload(Payment input){
	
		MessageFactory<IsoMessage> msgFact = new MessageFactory<IsoMessage>();
		IsoMessage iso = msgFact.newMessage(0x320);
		iso.setIsoHeader(UtilityHelper.unHex("6001120000"));
		iso.setBinary(true);
		iso.setCharacterEncoding("ISO-8859-1");
		
		
		logger.info("ISO REQUEST FOR 0320");
		
		logger.info("Field 3 :["+input.getProcessingCode() +"]");
		logger.info("Field 2 :["+input.getPanNumber() +"]"); //  pan number
		logger.info("Field 4 :["+input.getOriginalAmount() +"]"); // amount
		logger.info("Field 11 :["+input.getNewStanForSettlement()+"]");
		logger.info("Field 12 :["+input.getTime()+"]"); // time
		logger.info("Field 13 :["+UtilityHelper.dateInMMddYYYY().substring(0, 4)+"]"); // date
		logger.info("Field 14 :["+input.getExpiryDate()+"]"); // expiry date
		logger.info("Field 22 :["+input.getCardEntryMode()+"]"); // pos entry mode
		logger.info("Field 24 :["+112+"]");logger.info("Field 25 :["+00+"]");
		logger.info("Field 37 :["+input.getRetrivalRefNumber()+"]"); // rrn number
		logger.info("Field 38 :["+input.getAuthorizationId()+"]"); // auth id
		logger.info("Field 39 :["+input.getResponseCode()+"]"); // Response code
		logger.info("Field 41 :["+input.getTerminalId()+"]");
		logger.info("Field 42 :["+input.getMerchantId()+"]");
		if(input.getAdditionalAmount() != null)
			logger.info("Field 54 :["+String.format("%012d", Integer.parseInt(input.getAdditionalAmount()))+"]");
		if(input.getField55() != null)
			logger.info("Field 55 :["+input.getField55()+"]");
		
		iso.setValue(3, input.getProcessingCode() , IsoType.NUMERIC, 6);
		iso.setValue(2, input.getPanNumber() , new BCDencodeField60(), IsoType.LLBIN, 19); // pan number
		iso.setValue(4, input.getOriginalAmount(), IsoType.NUMERIC, 12); 
		iso.setValue(11, Integer.valueOf(input.getNewStanForSettlement()), IsoType.NUMERIC, 6); // stan number
		iso.setValue(12, input.getTime(), IsoType.NUMERIC, 6); // time
		iso.setValue(13, UtilityHelper.dateInMMddYYYY().substring(0, 4), IsoType.NUMERIC, 4); // date
		iso.setValue(14, input.getExpiryDate(), IsoType.NUMERIC, 4); // expiry date in YYMM
		iso.setValue(22, Integer.valueOf(input.getCardEntryMode()), IsoType.NUMERIC, 3); // pos entry mode
		iso.setValue(25, 00 , IsoType.NUMERIC, 2);// pos condition code
		iso.setValue(24, 112 , IsoType.NUMERIC, 3); // NII
		iso.setValue(37, input.getRetrivalRefNumber() , IsoType.ALPHA, 12);// RRN
		if(input.getAuthorizationId() != null)
			iso.setValue(38, input.getAuthorizationId() , IsoType.ALPHA, 6);// Auth Code
		else
			iso.setValue(38, 0 , IsoType.ALPHA, 6);// Auth Code
		iso.setValue(39, input.getResponseCode() , IsoType.ALPHA, 2);// Response code
		iso.setValue(41, input.getTerminalId(), IsoType.ALPHA, 8); // terminal id		
		iso.setValue(42, input.getMerchantId(), IsoType.ALPHA, 15); // acquiring id or merchant id
		if(input.getAdditionalAmount() != null)
			iso.setValue(54, String.format("%012d", Integer.parseInt(input.getAdditionalAmount())), new BCDencodeField60(), IsoType.LLLVAR, 6);
		if(input.getField55() != null)
			iso.setValue(55, input.getField55(),new BCDEncodedField55() , IsoType.LLLBIN, 1);
		
		
		/*
		 * as per the specification field 60 contains
		 * 
		 * -MTI of Original transaction (4 bytes)
		 * -STAN of Original transaction (6 Bytes)
		 * -RRN of Original transaction(12 Bytes)
		 * - Authorization source (1 char), if not available should be space char.
		 * - Transaction ID (15 chars) (only if original transaction is an online transaction)
		 */
		
		StringBuilder field60 = new StringBuilder("0")
		.append(input.getMTI()) 
		.append(String.format("%06d", Integer.valueOf(input.getStanNumber())))
		.append(input.getRetrivalRefNumber())
		.append(" ")
		.append(input.getSchemaTransactionId());
		logger.info("Field 60	 :["+field60.toString()+"]");
		
		iso.setValue(60, field60.toString() ,new BCDencodeField60() , IsoType.LLLVAR, 1); // new
		iso.setValue(61, Integer.valueOf(input.getCardEntryMode()), IsoType.NUMERIC, 3); // pos entry mode
		logger.info("Field 61 :["+"0" +input.getCardEntryMode()+"]");
		logger.info("Field 62 :["+input.getInvoiceNumber()+"]");
		iso.setValue(62, input.getInvoiceNumber(),new BCDencodeField60(), IsoType.LLLVAR, 6); //  invoice number new
		
		/*
		 * ‘A’ + 13 bytes Ticket Number (for airlines merchants)
		 * ‘P’ + 13 bytes PNR Number. (for Indian railway merchants)
		 * ‘H’+ 15 bytes hardware serial number + 3 bytes fall back reason code
		 * ‘B’+ 6 bytes terminal batch number
		 * ‘F Same as original transaction.
		 */
		logger.info("Field 63 :["+input.getField63()+"]");
		if(input.getField63() != null)
			iso.setValue(63, input.getField63(), new BCDencodeField60(),IsoType.LLLVAR, 5);
		
		byte[] isoMsg = iso.writeData();
		ByteArrayOutputStream byteOuts = new ByteArrayOutputStream();
		
		try {
			iso.write(byteOuts, 2);
			isoMsg = byteOuts.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception in Sale transaction :",e);
		}finally{	
			try {
				byteOuts.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		logger.info("Settlement packet :"+UtilityHelper.byteArrayToHexString(isoMsg));
		
		String str ="";
			
		try {
			str = new String(isoMsg,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception in Settlement packet trancation :",e);
		}
		return str;
	}

	public static void parseBatchUpload(String responseData , final Payment paymentBean){
		try {
			
			byte responseByteArray[] = responseData.getBytes("ISO-8859-1");
			final MessageFactory<IsoMessage> mfact = UtilityHelper.config("config.xml");
			byte binArray[]  = Arrays.copyOfRange(responseByteArray, 2, responseByteArray.length);
			mfact.setUseBinaryMessages(true);
			logger.info("batch upload response : [0330]");
			IsoMessage m330 = mfact.parseMessage(binArray, 21);
			
			logger.info("Field 3 :["+m330.getField(3) +"]");
			logger.info("Field 11 :["+m330.getField(11)+"]");
			logger.info("Field 24 :["+m330.getField(24)+"]");
			logger.info("Field 25 :["+00+"]");
			logger.info("Field 37 :["+m330.getField(37)+"]");
			logger.info("Field 39 :["+m330.getField(39)+"]");
			logger.info("Field 38 :["+m330.getField(38)+"]");
			logger.info("Field 41 :["+m330.getField(41)+"]");
			logger.info("Field 63 :["+m330.getField(63)+"]");
			
		} catch (IOException e) {
			logger.error("Exception in settlement :"+e.getMessage(),e);
		} catch (ParseException e) {
			logger.error("Exception in settlement:"+e.getMessage(),e);
		}
		catch (Exception e) {
			logger.error("Exception in settlement :"+e.getMessage(),e);
		}
		
	}
	public static void parseIsoMessageOfSettlement(String responseData,final Settlement settlementBean){
		try {
			byte responseByteArray[] = responseData.getBytes("ISO-8859-1");
			final MessageFactory<IsoMessage> mfact = UtilityHelper.config("config.xml");
			byte binArray[]  = Arrays.copyOfRange(responseByteArray, 2, responseByteArray.length);
			mfact.setUseBinaryMessages(true);
			logger.info("Settlement response : [0510]");
			IsoMessage m510 = mfact.parseMessage(binArray, 5);
			
			logger.info("Field 3 :"+m510.getField(3));
			logger.info("Field 11 :"+m510.getField(11));
			logger.info("Field 12 :"+m510.getField(12));
			logger.info("Field 13 :"+m510.getField(13));
			logger.info("Field 24 :"+m510.getField(24));
			logger.info("Field 37 :"+m510.getField(37));
			logger.info("Field 39 :"+m510.getField(39));
			logger.info("Field 41 :"+m510.getField(41));
			logger.info("Field 60 :"+m510.getField(60));
			logger.info("Field 61 :"+m510.getField(61));
			logger.info("Field 62 :"+m510.getField(62));
			logger.info("Field 63 :"+m510.getField(63));
			
			if(m510.getField(39) != null){
				settlementBean.setResponseCode(m510.getField(39).toString());
			}
			if(m510.getField(61) != null){
				settlementBean.setDate(m510.getField(61).toString().substring(0, 8));
				settlementBean.setTime(m510.getField(61).toString().substring(8));
			}
			if(m510.getField(37) != null){
				settlementBean.setRetrivalRefNumber(m510.getField(37).toString());
			}
		} catch (IOException e) {
			logger.error("Exception in settlement :"+e.getMessage(),e);
		} catch (ParseException e) {
			logger.error("Exception in settlement:"+e.getMessage(),e);
		}
		catch (Exception e) {
			logger.error("Exception in settlement :"+e.getMessage(),e);
		}
	}
}
