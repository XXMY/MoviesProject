package com.cfw.movies.recommend.service;

import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.vo.MoviesResponse;

import java.util.List;

/**
 * Recommend service, create a new thread to 
 * do the business, and pack the latest status
 * of recommend from thread into critical resource.
 * @author Fangwei_Cai
 * @time since 2016年5月15日 下午9:00:09
 */
public interface RecommendService {
	
	/**
	 * Create a new thread to recommend.
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 下午9:08:39
	 * @return
	 */
	boolean startRecommend();
	
	/**
	 * Get the newest status of recommend.
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 下午9:10:58
	 * @return
	 */
	MoviesResponse getRecommendStaus();
	
	/**
	 * Save recommended movies that are not watched.
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 下午1:18:13
	 * @return
	 */
	boolean processRecommendData();

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:47:25
	 * @param userKey
	 * @return
	 */
	List<Movie> getRecommendMovies(String userKey);
}
