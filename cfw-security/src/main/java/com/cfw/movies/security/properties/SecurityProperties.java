package com.cfw.movies.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Duskrain on 2017/3/13.
 */
@Component
@ConfigurationProperties(prefix = "movies")
public class SecurityProperties {
    private static int rsaKeyPairNumber = 5;

    public static int getRsaKeyPairNumber() {
        return rsaKeyPairNumber;
    }

    public static void setRsaKeyPairNumber(int rsaKeyPairNumber) {
        SecurityProperties.rsaKeyPairNumber = rsaKeyPairNumber;
    }
}
