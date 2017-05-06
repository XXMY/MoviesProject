package com.cfw.movies.home.dao;

import com.cfw.movies.commons.model.Type;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月2日 下午5:50:04
 */
public interface TypesDao {
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月2日 下午5:50:04
	 */
	List<Type> findAll();
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月11日 上午11:45:24
	 */
	int insertType(Type type);
}
