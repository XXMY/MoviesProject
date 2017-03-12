package com.cfw.movies.login.service.impl.redis;

import com.cfw.movies.login.service.impl.UserServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Duskrain on 2017/3/12.
 */
@Service("redisUserServiceImpl")
public class RedisUserServiceImpl extends UserServiceImpl {

    @Override
    public boolean userExists(String userName) {
        this.redisGet(userName);
        return false;
    }
}
