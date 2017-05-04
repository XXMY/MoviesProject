package com.cfw.movies.home.dao.impl;

import com.cfw.movies.commons.model.Recommends;
import com.cfw.movies.commons.model.Users;
import com.cfw.movies.home.dao.RecommendsDao;
import com.cfw.movies.home.mapper.RecommendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月31日 下午7:04:41
 */
@Repository
public class RecommendsDaoImpl implements RecommendsDao {

	@Autowired
	private RecommendsMapper recommendsMapper;
	
	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:37:07
	 */
	@Override
	public int insertOne(Recommends recommend) {
		
		int result = this.recommendsMapper.insertOne(recommend);
		
		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:04:48
	 */
	@Override
	public int selectRecommendedMoviesCount(Users user) {
		int count = this.recommendsMapper.selectRecommendedMoviesCount(user);
		return count;
	}

}
