package com.egras.helper;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Property {

	private static Logger logger  = LoggerFactory.getLogger(Property.class);
	
	static{
		Property.load();
	}
	
	private static String key;
	
	public static String getKey() {
		return key;
	}

	private static void load(){
		Properties pop = new Properties();
		try {
			pop.load(Property.class.getClassLoader().getResourceAsStream("EGRAS.properties"));
			//key = (String) pop.get("pos.key");
			key = (String) pop.get("pos.key_prod");
		} catch (IOException e) {
			logger.debug("Exception while loading properties" + e.getMessage());
		}
		
	}
}
