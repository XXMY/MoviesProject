package com.cfw.movies.login.service.impl.redis;

import com.cfw.movies.login.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Duskrain on 2017/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisUserServiceImplTest {

    @Autowired
    private FileSystemXmlApplicationContext fileSystemXmlApplicationContext;

    @Test
    public void test(){
        System.out.println();
    }
}
