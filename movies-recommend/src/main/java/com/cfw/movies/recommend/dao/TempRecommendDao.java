package com.cfw.movies.recommend.dao;

import com.cfw.movies.commons.model.Recommend;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.recommend.mapper.TempRecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:29:10
 */
@Repository
public class TempRecommendDao {

	@Autowired
	private TempRecommendMapper tempRecommendMapper;

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:12:31
	 * @return
	 */
	public List<User> selectRecommendUsers(){
		return this.tempRecommendMapper.selectRecommendUsers();
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 上午11:18:31
	 * @param user
	 * @return
	 */
	public List<Recommend> selectRecommends(User user){
		return this.tempRecommendMapper.selectRecommends(user);
	}
	
}
