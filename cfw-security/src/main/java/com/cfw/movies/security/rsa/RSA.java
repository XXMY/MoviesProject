package com.cfw.movies.security.rsa;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Do RSA encode and decode works.
 * @author Fangwei_Cai
 * @time since 2016年4月15日 上午9:39:03
 */
public class RSA {

	public final static String algorithm = "RSA";
	public final static int keySize = 1024;
	public final static String cipherAlgorihtm = "RSA/ECB/PKCS1Padding";
	
	private static Cipher cipher;
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午9:48:59
	 */
	public static void initRSA(String cipherAlgorihtm){
		try {
			cipher = Cipher.getInstance(cipherAlgorihtm);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午9:44:31
	 */
	public static byte[] encode(PublicKey publicKey, byte[] plainText){
		try{
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte [] encoded = cipher.doFinal(plainText);
			
			return encoded;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午9:49:40
	 */
	public static byte[] decode(PrivateKey privateKey, byte[] encoded){
		try{
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte [] decoded = cipher.doFinal(encoded);
			
			return decoded;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 下午2:42:48
	 */
	public static String decodeBase64String(PrivateKey privateKey, String encodedBase64String) throws Exception {
		//byte [] encodedByte = DatatypeConverter.parseBase64Binary(encodedBase64String);
		byte [] encodedByte = Base64Utils.decode(encodedBase64String.getBytes());

		byte [] decoded = null;
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		decoded = cipher.doFinal(encodedByte);

		String decodedString = new String(decoded);
		return decodedString;
	}
}
