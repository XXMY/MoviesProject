package com.cfw.movies.recommend.mapper;

import com.cfw.movies.commons.model.Recommend;
import com.cfw.movies.commons.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:06:36
 */
@Repository
@Mapper
public interface TempRecommendMapper {
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:12:31
	 * @return
	 */
	List<User> selectRecommendUsers();
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:18:31
	 * @param user
	 * @return
	 */
	List<Recommend> selectRecommends(User user);
}
