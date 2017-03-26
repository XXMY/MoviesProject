package com.cfw.movies.register.mapper;

import com.cfw.movies.commons.mapper.BaseMapper;
import com.cfw.movies.commons.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月14日 下午2:22:38
 */
@Repository("usersMapper")
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月1日 下午2:17:30
	 * @param username
	 * @return
	 */
	Users selectUserByName(String username);
}
