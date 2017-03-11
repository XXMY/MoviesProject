package com.cfw.movies.login.service.impl;

import com.cfw.movies.commons.model.Users;
import com.cfw.movies.login.dao.UsersDao;
import com.cfw.movies.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午8:07:16
 */
@Service("registerServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersDao usersDaoImpl;

	/**
	 * Check whether user is already exists
	 * @param userName
	 * @return true if exists, otherwise false.
	 *
	 * @author CaiFangwei
	 * @time since 2017-3-11 20:17:50
	 */
	@Override
	public boolean userExists(String userName) {

		return false;
	}

	/**
	 * @author fwCai
	 * @since 2016.06.26 20:13
	 */
	@Override
	public boolean modifyUsersInfo(Users newUser) {
		int result = usersDaoImpl.updateUser(newUser);
		
		if(result>0)
			return true;
		
		return false;
	}
}
