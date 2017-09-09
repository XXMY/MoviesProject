package com.cfw.movies.register.service.impl;

import com.cfw.movies.commons.model.User;
import com.cfw.movies.register.service.UserService;
import com.cfw.plugins.mq.rabbitmq.rpc.client.dispatch.OutboundDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午8:07:16
 */
@Service("registerServiceImpl")
public class UserServiceImpl implements UserService {

	private Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private OutboundDispatcher outboundDispatcher;

	@Value("${movies.remote.user}")
	private String remoteUser;
	@Value("${movies.remote.userService}")
	private String remoteService;
	@Value("${movies.remote.userService.userExists}")
	private String remoteUserExists;
	@Value("${movies.remote.userService.register}")
	private String remoteRegister;
	
	/**
	 * @author fwCai
	 * @since 2016.03.26 20:12
	 */
	@Override
    public boolean userExists(String username) {
		try {
			return this.outboundDispatcher.dispatch(remoteUser,remoteService,remoteUserExists,Boolean.class,username);
		} catch (Exception e) {
			this.logger.error(e.getMessage(),e);
		}
		return false;
	}

	/**
	 * @author fwCai
	 * @since 2016.03.27 10:11
	 */
	@Override
	public boolean register(User user) {
		try {
			return this.outboundDispatcher.dispatch(remoteUser,remoteService,remoteRegister,Boolean.class,user);
		} catch (Exception e) {
			this.logger.error(e.getMessage(),e);
		}
		return false;
	}


}
