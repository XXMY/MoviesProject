package com.cfw.movies.home.dao;

import com.cfw.movies.commons.model.Recommends;
import com.cfw.movies.commons.model.Users;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:35:13
 */
public interface RecommendsDao {

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:36:07
	 * @param recommend
	 * @return
	 */
	int insertOne(Recommends recommend);
	
	/**
	 * While the number of recommended movies larger than zero,then select movies.
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:59:15
	 * @param user
	 * @return
	 */
	int selectRecommendedMoviesCount(Users user);
}
