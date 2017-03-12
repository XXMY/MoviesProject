package com.cfw.movies.commons.enums;

/**
 * Created by Duskrain on 2017/3/12.
 */
public enum ResponseTypeEnum {
    SYSTEM_ERROR(-1,"系统错误"),
    SUCCESS(0,"成功"),
    USER_EXISTS(1001,"用户已存在"),
    USER_NOT_EXISTS(1002,"用户不存在"),
    USER_LOGINED(1003,"用户已登录"),
    USER_NOT_LOGINED(1004,"用户未登录");

    private int code;
    private String description;

    ResponseTypeEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
