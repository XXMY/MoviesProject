package com.cfw.movies.commons.model;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月4日 下午10:02:33
 */
public class Types {

	private int id;
	private String type_name;
	
	public Types() {
		super();
	}

	public Types(String type_name){
		this.type_name = type_name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	@Override
	public String toString() {
		return "Types [id=" + id + ", type_name=" + type_name + "]";
	}
	
	
}
