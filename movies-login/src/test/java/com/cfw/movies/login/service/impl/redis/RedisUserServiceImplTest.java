package com.cfw.movies.login.service.impl.redis;

import com.cfw.movies.login.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Duskrain on 2017/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisUserServiceImplTest {

    @Resource(name = "redisUserServiceImpl")
    private RedisUserServiceImpl redisUserService;

    @Test
    public void test(){
        System.out.println();
    }
}
