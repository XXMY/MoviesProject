package com.cfw.movies.security.rsa;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月15日 上午9:29:36
 */
public class RSAKeyRestore {

	private static String Algorithm;
	private static KeyFactory keyFactory;
	
	
	public static void initRestore(String algorithm){
		Algorithm = algorithm;
		try {
			keyFactory = KeyFactory.getInstance(Algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午9:30:36
	 */
	public static PublicKey restorePublicKey(byte [] keyBytes){
		
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		try{
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			
			return publicKey;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午9:35:26
	 */
	public static PrivateKey restorePrivateKey(byte [] keyBytes){
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		try{
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			
			return privateKey;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
