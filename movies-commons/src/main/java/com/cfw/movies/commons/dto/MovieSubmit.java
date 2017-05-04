package com.cfw.movies.commons.dto;

import java.util.Arrays;

/**
 * The DTO in submitting movies.
 * @author Fangwei_Cai
 * @time since 2016年4月9日 下午9:50:30
 */
public class MovieSubmit {

	//Template path of main picture.
	private String mainPicture;
	
	//Movie's name.
	private String name;
	
	//Array of movie types. 
	private int[] typesArray;
	
	private String abstract_;
	
	//Description of movies.
	private String description;

	public String getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getTypesArray() {
		return typesArray;
	}

	public void setTypesArray(int[] typesArray) {
		this.typesArray = typesArray;
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

	@Override
	public String toString() {
		return "MovieSubmit [mainPicture=" + mainPicture + ", name=" + name + ", typesArray="
				+ Arrays.toString(typesArray) + ", abstract_=" + abstract_ + ", description=" + description + "]";
	}

	
}
