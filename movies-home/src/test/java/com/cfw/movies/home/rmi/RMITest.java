package com.cfw.movies.home.rmi;

import com.cfw.movies.home.Application;
import com.cfw.movies.comment.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Duskrain on 2017/5/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RMITest {

    @Resource(name = "commentService")
    private CommentService commentService;

    @Test
    public void test(){
        System.out.println(commentService.getCommentsOfMovie(1));
    }
}
