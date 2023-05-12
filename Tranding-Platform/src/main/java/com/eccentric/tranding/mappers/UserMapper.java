package com.eccentric.tranding.mappers;


import com.eccentric.tranding.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
