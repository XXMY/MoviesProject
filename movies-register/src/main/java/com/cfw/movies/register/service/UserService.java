package com.cfw.movies.register.service;

import com.cfw.movies.commons.model.Users;

/**
 * 用户注册服务接口
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:38:30
 */
public interface UserService {
	
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
	boolean register(Users user);

}
