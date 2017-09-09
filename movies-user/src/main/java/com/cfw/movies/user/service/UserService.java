package com.cfw.movies.user.service;

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
	/**
	 * Get user's brief information through user's name and password
	 * @param username
	 * @return Brief information of user.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:34:17
	 */
	User getBriefInfo(String username, String password);
	
	/**
	 * The value of username attribute cannot empty.
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午8:05:26
	 */
	boolean modifyUsersInfo(User newUser);

	/**
	 * Check whether user exists.
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午7:38:17
	 */
	boolean userExists(String userName);


	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月27日 上午10:05:11
	 */
	boolean register(User user);

}
