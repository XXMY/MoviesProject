package com.cfw.movies.home.dao;

import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午4:18:10
 */
public interface MoviesDao {
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:18:42
	 */
	int insertMovie(Movie movies);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 上午10:24:53
	 * @param map
	 * @return List<Movie>
	 */
	List<Movie> selectMovies(Map<String, Object> map);
	List<Movie> selectMovies(Page page);


	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午6:39:51
	 * @param map
	 * @return
	 */
	List<Movie> selectFullMovies(Map<String, Object> map);
	List<Movie> selectFullMovies(Page page);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午9:56:23
	 * @return
	 */
	Integer selectCount();

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午12:25:57
	 * @param id
	 * @return
	 */
	Movie selectOne(int id);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:02:43
	 * @param map
	 * @return
	 */
	List<Movie> selectPic(Map<String, Object> map);

	List<Movie> selectPic(int start, int length);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:03:02
	 * @param map
	 * @return
	 */
	int updatePic(Map<String, Object> map);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午10:05:45
	 * @param movie
	 * @return
	 */
	int updateMovie(Movie movie);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:58:16
	 * @param user
	 * @return
	 */
	List<Movie> selectRecommendedMovies(User user);
	
	/**
	 * Select five movies which score is in top list. 
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:51:40
	 * @return
	 */
	List<Movie> selectTopScoreMoviesToRecommend();
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午5:10:20
	 * @param mid
	 * @return
	 */
	Integer selectDesciptionId(int mid);
	
}
