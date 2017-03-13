package com.cfw.movies.commons.security.rsa;

import com.cfw.movies.commons.properties.CommonProperties;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.cfw.movies.commons.security.rsa.RSA.initRSA;
import static com.cfw.movies.commons.security.rsa.RSAKeyGenerator.generateKey;
import static com.cfw.movies.commons.security.rsa.RSAKeyGenerator.initGenerator;
import static com.cfw.movies.commons.security.rsa.RSAKeyRestore.initRestore;

/**
 * Created by Duskrain on 2017/1/17.
 */
public class RSAKeyPairs {

    public final static Integer keyNumbers = CommonProperties.getRsaKeyPairNumber();
    public final static Map<Integer,Key>[] publicPrivateKeys = new HashMap[2];

    static {
        try {
            initGenerator(RSA.algorithm, RSA.keySize,true);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }
        initRestore(RSA.algorithm);
        initRSA(RSA.cipherAlgorihtm);

        generateKey(publicPrivateKeys,keyNumbers);
    }
}
