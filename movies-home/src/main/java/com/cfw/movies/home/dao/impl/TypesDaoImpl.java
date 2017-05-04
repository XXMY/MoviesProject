package com.cfw.movies.home.dao.impl;

import com.cfw.movies.commons.model.Types;
import com.cfw.movies.home.dao.TypesDao;
import com.cfw.movies.home.mapper.TypesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月2日 下午5:52:09
 */
@Repository("typesDaoImpl")
public class TypesDaoImpl implements TypesDao {
	
	@Autowired
	private TypesMapper typesMapper;
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月2日 下午5:52:09
	 */
	@Override
	public List<Types> findAll() {
		List<Types> result = typesMapper.selectAll();
		return result;
	}

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月11日 上午11:46:33
	 */
	@Override
	public int insertType(Types type) {
		int result = this.typesMapper.insertOne(type);
		
		return result;
	}

}
