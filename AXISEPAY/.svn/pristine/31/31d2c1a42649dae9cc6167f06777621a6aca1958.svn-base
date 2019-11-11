package com.awl.tch.axisepay.encryption;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AXISepayEncryption {

	private static Logger logger  = LoggerFactory.getLogger(AXISepayEncryption.class);

	public static String encryptText(String plainText, String key){
		logger.info("In encryption for AXISEPAY for : " + plainText);
		String encryptesResquest = null;
		try {
			Key keyByte = generateKey(key);
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			c.init(Cipher.ENCRYPT_MODE, keyByte);
			byte[] encVal = c.doFinal(plainText.getBytes());
			encryptesResquest =new String(new Base64().encode(encVal));
		} catch (Exception e) {
			logger.debug("Error while encrypting string : " +plainText);
		} 
		return encryptesResquest;
	}


	public static String decryptText(String encryptedString, String key){
		logger.info("in decrption for AXISEPAY");
		String encryptesResquest = null;
		try {
			final Cipher cipher = Cipher.getInstance("AES");
			final Key keySpec = generateKey(key);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] encVal = cipher.doFinal(new Base64().decode(encryptedString));
			encryptesResquest =new String(encVal);
		} catch (Exception e) {
			logger.debug("Error while decryting string : " +encryptedString);
		}
		return encryptesResquest;
	}


	private static Key generateKey(String keyValue) throws Exception {
		Key key = new SecretKeySpec(keyValue.getBytes(), "AES");
		return key;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		String key = "AXISBANKPOSEPAY1";
		String encryptText =  AXISepayEncryption.encryptText("MID=12345&TID=12345&URN=640330&DATE_TIME=25-10-2017&CKS=93bfeeb25f3e19684c1920ad8f9337e99a2fc65ae28b1d28894358f1e8aa5462",key);
		System.out.println("Encrypted Text : " +encryptText);

		System.out.println("Decrypted Text : " +AXISepayEncryption.decryptText(encryptText, key));



	}
}
