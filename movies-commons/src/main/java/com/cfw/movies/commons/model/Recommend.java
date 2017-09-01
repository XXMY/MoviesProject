package com.cfw.movies.commons.model;

import java.io.Serializable;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:13:06
 */
public class Recommend implements Serializable{

	private static final long serialVersionUID = 969985057126512889L;
	private Integer id;
	
	private Long uid;
	
	private Long mid;
	
	private int pref;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public int getPref() {
		return pref;
	}

	public void setPref(int pref) {
		this.pref = pref;
	}

	@Override
	public String toString() {
		return "Recommends [id=" + id + ", uid=" + uid + ", mid=" + mid + ", pref=" + pref + "]";
	}
	
	
}
