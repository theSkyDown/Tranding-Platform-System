package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author eccentric
 */
@Mapper
public interface PermissionMapper {


    /**
     * 通过id获取权限
     * @param permissionId
     * @return
     */
    @Select("select * from permission where permission_id = #{permissionId}")
    public Permission getPermissionById(@Param("permissionId") Integer permissionId);

    /**
     * 通过角色id和资源id获取权限
     * @param roleId
     * @param moduleId
     * @return
     */
    @Select("select * from permission where role_id=#{roleId} and module_id=#{moduleId}")
    public Permission getPermission(@Param("roleId") Integer roleId,@Param("moduleId") Integer moduleId);


    /**
     * 获取所有权限信息
     * @param num
     * @param size
     * @return
     */
    public List<Permission> getAllPermission(@Param("num") Integer num,@Param("size") Integer size);


    /**
     * 统计权限数据
     * @return
     */
    @Select("select count(*) from permission")
    public Integer getTotalPermission();


    /**
     * 添加权限
     * @param permission
     * @return
     */
    @Insert("insert into permission values(#{permissionId},#{roleId},#{moduleId})")
    public Integer addPermission(Permission permission);


    /**
     * 删除权限
     * @param permissionId
     * @return
     */
    @Delete("delete from permission where permission_id = #{permissionId}")
    public Integer deletePermission(@Param("permissionId") Integer permissionId);


    /**
     * 批量删除权限
     * @param idList
     * @return
     */
    public Integer deleteByIds(@Param("idList") List<Integer> idList);
}
