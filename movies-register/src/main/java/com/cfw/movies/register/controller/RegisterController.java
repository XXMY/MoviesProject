package com.cfw.movies.register.controller;

import com.cfw.movies.commons.model.Users;
import com.cfw.movies.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月26日 下午8:49:13
 */
@Controller
@RequestMapping(value="/register",method=RequestMethod.POST)
public class RegisterController {

	@Autowired
	private UserService userServiceImpl;
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月26日 下午8:05:55
	 */
	@RequestMapping(value="/userCheck")
	@ResponseBody
	public boolean userCheck(Users user){
		
		if(user.getUsername() == null) return false;
		
		boolean result = userServiceImpl.userExists(user.getUsername());
		
		return result;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年3月27日 上午10:15:19
	 */
	@RequestMapping("/registerUser")
	@ResponseBody
	public boolean userRegister(Users user){		
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty())
			return false;
		
		boolean result = userServiceImpl.register(user);
		
		return result;
		
	}
	
}
