package com.cfw.movies.commons.controller;

import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.vo.MoviesResponse;

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
	public MoviesResponse buildResponse(int code, String message){
		MoviesResponse moviesResponse = new MoviesResponse(code,message);
		
		return moviesResponse;
	}

	public MoviesResponse buildResponse(ResponseTypeEnum type){

		return this.buildResponse(type.getCode(),type.getDescription());
	}
}
