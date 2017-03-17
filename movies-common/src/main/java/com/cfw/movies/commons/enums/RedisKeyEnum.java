package com.cfw.movies.commons.enums;

/**
 * Created by Duskrain on 2017/3/12.
 */
public enum RedisKeyEnum {
    USER_LOGIN_CACHE("movies:user:logined:%s");

    public final String key;

    RedisKeyEnum(String key){
        this.key = key;
    }

}
