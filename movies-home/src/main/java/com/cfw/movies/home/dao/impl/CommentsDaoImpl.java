package com.cfw.movies.home.dao.impl;

import com.cfw.movies.commons.model.Comments;
import com.cfw.movies.home.dao.CommentsDao;
import com.cfw.movies.home.mapper.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月1日 上午11:44:42
 */
@Repository("commentsDaoImpl")
public class CommentsDaoImpl implements CommentsDao {

	@Autowired
	private CommentsMapper commentsMapper;
	
	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 上午11:45:08
	 */
	@Override
	public int insertComment(Comments comment) {
		
		int result = this.commentsMapper.insertOne(comment);
		
		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午11:02:05
	 */
	@Override
	public List<Comments> selectCommentsOfMovie(Long mid) {
		List<Comments> comments = this.commentsMapper.selectComments(mid);
		
		return comments;
	}

}
