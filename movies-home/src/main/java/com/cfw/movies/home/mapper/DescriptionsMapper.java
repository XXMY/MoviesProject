package com.cfw.movies.home.mapper;

import com.cfw.movies.commons.model.Descriptions;
import com.cfw.plugins.database.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午3:12:44
 */
@Repository("descriptionsMapper")
@Mapper
public interface DescriptionsMapper extends BaseMapper<Descriptions> {
	
}
