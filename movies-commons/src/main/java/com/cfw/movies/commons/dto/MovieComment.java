package com.cfw.movies.commons.dto;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月1日 上午11:22:46
 */
public class MovieComment {
	// movie id.
	private Long mid;
	
	// user id.
	private String username;
	
	// movie comment.
	private String comment;
	
	private float score;

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "MovieComment [mid=" + mid + ", username=" + username + ", comment=" + comment + ", score=" + score
				+ "]";
	}

	
}
