package com.cfw.movies.user.dao;


import com.cfw.movies.commons.model.User;

/**
 * Operation interface of users' data.
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:40:03
 */
public interface UsersDao {
	// For Register
	/**
	 * Use user's name to count.
	 * @param username
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午4:48:41
	 */
	int selectUserNumber(String username);
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月27日 上午10:07:00
	 */
	int addUser(User user);

	// For reset
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午8:10:47
	 */
	int updateUser(User user);

	// For login
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:16:26
	 * @param username
	 * @return
	 */
	User selectUserByName(String username);
	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 * @author CaiFangwei
	 * @time since 2017-3-12 18:42:02
	 */
	User selectUserInBrief(String username, String password);
	
}
