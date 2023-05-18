package com.eccentric.tranding.mappers;


import com.eccentric.tranding.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
     * 批量删除
     * @param idList
     * @return
     */
    public Integer deleteByIds(@Param("idList") List<Integer> idList);


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Integer updateUser(User user);


    /**
     * 修改用户的密码
     * @param userId
     * @param password
     * @return
     */
    @Update("update user set password=#{password} where user_id=#{userId}")
    public Integer updatePassword(@Param("userId") Integer userId,@Param("password") String password);


    /**
     * 分页查询所有用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<User> getAllUserPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("keyword") String keyword);


    /**
     * 修改用户的状态
     * @param userId
     * @return
     */
    @Update("update user set status = #{status} where user_id = #{userId}")
    public Integer updateUserStatus(@Param("userId") Integer userId,@Param("status") Integer status);

    /**
     * 统计用户数量
     * @return
     */
    @Select("select count(*) from user")
    public Integer getTotalUser();
}
