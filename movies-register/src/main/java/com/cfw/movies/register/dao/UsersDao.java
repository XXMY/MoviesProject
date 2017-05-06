package com.cfw.movies.register.dao;

import com.cfw.movies.commons.model.User;

/**
 * Operation interface of users' data.
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:40:03
 */
public interface UsersDao {
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月27日 上午10:07:00
	 */
	int addUser(User user);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:16:26
	 * @param username
	 * @return
	 */
	User selectUserByName(String username);

	
}
