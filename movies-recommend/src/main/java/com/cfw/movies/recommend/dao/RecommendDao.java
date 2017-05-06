package com.cfw.movies.recommend.dao;

import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.Recommend;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.recommend.mapper.RecommendMapper;
import com.cfw.movies.recommend.mapper.TempRecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:35:13
 */
@Repository
public class RecommendDao {

	@Autowired
	private RecommendMapper recommendMapper;

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:36:07
	 * @param recommend
	 * @return
	 */
	public int insertOne(Recommend recommend){
		return this.recommendMapper.insertOne(recommend);
	}
	
	/**
	 * While the number of recommended movies larger than zero,then select movies.
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:59:15
	 * @param userId
	 * @return
	 */
	public int selectRecommendedMoviesCount(Integer userId){
		return this.recommendMapper.selectRecommendedMoviesCount(userId);
	}

	/**
	 * Select five movies which score is in top list.
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:51:40
	 * @return
	 */
	public List<Movie> selectTopScoreMoviesToRecommend() {
		List<Movie> recommendMovies = this.recommendMapper.selectTopScoreMoviesToRecommend();

		return recommendMovies;
	}

	public List<Movie> selectRecommendedMovies(Integer userId) {
		List<Movie> recommendMovies = this.recommendMapper.selectRecommendedMovies(userId);

		return recommendMovies;
	}
}
