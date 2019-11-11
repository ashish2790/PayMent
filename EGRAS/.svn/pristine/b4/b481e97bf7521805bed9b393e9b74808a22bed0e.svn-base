package com.egras.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egras.constant.EgrasEnumConstant;
import com.egras.exception.EGRASServiceException;
import com.egras.helper.Property;

 
/**
 * This example program shows how AES encryption and decryption can be done in Java.
 * Please note that secret key and encrypted text is unreadable binary and hence
 * in the following program we display it in hexadecimal format of the underlying bytes.
 * @author ashish bhavsar
 */

public class EgrasEncyption {

	private static Logger logger = LoggerFactory.getLogger(EgrasEncyption.class);
	private static Cipher cipher;
	private static MessageDigest md;
	private static byte[] mainKey = new byte[16];
	private static SecretKey secKey;
	private static IvParameterSpec iv = null;
	
	static{
		try {
			byte[] key = getKey(Property.getKey()).getBytes();
			int len = key.length;
			if(len > mainKey.length){
				len = mainKey.length;
			}
		    System.arraycopy(key, 0, mainKey, 0,len);
		    init();
		} catch (EGRASServiceException e) {
			logger.debug("Exception in creation of instance of cipher");
		}
	}
	

  /**
   * 1. Generate a plain text for encryption
   * 2. Get a secret key (printed in hexadecimal form). In actual use this must
   * by encrypted and kept safe. The same key is required for decryption.
 * @throws NoSuchAlgorithmException 
 * @throws NoSuchPaddingException 
 * @throws NoSuchProviderException 
 * @throws EGRASServiceException 
   * 
   */
	
	private static void init() throws EGRASServiceException{
		try{
			md = MessageDigest.getInstance(EgrasEnumConstant.MD5);
			Security.addProvider(new BouncyCastleProvider());
			cipher = Cipher.getInstance(EgrasEnumConstant.ALGONAME);
			secKey = new SecretKeySpec(mainKey, EgrasEnumConstant.KEY_ALGO);
			iv = new IvParameterSpec(mainKey);
		}catch (Exception e){
			logger.debug("Excpetion in init :",e.getMessage(),e);
			throw new EGRASServiceException("EG-04", e.getMessage(), null);
		}
	}
	
  /*public static void main(String[] args) throws Exception {
	  
	 // pop.load(Property.class.getClassLoader().getResourceAsStream("EGRAS.properties"));
	 // System.out.println(pop.get("pos.key"));
     // String plainText = "Hello World";
	 // System.out.println(String.format("%1$128s", "POSAPP#123").replace(' ', '0'));
     String plainText = "GRN=7701583|BANK_CODE=1608|BankReferenceNo=709610430003|CIN=709610430003|PAID_DATE=27-04-2017|PAID_AMOUNT=100|TRANS_STATUS=S";
     
     String checksum = GetMD5Hash(plainText);
     
    // SecretKey secKey = getSecretEncryptionKey();
      //SecretKey secKey = generateKey("POSAPP#123");
      //System.out.println("Secret key : " +secKey);
     
     byte[] cipherText = encryptText(plainText + "|checkSum="+checksum, EgrasEncyption.getSecretEncryptionKey());
      
      String base64Encoded = new String(Base64.encode(cipherText));
      
      //logger.debug("Plain text : " + plainText);
      
      logger.debug(plainText +"|checksum=" + checksum);
      
      logger.debug("Encrypted : " + base64Encoded);
      
      String decryptedText = decryptText(Base64.decode(base64Encoded), secKey);
      

      //logger.debug("Original Text:" + plainText);

//      System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));

  //    System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));
	
      logger.debug("Descrypted Text:"+decryptedText);

  
  }*/
  /**
   * gets the AES encryption key. In your actual programs, this should be safely
   * stored.
   * @return
   * @throws Exception
   */

  public static SecretKey getSecretEncryptionKey() throws Exception{
      return secKey;
  }

  /**

   * Encrypts plainText in AES using the secret key
   * @param plainText
   * @param secKey
   * @return
   * @throws Exception

   */

  public static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{

      // AES defaults to AES/ECB/PKCS7Padding in using Bouncycastle
      cipher.init(Cipher.ENCRYPT_MODE, secKey,iv);
      byte[] byteCipherText = cipher.doFinal(plainText.getBytes());
      String base64Encoded = new String(Base64.encode(byteCipherText));
      logger.info("Encrypted text :" + base64Encoded);
      return byteCipherText;
  }

  /**
   * Decrypts encrypted byte array using the key used for encryption.
   * @param byteCipherText
   * @param secKey
   * @return
   * @throws Exception
   */

  public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {

      // AES defaults to AES/ECB/PKCS5Padding in Java 7
      cipher.init(Cipher.DECRYPT_MODE, secKey,iv);
      byte[] bytePlainText = cipher.doFinal(byteCipherText);
      return new String(bytePlainText,"UTF-8");
  }

   

  /**
   * Convert a binary byte array into readable hex form
   * @param hash
   * @return
   */

  private static String  bytesToHex(byte[] hash) {
      return DatatypeConverter.printHexBinary(hash);
  }
  
 /* private static SecretKey generateKey(String passphrase) {
      try {

              SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
              byte[] salt = new byte[16];              
              
              
              KeySpec spec = new PBEKeySpec(passphrase.toCharArray(),passphrase.getBytes(),1000,128);
              6+
              
              
            		  //new PBEKeySpec(passphrase.toCharArray(), hex(salt),  128);
              System.out.println(spec);
              SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

              return key;

      }

      catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
    	  e.printStackTrace();
      }
	return null;

}*/

  
  public static String GetMD5Hash(String plainText) throws NoSuchAlgorithmException
  {
      md.update(plainText.getBytes());
      byte byteData[] = md.digest();
      StringBuffer hexString = new StringBuffer();
	  	for (int i=0;i<byteData.length;i++) {
	  		String hex=Integer.toHexString(0xff & byteData[i]);
	 	     	if(hex.length()==1) hexString.append('0');
	 	     	hexString.append(hex);
	  	}
  	logger.debug("checksum :" + hexString.toString());
	return hexString.toString();
  }

  private static String getKey(String key){
		return new String(Base64.decode(key));
	}
}

