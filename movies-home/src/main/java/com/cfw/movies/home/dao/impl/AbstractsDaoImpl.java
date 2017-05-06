package com.cfw.movies.home.dao.impl;

import com.cfw.movies.commons.model.Description;
import com.cfw.movies.home.dao.AbstractsDao;
import com.cfw.movies.home.mapper.DescriptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午4:33:33
 */
@Repository("abstractsDaoImpl")
public class AbstractsDaoImpl implements AbstractsDao {

	@Autowired
	private DescriptionsMapper descriptionsMapper;
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:32:37
	 */
	@Override
	public int insertDescription(Description description) {
		int result = this.descriptionsMapper.insertOne(description);
		
		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午4:43:05
	 */
	@Override
	public int updateOne(Description descritpion) {
		int result = this.descriptionsMapper.updateOne(descritpion);
		return result;
	}

}
