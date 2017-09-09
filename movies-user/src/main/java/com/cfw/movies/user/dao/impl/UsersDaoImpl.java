package com.cfw.movies.user.dao.impl;

import com.cfw.movies.commons.model.User;
import com.cfw.movies.user.dao.UsersDao;
import com.cfw.movies.user.mapper.UsersMapper;
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
	public int selectUserNumber(String username) {
		return this.usersMapper.selectUserNumber(username);
	}
	/**
	 * @author fwCai
	 * @since 2016.03.27 10:08
	 */
	@Override
	public int addUser(User user) {
		return this.usersMapper.insertOne(user);
	}

	/**
	 * @author fwCai
	 * @since 2016.03.26 20:11
	 */
	@Override
	public int updateUser(User user) {
		return this.usersMapper.updateOne(user);
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:16:42
	 */
	@Override
	public User selectUserByName(String username) {
		return this.usersMapper.selectUserByName(username);
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
		return this.usersMapper.selectUserInBrief(username,password);
	}
}
