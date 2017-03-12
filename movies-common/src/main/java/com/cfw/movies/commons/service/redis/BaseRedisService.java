package com.cfw.movies.commons.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Duskrain on 2017/3/12.
 */
@Service
public class BaseRedisService<K,V> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //@Autowired
    private RedisTemplate redisTemplate;

    public BaseRedisService(){
    }

    public String redisGet(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void redisSet(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public void redisSet(String key,String value,Long timeout){
        this.redisSet(key,value);
        stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
    }

    public void redisSet(String key,String value,Long timeout, TimeUnit unit){
        this.redisSet(key,value);
        stringRedisTemplate.expire(key,timeout,unit);
    }

}
