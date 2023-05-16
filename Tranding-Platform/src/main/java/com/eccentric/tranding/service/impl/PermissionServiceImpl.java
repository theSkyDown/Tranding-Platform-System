package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.PermissionMapper;
import com.eccentric.tranding.pojo.Permission;
import com.eccentric.tranding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author eccentric
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Boolean accessibility(Integer roleId, Integer moduleId) {
        return permissionMapper.getPermission(roleId, moduleId)!=null;
    }
}
