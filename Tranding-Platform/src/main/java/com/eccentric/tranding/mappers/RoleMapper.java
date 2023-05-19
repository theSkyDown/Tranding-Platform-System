package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Role;
import org.apache.ibatis.annotations.*;

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
    public List<Role> getAllRolePage(@Param("num") Integer num,@Param("size") Integer size,@Param("keyword") String keyword);

    /**
     * 通过id获取角色信息
     * @param roleId
     * @return
     */
    @Select("select * from role where role_id = #{roleId}")
    public Role getRoleById(@Param("roleId") Integer roleId);


    /**
     * 添加角色
     * @param role
     * @return
     */
    @Insert("insert into role values(#{roleId},#{roleName},#{level},#{createTime})")
    public Integer insertRole(Role role);


    /**
     * 通过id删除角色
     * @param roleId
     * @return
     */
    @Delete("delete from role where role_id = #{roleId}")
    public Integer deleteById(@Param("roleId") Integer roleId);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    public Integer deleteByBatchIds(@Param("idList") List<Integer> idList);

    /**
     * 修改角色的信息
     * @param role
     * @return
     */
    @Update("update role set role_name=#{roleName},level=#{level} where role_id = #{roleId}")
    public Integer updateRole(Role role);


    /**
     * 统计角色数量
     * @return
     */
    public Integer getTotalRole(@Param("keyword") String keyword);


    /**
     * 通过角色名称获取角色
     * @param roleName
     * @return
     */
    @Select("select * from role where role_name = #{roleName}")
    public Role getRoleByName(@Param("roleName") String roleName);
}
