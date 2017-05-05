package com.cfw.movies.comment.service.impl;

import com.cfw.movies.comment.dao.CommentDao;
import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.commons.model.Comments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Cfw on 2017/5/5.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Resource(name = "commentDao")
    private CommentDao commentDao;

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
        return false;
    }

    /**
     * @param movieId
     * @return
     * @author Fangwei_Cai
     * @time since 2016年5月7日 上午11:08:55
     */
    @Override
    public List<Comments> getCommentsOfMovie(Integer movieId) {
        return null;
    }
}
