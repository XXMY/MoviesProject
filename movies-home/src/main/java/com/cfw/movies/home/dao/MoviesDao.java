package com.cfw.movies.home.dao;

import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.model.Movies;
import com.cfw.movies.commons.model.Users;

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
	int insertMovie(Movies movies);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 上午10:24:53
	 * @param map
	 * @return List<Movies>
	 */
	List<Movies> selectMovies(Map<String, Object> map);
	List<Movies> selectMovies(Page page);


	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午6:39:51
	 * @param map
	 * @return
	 */
	List<Movies> selectFullMovies(Map<String, Object> map);
	List<Movies> selectFullMovies(Page page);

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
	Movies selectOne(int id);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:02:43
	 * @param map
	 * @return
	 */
	List<Movies> selectPic(Map<String, Object> map);

	List<Movies> selectPic(int start, int length);

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
	int updateMovie(Movies movie);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:58:16
	 * @param user
	 * @return
	 */
	List<Movies> selectRecommendedMovies(Users user);
	
	/**
	 * Select five movies which score is in top list. 
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:51:40
	 * @return
	 */
	List<Movies> selectTopScoreMoviesToRecommend();
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午5:10:20
	 * @param mid
	 * @return
	 */
	Integer selectDesciptionId(int mid);
	
}
