package com.cfw.movies.home.mapper;

import com.cfw.movies.commons.model.Recommends;
import com.cfw.movies.commons.model.Users;
import com.cfw.plugins.database.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:24:56
 */
@Repository
@Mapper
public interface RecommendsMapper extends BaseMapper<Recommends> {

	/**
	 * While the number of recommended movies larger than zero,then select movies.
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午6:59:15
	 * @param user
	 * @return
	 */
	int selectRecommendedMoviesCount(Users user);
}
