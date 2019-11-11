package com.awl.tch.iso8583;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.bean.Handshake;
import com.awl.tch.constant.Tags;
import com.awl.tch.controller.HandshakeController;
import com.awl.tch.util.UtilityHelper;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;

public class ISOHandshakePG {
	
	private static final Logger logger = LoggerFactory.getLogger(HandshakeController.class);

	public static String keyExchange(Handshake hskBean)
	{
		MessageFactory<IsoMessage> msgFact = new MessageFactory<IsoMessage>();
		IsoMessage iso = msgFact.newMessage(0x800);
		iso.setIsoHeader(UtilityHelper.unHex("49534F383538332D31393837000000010000000500"));
		iso.setBinary(true);
		String terSerNum = hskBean.getTerminalSerialNumber();
		if(terSerNum.contains("-"))
			terSerNum = terSerNum.replace("-", "") ;
		else
			terSerNum = terSerNum.substring(hskBean.getTerminalSerialNumber().length()-8);
		logger.debug("Terminal serial number [ " +terSerNum+"]");
		
		if(terSerNum.length() < 10)	
			terSerNum =  UtilityHelper.leftPad(terSerNum,10);
		
		logger.info("Terminal Serial Number :"+terSerNum);
		//iso.setBinaryBitmap(true);
		iso.setCharacterEncoding("ISO-8859-1");
		iso.setValue(3, 990490,   IsoType.NUMERIC, 6); // for PG everything in enrypted format
		iso.setValue(11, hskBean.getStraceNumber() ,  IsoType.NUMERIC, 6);
		iso.setValue(24, hskBean.getNii(),  IsoType.NUMERIC, 4);
		iso.setValue(41, hskBean.getTid(),  IsoType.ALPHA, 8);
		iso.setValue(42, hskBean.getCardAcquiringId(),  IsoType.ALPHA, 15);
		iso.setValue(60, (Tags.hardwareserialnumberTagLength+String.format("%015d", Integer.parseInt(terSerNum))).getBytes(), IsoType.LLLBIN, 15); // AuthorizationSource Code
		iso.setValue(62, "      ".getBytes(),   IsoType.LLLBIN,6);
		logger.debug("Request to magnus | " + iso.debugString());
		byte[] isoMsg = iso.writeData();
		ByteArrayOutputStream byteOuts = new ByteArrayOutputStream();
		try {	
			iso.write(byteOuts, 2);
			isoMsg = byteOuts.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception in keyExchange :",e);
		}finally{
			try {
				byteOuts.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("Handshake Request Hex Dump :"+byteArrayToHexString(isoMsg));
		
		String str ="";
		
		try {
			str = new String(isoMsg,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in keyExchange :",e);
		}
		return str;
	}
	
	public static void parseKeyExchangeResponse(String responseData,Handshake hskBean)
	{
		
		try {
			byte responseByteArray[] = responseData.getBytes("ISO-8859-1");
			String hexDumpResponse = byteArrayToHexString(responseByteArray);
			
			logger.info("Handshake Response Hex Dump :"+hexDumpResponse);
			final MessageFactory<IsoMessage> mfact = config("config.xml");
			
	
			byte binArray[]  = Arrays.copyOfRange(responseByteArray, 2, responseByteArray.length);
			
			mfact.setUseBinaryMessages(true);
			
			
			IsoMessage m810 = mfact.parseMessage(binArray, 21);
			
			logger.info("Header :"+byteArrayToHexString(m810.getIsoHeader().getBytes()));
			logger.info("MTI :"+m810.getType());
			logger.info("Field 3 :"+m810.getField(3));
			logger.info("Field 11 :"+m810.getField(11));
			logger.info("Field 12 :"+m810.getField(12));
			logger.info("Field 13 :"+m810.getField(13));
			logger.info("Field 24 :"+m810.getField(24));
			logger.info("Field 39 :"+m810.getField(39));
			logger.info("Field 41 :"+m810.getField(41));
			logger.info("Field 62 :"+m810.getField(62));
	
			hskBean.setSessionKey(m810.getField(62).toString());
			hskBean.setResponseCode(m810.getField(39).toString());
			hskBean.setDate(m810.getField(13).toString());
			if(hskBean.getDate() != null){
				String actualDate = hskBean.getDate();
				logger.debug("Date received :" + actualDate);
				hskBean.setDate(UtilityHelper.MMYY().substring(2) + actualDate);
			}
			hskBean.setTime(m810.getField(12).toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in parseKeyExchangeResponse :"+e,e);
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in parseKeyExchangeResponse :"+e,e);
			e.printStackTrace();
		}
		catch (Exception e) {
			logger.error("Exception in parseKeyExchangeResponse :"+e,e);
			e.printStackTrace();
		}
	}
	
	private static MessageFactory<IsoMessage> config(String path) throws IOException {
		MessageFactory<IsoMessage> mfact = new MessageFactory<IsoMessage>();
        mfact.setConfigPath(path);
        return mfact;
    }
	
	
	private static String byteArrayToHexString(byte[] byteArray){
        String hexString = "";

        for(int i = 0; i < byteArray.length; i++){
            String thisByte = byteToHex(byteArray[i]);
           hexString += thisByte;
        }

        return hexString;
    }
	

	
	static public String byteToHex(byte b) {
	      // Returns hex String representation of byte b
	      char hexDigit[] = {
	         '0', '1', '2', '3', '4', '5', '6', '7',
	         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	      };
	      char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
	      return new String(array);
   }
	
	public static void main(String[] args) {
		//logger.info(doHex("0021398041"));
		//logger.info(6001120000L);
		//logger.info(unHex("6001120000"));
		/*logger.info(ISO8583.keyExchange());*/
		/*byte[] isoMsg = new byte[10];
		Bcd.encode("0021398041", isoMsg);
		String s = new String(isoMsg);*/
		//logger.info(Byte.parseByte("0021398041", 10));
		String str = "14298WL22307843";
		String str1 = str.substring(str.length()-8);
		System.out.println(str1);
		/*logger.info(ISO8583.networkMessage());*/
		//String resp0810 = "60000001120810203801000280000499049000000117355005310112303330363036303630360006303030383034";
		//ISO8583.parseKeyExchangeResponse(resp0810);
	}

}
