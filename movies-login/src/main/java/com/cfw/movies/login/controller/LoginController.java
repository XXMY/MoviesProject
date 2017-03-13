package com.cfw.movies.login.controller;

import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.model.Users;
import com.cfw.movies.commons.security.rsa.RSA;
import com.cfw.movies.commons.security.rsa.RSAKeyPairs;
import com.cfw.movies.commons.vo.HttpResponse;
import com.cfw.movies.commons.vo.RsaVO;
import com.cfw.movies.login.service.UserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月1日 下午10:00:20
 */
@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {
	
	@Resource(name = "userServiceImpl")
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
	public HttpResponse logined(HttpSession session){
		HttpResponse result = new HttpResponse();
		// Get the information from cache.
		Users user = this.userService.checkLogined(session.getId());
		if(user == null){
			result = buildHttpResponse(ResponseTypeEnum.USER_NOT_LOGINED);
		}else{
			result = buildHttpResponse(ResponseTypeEnum.USER_LOGINED);
			result.setData(user);
		}

		return result;
	}
	
	/**
	 * 处理用户登录请求
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午11:03:04
	 * @param rsaVO
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public HttpResponse login(HttpSession session, RsaVO rsaVO){
		HttpResponse result = new HttpResponse();
		Users user = null;

		try{
			String decoded = RSA.decodeBase64String((PrivateKey)RSAKeyPairs.publicPrivateKeys[1].get(rsaVO.getV()),rsaVO.getData());
			Gson gson = new Gson();
			user = (Users)gson.fromJson(decoded,Users.class);
			user = userService.userLogin(session.getId(),user.getUsername(),user.getPassword());
		}catch(Exception e){

		}

		if(user != null){
			result = buildHttpResponse(ResponseTypeEnum.SUCCESS);
		}else{
			result = buildHttpResponse(ResponseTypeEnum.USER_NOT_EXISTS);
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
		
		return buildHttpResponse(ResponseTypeEnum.SUCCESS);
	}
}
