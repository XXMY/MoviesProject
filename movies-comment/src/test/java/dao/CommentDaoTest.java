package dao;

import com.cfw.movies.comment.Application;
import com.cfw.movies.comment.dao.CommentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Duskrain on 2017/5/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void test(){
        System.out.println(commentDao.selectCommentsOfMovie(2100));
    }

}
