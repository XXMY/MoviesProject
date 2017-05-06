package com.cfw.movies.recommend.mapper;

import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.Recommend;
import com.cfw.movies.commons.model.User;
import com.cfw.plugins.database.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:24:56
 */
@Repository
@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {

	/**
	 * While the number of recommended movies larger than zero,then select movies.
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:59:15
	 * @param userId
	 * @return
	 */
	int selectRecommendedMoviesCount(@Param("userId") Integer userId);

	/**
	 * Select five movies which score is in top list.
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:51:40
	 * @return
	 */
	List<Movie> selectTopScoreMoviesToRecommend();

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:58:16
	 * @param user
	 * @return
	 */
	List<Movie> selectRecommendedMovies(@Param("userId") Integer userId);
}
