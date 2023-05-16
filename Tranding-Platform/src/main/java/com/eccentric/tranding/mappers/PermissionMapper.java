package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author eccentric
 */
@Mapper
public interface PermissionMapper {

    /**
     * 通过角色id和资源id获取权限
     * @param roleId
     * @param moduleId
     * @return
     */
    @Select("select * from permission where role_id=#{roleId} and module_id=#{moduleId}")
    public Permission getPermission(@Param("roleId") Integer roleId,@Param("moduleId") Integer moduleId);
}
