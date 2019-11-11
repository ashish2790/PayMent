package com.awl.tch.util;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.awl.tch.server.TcpServer;
import com.awl.tch.service.MenuObjectServiceImpl;

public class ISOMessages {

	private static Logger logger = LoggerFactory.getLogger(ISOMessages.class);
	static ConcurrentHashMap<String, String> switchMessages = new ConcurrentHashMap<String, String>();
	
	private ISOMessages(){
		
	}
	
	public static void load(){
		/*ApplicationContext app = TcpServer.getContext();
		MenuObjectServiceImpl s = (MenuObjectServiceImpl) app.getBean("menuObjectService");
		s.init();*/
		logger.info("Loading messages");
		switchMessages.put("01", "CALL ISSUER #01");
		switchMessages.put("02", "CALL ISSUER #02");
		switchMessages.put("03", "CONTACT AWL #03");
		switchMessages.put("04", "DECLINED PICK UP CARD #04");
		switchMessages.put("05", "DO NOT HONOR TRANS DECLINED #05");
		switchMessages.put("06", "CONTACT AWL #06");
		switchMessages.put("07", "PICK UP #07");
		switchMessages.put("08", "APPROVED VERIFY ID & SIGNATURE #08");
		switchMessages.put("09", "PLEASE WAIT #09");
		switchMessages.put("10", "APPROVED IN PART #10");
		switchMessages.put("11", "APPROVED #11");
		switchMessages.put("12", "INVALID TXN #12");
		switchMessages.put("13", "INVALID AMOUNT #13");
		switchMessages.put("14", "DECLINED #14");
		switchMessages.put("15", "DECLINED #15");
		switchMessages.put("17", "DECLINED #17");
		switchMessages.put("19", "RETRY #19");
		switchMessages.put("21", "RETRY #21");
		switchMessages.put("22", "RETRY #22");
		switchMessages.put("23", "DECLINE #23");
		switchMessages.put("24", "DECLINE #24");
		switchMessages.put("25", "RETRY #25");
		switchMessages.put("26", "DECLINE #26");
		switchMessages.put("27", "RETRY #27");
		switchMessages.put("28", "RETRY #28");
		switchMessages.put("29", "RETRY #29");
		switchMessages.put("30", "FORMAT ERROR #30");
		switchMessages.put("31", "RETRY #31 ");
		switchMessages.put("32", "RETRY #32");
		switchMessages.put("33", "EXPIRED CARD #33");
		switchMessages.put("34", "DECLINED #34");
		switchMessages.put("35", "CONTACT AWL #35");
		switchMessages.put("36", "RESTRICTED CARD #36");
		switchMessages.put("37", "CONTACT AWL #37");
		switchMessages.put("38", "EXCESS PIN TRIES #38");
		switchMessages.put("39", "DECLINED #39");
		switchMessages.put("40", "DECLINED #40");
		switchMessages.put("41", "PICK UP #41");
		switchMessages.put("42", "DECLINED #42");
		switchMessages.put("43", "PICK UP #43");
		switchMessages.put("44", "DECLINED #44");
		switchMessages.put("45", "RETRY #45");
		switchMessages.put("46", "RETRY #46");
		switchMessages.put("47", "RETRY #47");
		switchMessages.put("48", "RETRY #48");
		switchMessages.put("49", "RETRY #49");
		switchMessages.put("50", "RETRY #50");
		switchMessages.put("51", "DECLINED #51");
		switchMessages.put("52", "NO CHK ACCT #52");
		switchMessages.put("53", "NO SAVINGS ACCT #53");
		switchMessages.put("54", "EXPIRED CARD #54");
		switchMessages.put("55", "INCORRECT PIN #55");
		switchMessages.put("56", "DECLINED #56");
		switchMessages.put("57", "DECLINED #57");
		switchMessages.put("58", "DECLINED #58");
		switchMessages.put("59", "DECLINED #59");
		switchMessages.put("60", "DECLINED #60");
		switchMessages.put("61", "EXCEEDS AMT LMT #61");
		switchMessages.put("62", "RESTRICTED CARD #62");
		switchMessages.put("63", "SECURITY ERR #63");
		switchMessages.put("64", "RETRY #64");
		switchMessages.put("65", "EXCEEDS COUNT #65");
		switchMessages.put("66", "DECLINE #66");
		switchMessages.put("67", "PICK UP #67");
		switchMessages.put("75", "EX-PIN TRIES #75");
		switchMessages.put("78", "OLD ROC NOT FOUND #78");
		switchMessages.put("82", "INITIATE KEY EXCHANGE #82");
		switchMessages.put("84", "PROCEED IN INR #84");
		switchMessages.put("88", "NOT ENOUGH POINTS #88");
		switchMessages.put("89", "CONTACT AWL #89");
		switchMessages.put("90", "CUT OFF IN PROCESS #90");
		switchMessages.put("91", "TRY AFTER 5MIN #91");
		switchMessages.put("92", "TRY AFTER 5MIN #92");
		switchMessages.put("95", "TTOTALS MISMATCH #95");
		switchMessages.put("96", "SYSTEM ERROR #96");
	}
	
	public static String getSpecificMessageBasedOnResponseCode(String resCode){
		if(resCode != null && !resCode.isEmpty())
			return switchMessages.get(resCode);
		else
			return "CONTACT AWL #00";
	}
}
