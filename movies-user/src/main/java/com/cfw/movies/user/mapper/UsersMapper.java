package com.cfw.movies.user.mapper;

import com.cfw.movies.commons.model.User;
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
public interface UsersMapper extends BaseMapper<User> {

    /**
     * @author CaiFangwei
     * @since 2017-9-3 16:25:06
     * @param username
     * @return
     */
    @Select("SELECT COUNT(id) FROM users WHERE username = #{username}")
	int selectUserNumber(@Param("username") String username);
	
	/**
	 * @author Fangwei_Cai
	 * @since 2016年5月1日 下午2:17:30
	 * @param username
	 * @return
	 */
	@Select("SELECT id,user_key as userKey, username, head_pic, type FROM users WHERE username = #{username} limit 1")
    User selectUserByName(@Param("username") String username);

	/**
	 * @param username
	 * @param password
	 * @return
	 * @author CaiFangwei
	 * @since 2017-3-12 18:43:55
	 */
	@Select("SELECT username, head_pic, type FROM users WHERE username = #{username} AND password = #{password} limit 1")
    User selectUserInBrief(@Param("username") String username, @Param("password") String password);
}
