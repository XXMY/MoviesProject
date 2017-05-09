package com.cfw.movies.commons.model;

import java.util.Date;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月4日 下午9:34:30
 */
public class User {
	
	private Integer id;
	private String userKey;
	private String username;
	private String password;
	private String head_pic;
	private boolean type;
	private Date registerTime;
	private Date lastLogin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHead_pic() {
		return head_pic;
	}
	public void setHead_pic(String head_pic) {
		this.head_pic = head_pic;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", head_pic=" + head_pic
				+ ", type=" + type + "]";
	}
	
	
}
