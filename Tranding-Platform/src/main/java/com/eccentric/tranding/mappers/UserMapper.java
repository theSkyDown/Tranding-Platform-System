package com.eccentric.tranding.mappers;


import com.eccentric.tranding.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author eccentric
 */
@Mapper
public interface UserMapper {

    /**
     * 通过标识获取用户信息
     * @param id
     * @return
     */
    @Select("select * from user where user_id = #{id}")
    public User getUserByUserId(@Param("id") Integer id);

    /**
     * 通过手机号获取用户信息
     * @param phone
     * @return
     */
    @Select("select * from user where phone = #{phone}")
    public User getUserByPhone(@Param("phone") String phone);


    /**
     * 插入用户数据
     * @param user
     * @return
     */
    public Integer insertUser(User user);


    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Delete("delete from user where user_id=#{userId}")
    public Integer deleteUser(@Param("userId") Integer userId);


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Integer updateUser(User user);
}
