package com.awl.tch.brandemi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperUtil {
	
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
	public static String getReferenceNumber()
	{
		Calendar calendar = Calendar.getInstance();
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR); 
		int year = calendar.get(Calendar.YEAR);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millis = calendar.get(Calendar.MILLISECOND);
		Random r = new Random();
		int random  = r.nextInt();
		random = Math.abs(random%10000000);
		StringBuilder sb  = new StringBuilder();
		sb.append(year%10); //1
		sb.append(String.format("%03d",dayOfYear)); // 3
		sb.append(String.format("%02d",hour));//2
		sb.append(String.format("%02d",minute));//2
		sb.append(String.format("%02d",second));//2
		sb.append(String.format("%03d",millis));//3
		sb.append(String.format("%07d",random));//7
		return sb.toString();
	}
	
	public static String getDate(String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * generates a 40 byte long substrings from a string using Matcher and Pattern
	 * @return list which contains 40 bytes(max) long sub strings.
	 * @param fullStr
	 * @return list of 40 bytes subString
	 * @author  Pooja Patil
	 */
	public static List<String> toGetByteString(String fullStr){
		List<String> listOfStrToByte = new ArrayList<String>();
		Pattern p = Pattern.compile(".{1,40}(\\s+|$)");
		try{
			if(fullStr != null && !fullStr.isEmpty()){
				Matcher m = p.matcher(fullStr);
				while(m.find()) {
				  listOfStrToByte.add(m.group().trim());
				}
			}
		}catch(IllegalStateException e){
		}
		return listOfStrToByte;
	}
	
}
