package com.awl.tch.encryptor;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class RSAEncryption {
	
	public static void main(String[] args) throws Exception{
		byte[] expBytes = Base64.decodeBase64("AQAB".trim());
		byte[] modBytes = Base64.decodeBase64("p1l9ptHevGbUK+IvZ/rkkbTCG19tq/bRIXd6Bik9MNI5EgBsQ8yVKxW0SnO3I2+NGRVMuPy77mlpqMMBOjdIpAtoSX5nCmbgVn1M3ADAeOB1a21qkU3QuI6GhWYzPW3vn6oRsPF5O+WZh6oSBvrf1s5pDBBj74oTpfw1C/LbxMB/ajQnDSXyUuEfP+nccC0u25u6SOMLr4Jta21j0ZHfksSa2eLHL0l5cZfmcES5ngTkAvokQf/Bn3D2BVD+lcjaU3ThDQT6HFfsd0U+118lGSRxjr+8+MchvgQgcCn5J93jU+C/+bowCVFBBiy84EsiZ5rVSblSl+DSd5KQnls96T5kP2v4nFtQazxkfsFKsfyhxVZck3cIsfcF+NiqvQp46l4gBLAVzNtsPk2HuUDGQUAE0Y4ZIoQN+SLOUBNiqLUyzIKWd3+af7FNipLVY0O9KY/t7ir2ElT3v+ZHQPhDZNRDkru3G4ev9VQuYyt40WUz+z6RE3lzpkwt2bCumqSaacl4BP8qSOcwIobzHI6KbHg7vIlJMotgLERyNNImx8vuJHRDsO1Ed1hc3mMM+epU9iUaPvSD8m3X74SRizwYTO/13EwpERGPZzXGx6ra2zPARhKYmpFQTNIkYB5IU+nio/DKHn7wP+UsPbrEdmEVs52Y4uEoRFNIzS4iQffOwoM=".trim());
		byte[] dBytes = Base64.decodeBase64("NqfHneAVOrrquJpamT8bv2qlqoL0MqGdk6yNsc1uKq+9698vfMUoQrYH9/jq6yvXjUWjlRcV2bubwn5NvFGp68m9+aLKzF7lDdpGa+Pi+Jd2QeuuTl53uI7z6EWEnp91D1RgCsYSrjlCd0hYMudki6dGL6Z6vRT/bcp9GLXsiMnF003NI0ItblwP9LPQRuK61wZ5RKzCeEPEsL09SIi32BtV56tlGlCI1Uyi+i+336ABR+eiR9vbxNMDxqC5RPogM/mMNtBVT0R3+kwnRffRCfEZFWIRub5viruWTIuo0Bhr0k0o5cqlPaXH8thRp39icSvkVPutueS3WE4J5W4vPaJvGnJ9L0JEHclQyznE4CqeDELaH1ouYgBIYBZzLvnu/COcx3TO21OGeQ+p3ngRAJxaLyRYmVNXE4OEiUlKXKULPFT/sx5qezp/EnmEi1rmmjG5FTd0ru9EhNQJVMbF+uHKVRsjaUigm26LqNADF8/sEcucOcPp9mrL+WD7rqwlZuXICzXFARL8/k8wV9Jyn3P1uuLl4qDTEj1U3xILTB9vyvJ5MRfVRp58/Kre8JMEG+o5T3s6m+Cc6xMUA13tbC/+JL5Isayqz4TKXqJWWB4yFMzPJyuEnZTeXqcJyzB7orW4Hh80C530Td3EcYRc9mxDfLE0zgIUT22HPnGO8qE=".trim());
		Base64 base64Encoder = new Base64();
		BigInteger modules = new BigInteger(1, modBytes);
		BigInteger exponent = new BigInteger(1, expBytes);
		BigInteger d = new BigInteger(1, dBytes);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		Cipher cipher = Cipher.getInstance("RSA");
		String input = "{ \"MID\":\"123432345643234\", \"TID\":\"12345667\", \"CHLN_NO\":\"201700080827\" }";

		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modules, exponent);
		PublicKey pubKey = factory.generatePublic(pubSpec);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
		String encryptedStr = new String(base64Encoder.encode(encrypted));
		System.out.println("encrypted: " +encryptedStr);
		System.out.println(URLEncoder.encode(encryptedStr, "UTF-8"));
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(modules, d);
		PrivateKey privKey = factory.generatePrivate(privSpec);
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		byte[] decrypted = cipher.doFinal(base64Encoder.decode(encryptedStr));
		System.out.println("decrypted: " + new String(decrypted));
	}
	
	public static String encrypt(String input) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		
		byte[] expBytes = Base64.decodeBase64("AQAB".trim());
		byte[] modBytes = Base64.decodeBase64("p1l9ptHevGbUK+IvZ/rkkbTCG19tq/bRIXd6Bik9MNI5EgBsQ8yVKxW0SnO3I2+NGRVMuPy77mlpqMMBOjdIpAtoSX5nCmbgVn1M3ADAeOB1a21qkU3QuI6GhWYzPW3vn6oRsPF5O+WZh6oSBvrf1s5pDBBj74oTpfw1C/LbxMB/ajQnDSXyUuEfP+nccC0u25u6SOMLr4Jta21j0ZHfksSa2eLHL0l5cZfmcES5ngTkAvokQf/Bn3D2BVD+lcjaU3ThDQT6HFfsd0U+118lGSRxjr+8+MchvgQgcCn5J93jU+C/+bowCVFBBiy84EsiZ5rVSblSl+DSd5KQnls96T5kP2v4nFtQazxkfsFKsfyhxVZck3cIsfcF+NiqvQp46l4gBLAVzNtsPk2HuUDGQUAE0Y4ZIoQN+SLOUBNiqLUyzIKWd3+af7FNipLVY0O9KY/t7ir2ElT3v+ZHQPhDZNRDkru3G4ev9VQuYyt40WUz+z6RE3lzpkwt2bCumqSaacl4BP8qSOcwIobzHI6KbHg7vIlJMotgLERyNNImx8vuJHRDsO1Ed1hc3mMM+epU9iUaPvSD8m3X74SRizwYTO/13EwpERGPZzXGx6ra2zPARhKYmpFQTNIkYB5IU+nio/DKHn7wP+UsPbrEdmEVs52Y4uEoRFNIzS4iQffOwoM=".trim());
		byte[] dBytes = Base64.decodeBase64("NqfHneAVOrrquJpamT8bv2qlqoL0MqGdk6yNsc1uKq+9698vfMUoQrYH9/jq6yvXjUWjlRcV2bubwn5NvFGp68m9+aLKzF7lDdpGa+Pi+Jd2QeuuTl53uI7z6EWEnp91D1RgCsYSrjlCd0hYMudki6dGL6Z6vRT/bcp9GLXsiMnF003NI0ItblwP9LPQRuK61wZ5RKzCeEPEsL09SIi32BtV56tlGlCI1Uyi+i+336ABR+eiR9vbxNMDxqC5RPogM/mMNtBVT0R3+kwnRffRCfEZFWIRub5viruWTIuo0Bhr0k0o5cqlPaXH8thRp39icSvkVPutueS3WE4J5W4vPaJvGnJ9L0JEHclQyznE4CqeDELaH1ouYgBIYBZzLvnu/COcx3TO21OGeQ+p3ngRAJxaLyRYmVNXE4OEiUlKXKULPFT/sx5qezp/EnmEi1rmmjG5FTd0ru9EhNQJVMbF+uHKVRsjaUigm26LqNADF8/sEcucOcPp9mrL+WD7rqwlZuXICzXFARL8/k8wV9Jyn3P1uuLl4qDTEj1U3xILTB9vyvJ5MRfVRp58/Kre8JMEG+o5T3s6m+Cc6xMUA13tbC/+JL5Isayqz4TKXqJWWB4yFMzPJyuEnZTeXqcJyzB7orW4Hh80C530Td3EcYRc9mxDfLE0zgIUT22HPnGO8qE=".trim());
		Base64 base64Encoder = new Base64();
		BigInteger modules = new BigInteger(1, modBytes);
		BigInteger exponent = new BigInteger(1, expBytes);
		BigInteger d = new BigInteger(1, dBytes);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		Cipher cipher = Cipher.getInstance("RSA");
		
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modules, exponent);
		PublicKey pubKey = factory.generatePublic(pubSpec);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
		String encryptedStr = new String(base64Encoder.encode(encrypted));
		System.out.println("encrypted: " +encryptedStr);
		
		return encryptedStr;
	}
	
	
	public static String decrypt(String input)throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		
		byte[] expBytes = Base64.decodeBase64("AQAB".trim());
		byte[] modBytes = Base64.decodeBase64("p1l9ptHevGbUK+IvZ/rkkbTCG19tq/bRIXd6Bik9MNI5EgBsQ8yVKxW0SnO3I2+NGRVMuPy77mlpqMMBOjdIpAtoSX5nCmbgVn1M3ADAeOB1a21qkU3QuI6GhWYzPW3vn6oRsPF5O+WZh6oSBvrf1s5pDBBj74oTpfw1C/LbxMB/ajQnDSXyUuEfP+nccC0u25u6SOMLr4Jta21j0ZHfksSa2eLHL0l5cZfmcES5ngTkAvokQf/Bn3D2BVD+lcjaU3ThDQT6HFfsd0U+118lGSRxjr+8+MchvgQgcCn5J93jU+C/+bowCVFBBiy84EsiZ5rVSblSl+DSd5KQnls96T5kP2v4nFtQazxkfsFKsfyhxVZck3cIsfcF+NiqvQp46l4gBLAVzNtsPk2HuUDGQUAE0Y4ZIoQN+SLOUBNiqLUyzIKWd3+af7FNipLVY0O9KY/t7ir2ElT3v+ZHQPhDZNRDkru3G4ev9VQuYyt40WUz+z6RE3lzpkwt2bCumqSaacl4BP8qSOcwIobzHI6KbHg7vIlJMotgLERyNNImx8vuJHRDsO1Ed1hc3mMM+epU9iUaPvSD8m3X74SRizwYTO/13EwpERGPZzXGx6ra2zPARhKYmpFQTNIkYB5IU+nio/DKHn7wP+UsPbrEdmEVs52Y4uEoRFNIzS4iQffOwoM=".trim());
		byte[] dBytes = Base64.decodeBase64("NqfHneAVOrrquJpamT8bv2qlqoL0MqGdk6yNsc1uKq+9698vfMUoQrYH9/jq6yvXjUWjlRcV2bubwn5NvFGp68m9+aLKzF7lDdpGa+Pi+Jd2QeuuTl53uI7z6EWEnp91D1RgCsYSrjlCd0hYMudki6dGL6Z6vRT/bcp9GLXsiMnF003NI0ItblwP9LPQRuK61wZ5RKzCeEPEsL09SIi32BtV56tlGlCI1Uyi+i+336ABR+eiR9vbxNMDxqC5RPogM/mMNtBVT0R3+kwnRffRCfEZFWIRub5viruWTIuo0Bhr0k0o5cqlPaXH8thRp39icSvkVPutueS3WE4J5W4vPaJvGnJ9L0JEHclQyznE4CqeDELaH1ouYgBIYBZzLvnu/COcx3TO21OGeQ+p3ngRAJxaLyRYmVNXE4OEiUlKXKULPFT/sx5qezp/EnmEi1rmmjG5FTd0ru9EhNQJVMbF+uHKVRsjaUigm26LqNADF8/sEcucOcPp9mrL+WD7rqwlZuXICzXFARL8/k8wV9Jyn3P1uuLl4qDTEj1U3xILTB9vyvJ5MRfVRp58/Kre8JMEG+o5T3s6m+Cc6xMUA13tbC/+JL5Isayqz4TKXqJWWB4yFMzPJyuEnZTeXqcJyzB7orW4Hh80C530Td3EcYRc9mxDfLE0zgIUT22HPnGO8qE=".trim());
		Base64 base64Encoder = new Base64();
		BigInteger modules = new BigInteger(1, modBytes);
		BigInteger exponent = new BigInteger(1, expBytes);
		BigInteger d = new BigInteger(1, dBytes);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		Cipher cipher = Cipher.getInstance("RSA");
		
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(modules, d);
		PrivateKey privKey = factory.generatePrivate(privSpec);
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		byte[] decrypted = cipher.doFinal(base64Encoder.decode(input));
		return new String(decrypted);
	}
	
}
