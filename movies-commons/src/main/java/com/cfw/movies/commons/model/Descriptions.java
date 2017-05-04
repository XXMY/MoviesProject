package com.cfw.movies.commons.model;


/**
 * @author Fangwei_Cai
 * @time since 2016年3月4日 下午10:04:52
 */
public class Descriptions {
	
	private int id;

	private String abstract_;

	private String description;

	private boolean isdeleted;

	public Descriptions() {
		super();
	}

	public Descriptions(String description){
		this.description = description;
		this.isdeleted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbstract_() {
		return abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "Descriptions [id=" + id + ", abstract_=" + abstract_ + ", description=" + description + ", isdeleted="
				+ isdeleted + "]";
	}
	
	
	
}
