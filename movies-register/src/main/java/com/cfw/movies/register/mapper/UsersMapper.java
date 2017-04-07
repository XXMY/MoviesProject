package com.cfw.movies.register.mapper;

import com.cfw.movies.commons.model.Users;
import com.cfw.plugins.database.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
	@Select("SELECT id FROM users WHERE username = #{username}")
	Users selectUserByName(@Param("username") String username);
}
