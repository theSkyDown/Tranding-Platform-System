package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.PermissionMapper;
import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.Permission;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.ModuleService;
import com.eccentric.tranding.service.PermissionService;
import com.eccentric.tranding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eccentric
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;


    @Override
    public Boolean isExist(Integer permissionId) {
        if (permissionId == null){
            return false;
        }
        return permissionMapper.getPermissionById(permissionId)!=null;
    }

    @Override
    public Boolean accessibility(Integer roleId, Integer moduleId) {
        return permissionMapper.getPermission(roleId, moduleId)!=null;
    }

    @Override
    public List<Permission> getAllPermission(Integer num, Integer size) {
        List<Permission> allPermission = permissionMapper.getAllPermission(num, size);
        allPermission.forEach(permission -> {
            //设置角色名称
            Role role = roleService.getRoleById(permission.getRoleId());
            if (role != null){
                permission.setRoleName(role.getRoleName());
            }else{
                permission.setRoleName("角色异常");
            }


            //设置资源名称
            Module module = moduleService.getModuleById(permission.getModuleId());
            if (module != null){
                permission.setModuleName(module.getModuleName());
            }else{
                permission.setModuleName("资源异常");
            }

        });
        return allPermission;
    }

    @Override
    public Ret getTotalPermission() {
        return Ret.ok(null,permissionMapper.getTotalPermission());
    }

    @Override
    public Ret addPermission(Permission permission) {
        //如果权限已经存在
        if (accessibility(permission.getRoleId(),permission.getModuleId())) {
            return Ret.fail("权限已存在");
        }
        //执行添加操作
        Integer count = permissionMapper.addPermission(permission);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deletePermission(Integer permissionId) {
        if (!isExist(permissionId)){
            return Ret.fail("权限不存在");
        }
        Integer count = permissionMapper.deletePermission(permissionId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByIds(List<Integer> idList) {
        Integer count = permissionMapper.deleteByIds(idList);
        return count>0 ? Ret.ok() : Ret.fail();
    }
}
