package com.cfw.movies.security.rsa;

import org.springframework.util.Base64Utils;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA public and private key generator.
 * @author Fangwei_Cai
 * @time since 2016年4月14日 下午5:14:44
 */
public class RSAKeyGenerator {

	private static KeyPairGenerator Key_Pair_Generator = null;
	private static KeyPair Key_Pair = null;
	
	private static RSAPublicKey public_Key = null;
	private static RSAPrivateKey private_Key = null;
	
	/**
	 * Initialize the RSAKeyUtil.
	 * @author Fangwei_Cai
	 * @throws NoSuchAlgorithmException
	 * @time since 2016年4月14日 下午5:21:32
	 */
	public static void initGenerator(String algorithm, int keySize, boolean create) throws NoSuchAlgorithmException {
		Key_Pair_Generator = KeyPairGenerator.getInstance(algorithm);
		Key_Pair_Generator.initialize(keySize);
		if(create){
			generateKey();
		}
	}
	
	public static void generateKey(){
		Key_Pair = Key_Pair_Generator.generateKeyPair();
		public_Key = (RSAPublicKey) Key_Pair.getPublic();
		private_Key = (RSAPrivateKey) Key_Pair.getPrivate();
	}
	
	/**
	 * Generate a number of public and private key pair into
	 * an array of map;
	 * @param pubPriKey {Map<Integer,RSAKey> []} empty map array
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午10:31:03
	 */
	public static void generateKey(Map<Integer,Key>[] pubPriKey, int number){
		pubPriKey[0] = new HashMap<Integer,Key>();
		pubPriKey[1] = new HashMap<Integer,Key>();
		for(int i=0;i<number;i++){
			Key_Pair = Key_Pair_Generator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) Key_Pair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) Key_Pair.getPrivate();
			pubPriKey[0].put(i, publicKey);
			pubPriKey[1].put(i, privateKey);
		}
		
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午11:55:35
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer,String>[] toBase64String(Map<Integer,Key>[] pubPriKey){
		int size = pubPriKey[0].size();
		HashMap<Integer,String> publicKey = new HashMap<Integer,String>();
		HashMap<Integer,String> privateKey = new HashMap<Integer,String>();
		for(int i=0;i<size;i++){
			String publicBase64Str = new String(Base64Utils.encode(pubPriKey[0].get(i).getEncoded()));
			String privateBase64Str = new String(Base64Utils.encode(pubPriKey[1].get(i).getEncoded()));
			publicKey.put(i, publicBase64Str);
			privateKey.put(i, privateBase64Str);
		}
		
		return new HashMap[]{publicKey,privateKey};
	}
	
	public static RSAPublicKey getPublicKey(){
		return public_Key;
	}
	
	public static RSAPrivateKey getPrivateKey(){
		return private_Key;
	}
	
	public static String getBase64PublicKey(){
		byte [] base64Key = Base64Utils.encode(public_Key.getEncoded());
		String keyString = new String(base64Key);
		return keyString;
	}
	
	public static String getBase64PrivateKey(){
		byte [] base64Key = Base64Utils.encode(private_Key.getEncoded());
		String keyString = new String(base64Key);
		return keyString;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月15日 上午10:18:10
	 */
	public static void setEncodedKeyMaps(Map<Integer,byte[]> publicKeyMap, Map<Integer,byte[]> privateKeyMap, int number){
		for(int i=0;i<number;i++){
			generateKey();
			publicKeyMap.put(i, public_Key.getEncoded());
			privateKeyMap.put(i, private_Key.getEncoded());
		}
	}
	
	
	
}
