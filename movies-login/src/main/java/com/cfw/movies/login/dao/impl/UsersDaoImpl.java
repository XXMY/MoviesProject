package com.cfw.movies.login.dao.impl;

import com.cfw.movies.commons.model.User;
import com.cfw.movies.login.dao.UsersDao;
import com.cfw.movies.login.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午4:56:41
 */
@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private UsersMapper usersMapper;

	@Override
	public int checkUser(String userName) {
		return 0;
	}

	/**
	 * @author fwCai
	 * @since 2016.03.26 20:11
	 */
	@Override
	public int updateUser(User user) {
		
		int result = usersMapper.updateOne(user);
		
		return result;
	}

	/**
	 * @author fwCai
	 * @since 2016.03.27 10:08
	 */
	@Override
	public int addUser(User user) {
		
		int result = usersMapper.insertOne(user);
		
		return result;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:16:42
	 */
	@Override
	public User selectUserInBrief(String username) {
		User user = usersMapper.selectUserInBriefByName(username);
		return user;
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 * @author CaiFangwei
	 * @time since 2017-3-12 18:42:02
	 */
	@Override
	public User selectUserInBrief(String username, String password) {
		User user = this.usersMapper.selectUserInBrief(username,password);
		return user;
	}
}
