package com.cfw.movies.commons.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月4日 下午10:03:50
 */
public class Comment implements Serializable{

	private static final long serialVersionUID = 6791938201881473975L;
	private Integer id;
	
	private String comment;
	
	private float score;
	
	private User user;
	
	private Integer mid;
	
	private Date create_time;
	
	private int like;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer id, String comment, float score, Integer mid, Date create_time) {
		super();
		this.id = id;
		this.comment = comment;
		this.score = score;
		this.mid = mid;
		this.create_time = create_time;
	}

	public Comment(Integer id, String comment, Float score, Integer mid, Date create_time, Integer like) {
		super();
		this.id = id;
		this.comment = comment;
		this.score = score;
		this.mid = mid;
		this.create_time = create_time;
		this.like = like;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Date getCreate_time() {
		return create_time;
	}
	
	public String getCreate_timeString(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		
		return simpleDateFormat.format(create_time);
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	
	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", score=" + score + ", user=" + user + ", mid=" + mid
				+ ", create_time=" + create_time + ", like=" + like + "]";
	}

	
}
