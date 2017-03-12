package com.cfw.movies.login.service.impl;

import com.cfw.movies.commons.enums.RedisKeyEnum;
import com.cfw.movies.commons.model.Users;
import com.cfw.movies.commons.service.redis.BaseRedisService;
import com.cfw.movies.login.dao.UsersDao;
import com.cfw.movies.login.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午8:07:16
 */
@Service("userServiceImpl")
public class UserServiceImpl extends BaseRedisService implements UserService {

	@Autowired
	private UsersDao usersDaoImpl;

	/**
	 * User login.<br/>
	 * Check whether username and password valid.
	 *
	 * @param sessionId
	 * @param username
	 * @param password
	 * @return true if valid, otherwise false.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:31:58
	 */
	@Override
	public Users userLogin(String sessionId, String username, String password) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return null;

		// Verify and get user's information.
		Users user = this.getBriefInfo(username,password);
		if(user != null){
			// Cache with 5 hours.
			// Use session id to cache.
			Gson gson = new Gson();
			super.redisSet(sessionId,gson.toJson(user),5L, TimeUnit.HOURS);
			return user;
		}

		return null;
	}

	/**
	 * Check whether user logined.</br>
	 *
	 * @param sessionId
	 * @return
	 */
	@Override
	public Users checkLogined(String sessionId) {
		String cache = super.redisGet(RedisKeyEnum.USER_LOGIN_CACHE.key);
		Gson gson = new Gson();
		Users user = gson.fromJson(cache,Users.class);
		return user;
	}

	/**
	 * Get user's brief information through user's name
	 *
	 * @param username
	 * @return Brief information of user.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:09:53
	 */
	@Override
	public Users getBriefInfo(String username) {
		if(StringUtils.isEmpty(username))
			return null;

		return this.usersDaoImpl.selectUserInBrief(username);
	}

	/**
	 * Get user's brief information through user's name and password
	 *
	 * @param username
	 * @param password
	 * @return Brief information of user.
	 * @author CaiFangwei
	 * @time since 2017-3-12 16:34:17
	 */
	@Override
	public Users getBriefInfo(String username, String password) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return null;

		return this.usersDaoImpl.selectUserInBrief(username,password);
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
