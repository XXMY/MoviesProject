package com.cfw.movies.commons.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月4日 下午9:32:14
 */
public class Movie implements Serializable{

	private static final long serialVersionUID = -8972860067021249138L;
	private Integer id;
	
	private String name;
	
	private String type;
	
	private Date time;

	// Main picture.
	private String pic;
	
	private Description description;
	
	private float score;
	
	private Date record_time;
	
	private boolean isdeleted = false;

	public Movie() {
		super();
		this.description = new Description();
	}
	
	public Movie(Integer id, String name, Float score){
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public Movie(Integer id, String name, String type, String pic, Float score){
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.pic = pic;
		this.score = score;
	}
	
	public Movie(String name, String type, Description description, String pic) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
		this.pic = pic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Date getRecord_time() {
		return record_time;
	}

	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", type=" + type + ", time=" + time + ", pic=" + pic
				+ ", description=" + description + ", score=" + score + ", record_time=" + record_time + ", isdeleted="
				+ isdeleted + "]";
	}

	
}
