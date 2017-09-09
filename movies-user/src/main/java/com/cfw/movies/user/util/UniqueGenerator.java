package com.cfw.movies.user.util;

import com.cfw.movies.commons.enums.AccountTypeEnum;
import com.cfw.movies.commons.enums.RedisKeyEnum;
import com.cfw.movies.commons.utils.DateHelper;
import com.cfw.plugins.redis.CRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Duskrain on 2017/5/8.
 */
@Component
public class UniqueGenerator {

    @Autowired
    private CRedis redis;

    public String newUserKey(AccountTypeEnum accountType){
        Long today = DateHelper.firstTimeofDate(new Date()).getTime() / 1000;
        String key = String.format(RedisKeyEnum.USER_REGISTER_INCREASE.key, DateHelper.dateString());
        Long increase = this.redis.incr(key);

        if(increase == 1L)
            this.redis.expire(key,DateHelper.toEndOfToday() / 1000);

        return accountType.type + today + String.format("%010d",increase);
    }

}
