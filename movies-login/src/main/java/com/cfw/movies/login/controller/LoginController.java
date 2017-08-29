package com.cfw.movies.login.controller;

import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.commons.vo.MoviesResponse;
import com.cfw.movies.commons.vo.RsaVO;
import com.cfw.movies.login.service.UserService;
import com.cfw.plugins.security.rsa.RSA;
import com.cfw.plugins.security.rsa.RSAKeyPairs;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	@Resource(name = "userService")
	private UserService userService;

	private Log logger = LogFactory.getLog(LoginController.class);

	/**
	 * Check user whether logined.
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午10:11:32
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logined",method=RequestMethod.GET)
	@ResponseBody
	public MoviesResponse logined(HttpSession session){
		this.logger.info("[/Login/logined]");

		MoviesResponse result = new MoviesResponse();
		// Get the information from cache.
		User user = this.userService.checkLogined(session.getId());
		if(user == null){
			result = buildResponse(ResponseTypeEnum.USER_NOT_LOGINED);
		}else{
			result = buildResponse(ResponseTypeEnum.SUCCESS);
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
	public MoviesResponse login(HttpSession session, RsaVO rsaVO){
		MoviesResponse result = new MoviesResponse();
		User user = null;

		try{
			String decoded = RSA.decodeBase64String((PrivateKey) RSAKeyPairs.publicPrivateKeys[1].get(rsaVO.getV()),rsaVO.getData());
			Gson gson = new Gson();
			user = (User)gson.fromJson(decoded,User.class);
			user = userService.userLogin(session.getId(),user.getUsername(),user.getPassword());
		}catch(Exception e){
			user = null;
		}

		if(user != null){
			result = buildResponse(ResponseTypeEnum.SUCCESS);
		}else{
			result = buildResponse(ResponseTypeEnum.USER_NOT_EXISTS);
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
	public MoviesResponse userLogout(HttpSession session){
		session.invalidate();
		
		return buildResponse(ResponseTypeEnum.SUCCESS);
	}
}
