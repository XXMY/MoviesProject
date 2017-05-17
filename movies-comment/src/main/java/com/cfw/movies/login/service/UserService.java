package com.cfw.movies.login.service;

import com.cfw.movies.commons.model.User;

/**
 * 用户注册服务接口
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:38:30
 */
public interface UserService {


	/**
	 * Get user's brief information through user's name
	 * @param username
	 * @return Brief information of user.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:09:53
	 */
	User getBriefInfo(String username);

}
