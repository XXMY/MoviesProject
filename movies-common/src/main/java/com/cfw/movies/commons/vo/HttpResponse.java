package com.cfw.movies.commons.vo;

import java.util.UUID;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月11日 上午10:32:08
 */
public class HttpResponse {

	private int code;

	private String message;

	private Object object;

	private String requestId;

	public HttpResponse() {
		super();
		this.requestId = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}

	public HttpResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "HttpResponse{" +
				"code=" + code +
				", message='" + message + '\'' +
				", object=" + object +
				", requestId='" + requestId + '\'' +
				'}';
	}
}
