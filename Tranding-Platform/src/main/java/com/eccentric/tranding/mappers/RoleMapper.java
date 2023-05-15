package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author eccentric
 */
@Mapper
public interface RoleMapper {

    /**
     * 获取所有角色的信息(分页)
     * @param num
     * @param size
     * @return
     */
    @Select("select * from role limit #{num},#{size}")
    public List<Role> getAllRolePage(@Param("num") Integer num,@Param("size") Integer size);

    /**
     * 通过id获取角色信息
     * @param roleId
     * @return
     */
    @Select("select * from role where role_id = #{roleId}")
    public Role getRoleById(@Param("roleId") Integer roleId);


    @Insert("insert into role values(#{roleId},#{roleName},#{createTime})")
    public Integer addRole(Role role);
}
