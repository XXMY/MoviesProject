package com.cfw.movies.register.dao.impl;

import com.cfw.movies.commons.model.Users;
import com.cfw.movies.register.dao.UsersDao;
import com.cfw.movies.register.mapper.UsersMapper;
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

	/**
	 * @author fwCai
	 * @since 2016.03.27 10:08
	 */
	@Override
	public int addUser(Users user) {
		
		int result = usersMapper.insertOne(user);
		
		return result;
	}
	
	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:16:42
	 */
	@Override
	public Users selectUserByName(String username) {
		Users user = usersMapper.selectUserByName(username);
		return user;
	}


}
