package com.awl.tch.wallet.fc.utility;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.wallet.fc.constant.FcConstant;

public class HelperUtil{
	private static Logger logger = LoggerFactory.getLogger(HelperUtil.class);
	private static Calendar calendar = Calendar.getInstance();
	private static Integer dayOfYearInstance = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	
	static LinkedHashSet<String> val  = new  LinkedHashSet<String>();
	/**
	 * generates response String
	 * @param status 
	 * @param message
	 * @return the formatted String
	 */
	public static String generateResponse(String status,String message)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(status);
		sb.append("/");
		sb.append(message);
		return sb.toString();
	}
	
	
	/**
	 * generates unique reference number
	 * @return String which is unique reference number based on timestamp and random
	 */
	public static String getReferenceNumber(int sequence)
	{
		int year = calendar.get(Calendar.YEAR);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		StringBuilder sb  = new StringBuilder();
		sb.append(year%10); //1
		sb.append(String.format("%03d",dayOfYear));//3
		
		// condition to reset counter for next day
		synchronized (HelperUtil.class) {
			int diff = dayOfYear - dayOfYearInstance;
			logger.debug("difference in days :" + diff);
			if(diff != 0){
				dayOfYearInstance = dayOfYear;
				//numberValue = new BigInteger("0");
			}
			//numberValue = numberValue.add(BigInteger.ONE);
			String str = String.format("%06d",sequence);
			sb.append(str.substring(str.length() - 6,str.length()));//6
		}
		sb.append(calendar.get(Calendar.HOUR_OF_DAY));
		
		return sb.toString();
	}
	
	public static String getDate(String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * generates auth token to identify that the packet is from valid source at Fc host.
	 * @return String which is auth token based on concatenation of mid, tid, txnRefNo(string) along with salt(SA) 
	 * and SHA-256 of (String + SA)
	 */
	public static String genAuthToken(String mid,String tid, String transactionRefNumber){
		String token = "";
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			StringBuilder hash = new StringBuilder(mid); 
			hash.append(tid); 
			hash.append(transactionRefNumber); 
			hash.append(FcConstant.TCH_FC_MERCHANT_SALT); 
			md.reset(); 
			byte[] bytesOfMessage = hash.toString().getBytes("UTF-8"); 
			md.update(bytesOfMessage); 
			byte[] messageDigest = md.digest(); 
			StringBuilder hexString = new StringBuilder(); 
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]); 
				if (hex.length() == 1) { 
					hexString.append("0"); 
				} 
				hexString.append(hex); 
			}
			return hexString.toString();
		}catch(Exception e){
			logger.debug("Counld not generate Auth token");
		}
		return token;
	}

}
