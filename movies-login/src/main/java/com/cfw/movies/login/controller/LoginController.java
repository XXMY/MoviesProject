package com.cfw.movies.login.controller;

import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.model.Users;
import com.cfw.movies.commons.utils.CodeHelper;
import com.cfw.movies.commons.vo.HttpResponse;
import com.cfw.movies.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月1日 下午10:00:20
 */
@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Check user whether logined.
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午10:11:32
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logined",method=RequestMethod.GET)
	@ResponseBody
	public HttpResponse checkLogin(HttpSession session){
		HttpResponse result = null;
		// Get the information from session.
		Object object =  session.getAttribute(session.getId());
		
		if(CodeHelper.isNull(object)){
			result = buildHttpResponse(0, "用户未登录");
			return result;
		}
		
		result = buildHttpResponse(1, "用户已登录");
		result.setObject(object);
		
		return result;
	}
	
	/**
	 * 处理用户登录请求
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午11:03:04
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public HttpResponse userLogin(HttpSession session, Users user){
		HttpResponse result = null;
		
		boolean userExists = userService.userExists(user.getUsername());
		if(userExists){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("username", user.getUsername());
			map.put("type", user.isType());
			
			session.setAttribute(session.getId(), map);
			
			result = buildHttpResponse(1, "用户登录成功");
		}else{
			result = buildHttpResponse(0,"用户不存在");
		}
		
		
		return result;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午3:13:36
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public HttpResponse userLogout(HttpSession session){
		session.invalidate();
		
		return buildHttpResponse(1,"注销成功");
	}
}
