package com.cfw.movies.login.service;

import com.cfw.movies.commons.model.Users;

/**
 * 用户注册服务接口
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:38:30
 */
public interface UserService {

	/**
	 * User login.<br/>
	 * Check whether username and password valid.
	 * @param sessionId
	 * @param username
	 * @param password
	 * @return user's information to display.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:31:58
	 */
	Users userLogin(String sessionId, String username, String password);

	/**
	 * Check whether user logined.</br>
	 * Use session id to get information from cache.
	 * @param sessionId
	 * @return user's brief information
	 * @author CaiFangwei
	 * @time since 2017-3-12 17:03:54
	 */
	Users checkLogined(String sessionId);

	/**
	 * Get user's brief information through user's name
	 * @param username
	 * @return Brief information of user.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:09:53
	 */
	Users getBriefInfo(String username);
	/**
	 * Get user's brief information through user's name and password
	 * @param username
	 * @return Brief information of user.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:34:17
	 */
	Users getBriefInfo(String username, String password);
	
	/**
	 * The value of username attribute cannot empty.
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午8:05:26
	 */
	boolean modifyUsersInfo(Users newUser);


}
