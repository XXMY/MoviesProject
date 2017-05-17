package com.cfw.movies.comment.service.impl;

import com.cfw.movies.login.service.UserService;
import com.cfw.movies.comment.dao.CommentDao;
import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.commons.model.Comment;
import com.cfw.movies.commons.model.User;
import com.cfw.plugins.rmi.annotation.CRmiExport;
import com.cfw.plugins.rmi.annotation.CRmiImport;
import com.cfw.plugins.rmi.annotation.CRmiImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Cfw on 2017/5/5.
 */
@Service("commentService")
@CRmiImport
@CRmiExport
public class CommentServiceImpl implements CommentService{

    @Resource(name = "commentDao")
    private CommentDao commentDao;

    @Autowired
    //@Resource(name = "userService")
    @Lazy
    @CRmiImportService
    private UserService userService;

    /**
     * Add a new comment.
     *
     * @param movieId
     * @param username
     * @param comment
     * @param score
     * @return
     * @author Fangwei_Cai
     * @time since 2016年5月1日 上午11:48:13
     */
    @Override
    public boolean addComment(Integer movieId, String username, String comment, float score) {
        Comment c = new Comment();
        c.setMid(movieId);

        User user = this.userService.getBriefInfo(username);
        c.setUser(user);
        c.setComment(comment);
        c.setScore(score);

        return this.commentDao.insertComment(c) > 0;
    }

    /**
     * @param movieId
     * @return
     * @author Fangwei_Cai
     * @time since 2016年5月7日 上午11:08:55
     */
    @Override
    public List<Comment> getCommentsOfMovie(Integer movieId) {
        return this.commentDao.selectCommentsOfMovie(movieId);
    }
}
