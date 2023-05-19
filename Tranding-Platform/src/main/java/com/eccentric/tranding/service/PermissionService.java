package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.Permission;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;

import java.util.List;

/**
 * 对权限管理提供服务
 * @author eccentric
 */
public interface PermissionService  {


    /**
     * 权限是否存在
     * @param permissionId
     * @return
     */
    public Boolean isExist(Integer permissionId);


    /**
     * 判断是否能访问资源
     * @param roleId
     * @param moduleId
     * @return
     */
    public Boolean accessibility(Integer roleId,Integer moduleId);


    /**
     * 获取所有权限信息
     * @param num
     * @param size
     * @return
     */
    public List<Permission> getAllPermission(Integer num,Integer size);


    /**
     * 统计权限数据的数量
     * @return
     */
    public Ret getTotalPermission();


    /**
     * 添加权限
     * @param permission
     * @return
     */
    public Ret addPermission(Permission permission,User actionUser);


    /**
     * 删除权限
     * @param permissionId
     * @return
     */
    public Ret deletePermission(Integer permissionId,User actionUser);


    /**
     * 批量删除权限
     * @param idList
     * @return
     */
    public Ret deleteByIds(List<Integer> idList,User actionUser);
}
