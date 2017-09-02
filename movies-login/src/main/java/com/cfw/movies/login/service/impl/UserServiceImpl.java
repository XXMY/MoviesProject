package com.cfw.movies.login.service.impl;

import com.cfw.movies.commons.enums.RedisKeyEnum;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.login.service.UserService;
import com.cfw.plugins.mq.rabbitmq.rpc.RemoteProcedureRequest;
import com.cfw.plugins.mq.rabbitmq.rpc.RemoteProcedureResponse;
import com.cfw.plugins.mq.rabbitmq.rpc.client.dispatch.OutboundDispatcher;
import com.cfw.plugins.mq.rabbitmq.send.RoutingSender;
import com.cfw.plugins.redis.CRedis;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午8:07:16
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private CRedis redis;

	@Autowired
	private OutboundDispatcher dispatcher;

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
	public User userLogin(String sessionId, String username, String password) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return null;

		// Verify and get user's information.
		User user = this.getBriefInfo(username,password);
		if(user != null){
			// Cache with 5 hours.
			// Use session id to cache.
			Gson gson = new Gson();
			redis.set(String.format(RedisKeyEnum.USER_LOGIN_CACHE.key,sessionId),gson.toJson(user),5L, TimeUnit.HOURS);
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
	public User checkLogined(String sessionId) {
		String cache = redis.get(String.format(RedisKeyEnum.USER_LOGIN_CACHE.key,sessionId));
		Gson gson = new Gson();
		User user = gson.fromJson(cache,User.class);
		return user;
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
	private User getBriefInfo(String username, String password) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return null;

		// Call remote procedure.
        List<Object> data = new ArrayList<>();
		data.add(username);
		data.add(password);
		try {
			return dispatcher.dispatch("movies-user","com.cfw.movies.user.service.UserService","getBriefInfo",data,new User());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
