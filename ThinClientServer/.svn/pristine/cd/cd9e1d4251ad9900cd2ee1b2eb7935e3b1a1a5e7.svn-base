package com.awl.tch.iso8583;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.bean.Handshake;
import com.awl.tch.controller.HandshakeController;
import com.awl.tch.util.UtilityHelper;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;

public class ISOHandshake {
	
	private static final Logger logger = LoggerFactory.getLogger(HandshakeController.class);

	/*public static void main(String[] args) throws IOException {

		//MessageFactory<IsoMessage> msgFact = ConfigParser.createFromClasspathConfig(ISO8583.class.getClassLoader(), "/\NetworkMessage.xml");
		MessageFactory<IsoMessage> msgFact = new MessageFactory<IsoMessage>();
		IsoMessage iso = msgFact.newMessage(0x800);
		//iso.setIsoHeader("ISO8583-1987015000050");
		//iso.setType(800);
		iso.setBinary(true);
		iso.setBinaryBitmap(true);
		//iso.setField(0,new IsoValue<Integer>(IsoType.NUMERIC,800,4));
		iso.setField(3,new IsoValue<Integer>(IsoType.NUMERIC,990490,6));
		//iso.setField(7,new IsoValue<Date>(IsoType.DATE10,new Date()));
		iso.setField(11,new IsoValue<Integer>(IsoType.NUMERIC,STANGenerator.nextTrace(),6));
		iso.setField(24,new IsoValue<Integer>(IsoType.NUMERIC,112,3));
		iso.setField(41,new IsoValue<String>(IsoType.ALPHA,"06060606",8));
		iso.setField(42,new IsoValue<String>(IsoType.ALPHA,"000000000008062",15));
		iso.setField(60,new IsoValue<String>(IsoType.ALPHA,"0021398041",10));
		iso.setField(62,new IsoValue<String>(IsoType.ALPHA,"30303030303030300000",20));
		byte[] isoMsg = iso.writeData();
		logger.info(iso.debugString());
		String str1 = iso.debugString();
		String str = new String(isoMsg);

		String isoHeader = iso.getIsoHeader();
		int isoHex ;
		String s ;
		String bin="" ; 
		for(int i=0;i<isoHeader.length();i++)
		{
			isoHex = isoHeader.charAt(i);
			s = Integer.toHexString(isoHex);

			bin = bin + (char)Integer.parseInt(s, 16);			
			//System.out.format("%H", isoHeader.charAt(i));			
		}
		//logger.info(bin);
		logger.info();
		logger.info(str);
		int isoHex ;
		for(int i=0;i<str.length();i++)
		{
			isoHex = str.charAt(i);
			System.out.format("%H ", isoHex);

			//System.out.format("%H", isoHeader.charAt(i));			
		}




	}*/
	
	private static String doHex(String str)
	{
		String hexStr="";
		for(int i=0;i<str.length();i++)
		{
			
			hexStr = hexStr + Integer.toHexString(str.charAt(i));
		}
		return hexStr;
	}
	
	private static String unHex(String arg) {        

	    String str = "";
	    for(int i=0;i<arg.length();i+=2)
	    {
	        String s = arg.substring(i, (i + 2));
	        int decimal = Integer.parseInt(s, 16);
	        str = str + (char) decimal;
	    }       
	    return str;
	}

	public static String keyExchange(Handshake hskBean)
	{
		MessageFactory<IsoMessage> msgFact = new MessageFactory<IsoMessage>();
		IsoMessage iso = msgFact.newMessage(0x800);
		//String header = "6001120000";
		//iso.setIsoHeader(UtilityHelper.unHex(hskBean.getTpdu()));
		iso.setIsoHeader(UtilityHelper.unHex("6001120000"));
		iso.setBinary(true);
		String terSerNum = hskBean.getTerminalSerialNumber().substring(hskBean.getTerminalSerialNumber().length()-8);
				;
		/*if("IWL".equalsIgnoreCase(hskBean.getTerminalSerialNumber()))
			terSerNum = hskBean.getTerminalSerialNumber().substring(3);
		else
			terSerNum = hskBean.getTerminalSerialNumber();*/
		
		if(terSerNum.length() < 10)	
			terSerNum =  UtilityHelper.leftPad(terSerNum,10);
		
		logger.info("Terminal Serial Number :"+terSerNum);
		//iso.setBinaryBitmap(true);
		iso.setCharacterEncoding("ISO-8859-1");
		if("Y".equalsIgnoreCase(hskBean.getLineEncryptionFlag()))
			iso.setValue(3, 990490,   IsoType.NUMERIC, 6);
		else
			iso.setValue(3, 990390,   IsoType.NUMERIC, 6);
		iso.setValue(11, hskBean.getStraceNumber() ,  IsoType.NUMERIC, 6);
		iso.setValue(24, hskBean.getNii(),  IsoType.NUMERIC, 4);
		iso.setValue(41, hskBean.getTid(),  IsoType.ALPHA, 8);
		iso.setValue(42, hskBean.getCardAcquiringId(),  IsoType.ALPHA, 15);
		iso.setValue(60, terSerNum.getBytes(),   IsoType.LLLBIN,10);
		iso.setValue(62, "      ".getBytes(),   IsoType.LLLBIN,6);
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
			
			
			IsoMessage m810 = mfact.parseMessage(binArray, 5);
			
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
	

}
