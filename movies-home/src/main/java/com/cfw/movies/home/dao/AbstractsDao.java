package com.cfw.movies.home.dao;

import com.cfw.movies.commons.model.Descriptions;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午4:19:07
 */
public interface AbstractsDao {

	/**
	 * @param abstracts {Abstracts}
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:19:41
	 */
	int insertDescription(Descriptions abstracts);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午4:42:53
	 * @param descritpion
	 * @return
	 */
	int updateOne(Descriptions descritpion);
	

}
