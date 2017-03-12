package com.cfw.movies.login.dao;


import com.cfw.movies.commons.model.Users;

/**
 * Operation interface of users' data.
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:40:03
 */
public interface UsersDao {

	/**
	 * Use user's name to count.
	 * @param userName
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午4:48:41
	 */
	int checkUser(String userName);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午8:10:47
	 */
	int updateUser(Users user); 
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月27日 上午10:07:00
	 */
	int addUser(Users user);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:16:26
	 * @param username
	 * @return
	 */
	Users selectUserInBrief(String username);

	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 * @author CaiFangwei
	 * @time since 2017-3-12 18:42:02
	 */
	Users selectUserInBrief(String username,String password);
	
}
