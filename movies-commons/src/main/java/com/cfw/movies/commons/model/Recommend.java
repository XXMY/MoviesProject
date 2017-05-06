package com.cfw.movies.commons.model;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月29日 上午11:13:06
 */
public class Recommend {
	
	private Long id;
	
	private Long uid;
	
	private Long mid;
	
	private int pref;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
