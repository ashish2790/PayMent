package com.awl.tch.axisepay.helper;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AXISepayHelper {

	public static class Property {

		private static Logger logger  = LoggerFactory.getLogger(Property.class);
		
		static{
			Property.load();
		}
		
		private static String checksumKey;
		private static String encryptionKey;
		private static String apiUrl;
		
		public static String getChecksumKey() {
			return checksumKey;
		}
		
		public static String getEncryptionKey() {
			return encryptionKey;
		}
		
		public static String getApiUrl() {
			return apiUrl;
		}



		private static void load(){
			Properties pop = new Properties();
			try {
				pop.load(Property.class.getClassLoader().getResourceAsStream("AXISEPAY.properties"));
				checksumKey = (String) pop.get("pos.cks.key");
				encryptionKey = (String) pop.get("pos.encryption.key");
				apiUrl=(String) pop.get("api.url");
						
			} catch (IOException e) {
				logger.debug("Exception while loading properties" + e.getMessage());
			}
			
		}


	}

}
