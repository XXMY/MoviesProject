package com.cfw.movies.login.service;

import com.cfw.movies.commons.model.Users;

/**
 * 用户注册服务接口
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:38:30
 */
public interface UserService {

	/**
	 * Check whether user exists.
	 * @param userName
	 * @return true if user exists, otherwise false.
	 *
	 */
	boolean userExists(String userName);
	
	/**
	 * The value of username attribute cannot empty.
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午8:05:26
	 */
	boolean modifyUsersInfo(Users newUser);


}
