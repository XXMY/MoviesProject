package com.cfw.movies.register.service.impl;

import com.cfw.movies.commons.enums.AccountTypeEnum;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.register.dao.UsersDao;
import com.cfw.movies.register.service.UserService;
import com.cfw.movies.register.util.UniqueGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午8:07:16
 */
@Service("registerServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UniqueGenerator keyGenerator;

	@Autowired
	private UsersDao usersDaoImpl;
	
	/**
	 * @author fwCai
	 * @since 2016.03.26 20:12
	 */
	@Override
    public boolean userExists(String userName) {
		User user = usersDaoImpl.selectUserByName(userName);

		return user == null ? false : true;
	}

	/**
	 * @author fwCai
	 * @since 2016.03.27 10:11
	 */
	@Override
	public boolean register(User user) {
		boolean userExists = userExists(user.getUsername());
		if(!userExists){
			user.setUserKey(keyGenerator.newUserKey(AccountTypeEnum.MOVIE));

			return usersDaoImpl.addUser(user) > 0;
		}
		
		return false;
	}


}
