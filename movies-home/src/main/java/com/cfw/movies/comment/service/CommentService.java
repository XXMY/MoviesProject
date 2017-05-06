package com.cfw.movies.comment.service;

import com.cfw.movies.commons.model.Comment;

import java.util.List;

/**
 * Created by Cfw on 2017/5/5.
 */
public interface CommentService {
    /**
     * Add a new comment.
     * @author Fangwei_Cai
     * @time since 2016年5月1日 上午11:48:13
     * @return
     */
    boolean addComment(Integer movieId, String username, String comment, float score);

    /**
     *
     * @author Fangwei_Cai
     * @time since 2016年5月7日 上午11:08:55
     * @param movieId
     * @return
     */
    List<Comment> getCommentsOfMovie(Integer movieId);
}
