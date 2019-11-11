package com.tch.sbiepay.encryption;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toml.dp.util.AES128Bit;
import com.toml.dp.util.DPBase64Coder;

public class SBIepayEncryption {

	private static Logger logger  = LoggerFactory.getLogger(SBIepayEncryption.class);
	
	public static String encryptText(String plainText, String key){
		logger.info("in encryption for SBIEPAY");
		return AES128Bit.encrypt(plainText, key);
	}
	
	public static String decryptText(String cipherText, String key){
		logger.info("in decryption for SBIEPAY");
			return AES128Bit.decrypt(cipherText,key);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//byte[] value = DPBase64Coder.decode("fBc5628ybRQf88f/aqDUOQ==");
		//byte[] value = DPBase64Coder.decode("5ALidF/1sNo49PxlXxeCoQ==");
		//System.out.println(new String(value));
		
		String key = "5ALidF/1sNo49PxlXxeCoQ==";
		String enq_encrypt =  SBIepayEncryption.encryptText("SBIEPAY|7687597394710",key);
		System.out.println(enq_encrypt);
		String requestStr = URLEncoder.encode(enq_encrypt,"UTF-8");
		System.out.println(requestStr);
		
		String response = "hMGjtZL5O1HgWQ905J5A+M/vToIpQeLodD3sKKAfmZSC0eE2hqC919CHCiNbHKa6St1EFpeetOtqqoa09GuYvjJkYyflES54UdtRrNTjzWE=";
		response = SBIepayEncryption.decryptText(response,key);
		System.out.println(response);
		/*String encrypt = SBIepayEncryption.encryptText("SBIEPAY|4318086075942|714911100001|100.00|Y|Payment transferred.",key);
		System.out.println("Encrypted :" + encrypt);
		String decrypt = SBIepayEncryption.decryptText("K+KcU9yDgGCsdo8jP3hvhhj+FDpVkRoRY9K86ruFnipx08b+Dl1tUc5SqK/5xvVQ", key);
		System.out.println("decrypted :" + decrypt);*/
		
		
		
		
	}
}
