package com.cfw.movies.commons.controller;

import com.cfw.movies.commons.vo.HttpResponse;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月18日 下午1:54:55
 */
public abstract class BaseController {

	/**
	 * Build a http response.
	 * @param code
	 * @param message
	 * @return
	 *
	 * @author CaiFangwei
	 * @time since 2017-3-11 19:49:21
	 */
	public HttpResponse buildHttpResponse(int code, String message){
		HttpResponse httpResponse = new HttpResponse(code,message);
		
		return httpResponse;
	}
}
