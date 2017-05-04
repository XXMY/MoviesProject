package com.cfw.movies.home.dao;

import com.cfw.movies.commons.model.Comments;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月1日 上午11:43:19
 */
public interface CommentsDao {
	
	/**
	 * Insert one comment.
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 上午11:44:03
	 * @param comment
	 * @return
	 */
	int insertComment(Comments comment);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午11:01:46
	 * @param mid
	 * @return
	 */
	List<Comments> selectCommentsOfMovie(int mid);
}
