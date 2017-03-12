package com.cfw.movies.login.mapper;

import com.cfw.movies.commons.mapper.BaseMapper;
import com.cfw.movies.commons.model.Users;
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
	 * @param userName
	 * @return
	 */
	@Select("SELECT username, head_pic, type FROM users WHERE username = #{userName} limit 1")
	Users selectUserInBrief(@Param("userName") String userName);

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @author CaiFangwei
	 * @time since 2017-3-12 18:43:55
	 */
	@Select("SELECT username, head_pic, type FROM users WHERE username = #{userName} AND password = #{password} limit 1")
	Users selectUserInBrief(@Param("userName") String userName, @Param("password") String password);
}
