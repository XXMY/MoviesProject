package com.cfw.movies.commons.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Duskrain on 2017/3/13.
 */
@Component
@ConfigurationProperties(prefix = "movies")
public class CommonProperties {
    private static int rsaKeyPairNumber = 5;

    private static String commentRmiHost;
    private static int commentRmiPort;

    private static String loginRmiHost;
    private static int loginRmiPort;

    public static int getRsaKeyPairNumber() {
        return rsaKeyPairNumber;
    }

    public static void setRsaKeyPairNumber(int rsaKeyPairNumber) {
        CommonProperties.rsaKeyPairNumber = rsaKeyPairNumber;
    }

    public static int getCommentRmiPort() {
        return commentRmiPort;
    }

    public static void setCommentRmiPort(int commentRmiPort) {
        CommonProperties.commentRmiPort = commentRmiPort;
    }

    public static String getCommentRmiHost() {
        return commentRmiHost;
    }

    public static void setCommentRmiHost(String commentRmiHost) {
        CommonProperties.commentRmiHost = commentRmiHost;
    }

    public static String getCommentRmiUrl(String serviceName){
        return "rmi://" + commentRmiHost + ":" + commentRmiPort + "/" + serviceName;
    }

    public static String getLoginRmiHost() {
        return loginRmiHost;
    }

    public static void setLoginRmiHost(String loginRmiHost) {
        CommonProperties.loginRmiHost = loginRmiHost;
    }

    public static int getLoginRmiPort() {
        return loginRmiPort;
    }

    public static void setLoginRmiPort(int loginRmiPort) {
        CommonProperties.loginRmiPort = loginRmiPort;
    }

    public static String getLoginRmiUrl(String serviceName){
        return "rmi://" + loginRmiHost + ":" + loginRmiPort + "/" + serviceName;
    }
}
