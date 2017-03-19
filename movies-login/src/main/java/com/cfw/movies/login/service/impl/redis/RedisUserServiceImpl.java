package com.cfw.movies.login.service.impl.redis;

import com.cfw.movies.commons.plugins.redis.CRedis;
import com.cfw.movies.login.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Duskrain on 2017/3/12.
 */
@Service("redisUserServiceImpl")
public class RedisUserServiceImpl  {

    @Autowired
    private CRedis redis;

    public boolean userExists(String userName) {
        redis.get(userName);
        return false;
    }
}
