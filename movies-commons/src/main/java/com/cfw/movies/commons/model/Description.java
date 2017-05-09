package com.cfw.movies.commons.model;


/**
 * @author Fangwei_Cai
 * @time since 2016年3月4日 下午10:04:52
 */
public class Description {
	
	private Integer id;

	private String abstract_;

	private String description;

	private boolean isdeleted;

	public Description() {
		super();
	}

	public Description(String description){
		this.description = description;
		this.isdeleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return "Description [id=" + id + ", abstract_=" + abstract_ + ", description=" + description + ", isdeleted="
				+ isdeleted + "]";
	}
	
	
	
}
