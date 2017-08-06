package com.cfw.movies.commons.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Duskrain on 2017/3/13.
 */
@Component
@ConfigurationProperties(prefix = "movies")
public class CommonProperties {

    private static String moduleName;

    private static int rsaKeyPairNumber = 5;

    public static int getRsaKeyPairNumber() {
        return rsaKeyPairNumber;
    }

    public static void setRsaKeyPairNumber(int rsaKeyPairNumber) {
        CommonProperties.rsaKeyPairNumber = rsaKeyPairNumber;
    }

    public static String getModuleName() {
        return moduleName;
    }

    public static void setModuleName(String moduleName) {
        CommonProperties.moduleName = moduleName;
    }
}
