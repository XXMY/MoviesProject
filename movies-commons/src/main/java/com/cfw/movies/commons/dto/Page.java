package com.cfw.movies.commons.dto;

import java.io.Serializable;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月24日 上午11:13:47
 */
public class Page implements Serializable{

	private static final long serialVersionUID = 1401307508996387286L;
	private String keyword;

	private Long start;

	private int length;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
}
