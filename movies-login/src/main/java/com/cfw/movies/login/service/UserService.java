package com.cfw.movies.login.service;

import com.cfw.movies.commons.model.User;

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
	User userLogin(String sessionId, String username, String password);

	/**
	 * Check whether user logined.</br>
	 * Use session id to get information from cache.
	 * @param sessionId
	 * @return user's brief information
	 * @author CaiFangwei
	 * @time since 2017-3-12 17:03:54
	 */
	User checkLogined(String sessionId);


}
