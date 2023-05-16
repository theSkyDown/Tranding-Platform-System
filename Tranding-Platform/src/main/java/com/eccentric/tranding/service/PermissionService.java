package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.User;

/**
 * 对权限管理提供服务
 * @author eccentric
 */
public interface PermissionService  {

    /**
     * 判断是否能访问资源
     * @param roleId
     * @param moduleId
     * @return
     */
    public Boolean accessibility(Integer roleId,Integer moduleId);
}
