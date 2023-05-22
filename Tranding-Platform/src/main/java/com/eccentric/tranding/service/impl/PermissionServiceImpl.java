package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.ModuleMapper;
import com.eccentric.tranding.mappers.PermissionMapper;
import com.eccentric.tranding.mappers.RoleMapper;
import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.Permission;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.ModuleService;
import com.eccentric.tranding.service.PermissionService;
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
    private RoleMapper roleMapper;

    @Autowired
    private ModuleMapper moduleMapper;


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
            Role role = roleMapper.getRoleById(permission.getRoleId());
            if (role != null){
                permission.setRoleName(role.getRoleName());
            }else{
                permission.setRoleName("角色异常");
            }


            //设置资源名称
            Module module = moduleMapper.getModuleById(permission.getModuleId());
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
    public Ret addPermission(Permission permission,User actionUser) {
        //如果权限已经存在
        if (accessibility(permission.getRoleId(),permission.getModuleId())) {
            return Ret.fail("权限已存在");
        }

        //判断是否有权限为该角色分配资源
        Role actionRole = roleMapper.getRoleById(actionUser.getRoleId());
        Role addRole = roleMapper.getRoleById(permission.getRoleId());
        if (actionRole == null){
            return Ret.fail("你的角色信息异常");
        }
        if (addRole!=null && actionUser.getUserId() != 1 && actionRole.getLevel() >= addRole.getLevel()){
            return Ret.fail("你没有权限为该角色分配资源");
        }

        //执行添加操作
        Integer count = permissionMapper.addPermission(permission);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deletePermission(Integer permissionId,User actionUser) {
        if (!isExist(permissionId)){
            return Ret.fail("权限不存在");
        }

        //获取删除的权限
        Permission deletePermission = permissionMapper.getPermissionById(permissionId);
        //判断是否有权限为该角色分配资源
        Role actionRole = roleMapper.getRoleById(actionUser.getRoleId());
        Role deleteRole = roleMapper.getRoleById(deletePermission.getRoleId());
        if (actionRole == null){
            return Ret.fail("你的角色信息异常");
        }
        if (deleteRole!=null && actionUser.getUserId() != 1 && actionRole.getLevel() >= deleteRole.getLevel()){
            return Ret.fail("你没有权限删除该角色的资源");
        }


        Integer count = permissionMapper.deletePermission(permissionId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByIds(List<Integer> idList, User actionUser) {
        //循环判断是否有权限删除这些'权限'
        for (Integer id : idList){
            Permission permission = permissionMapper.getPermissionById(id);
            if (permission != null){
                //判断操作用户的角色等级是否比要删除的用户的等级高
                Role actionRole = roleMapper.getRoleById(actionUser.getRoleId());
                Role deleteRole = roleMapper.getRoleById(permission.getRoleId());
                //防止操作用户角色异常
                if (actionRole == null){
                    return Ret.fail("你的角色信息异常");
                }
                //若删除人的  角色信息异常  或者  admin  可以直接操作
                if (deleteRole!=null
                        && actionUser.getUserId() != 1
                        && actionRole.getLevel() >= deleteRole.getLevel()){
                    return Ret.fail("你没有权限删除 '"+deleteRole.getRoleName()+"' 这个角色的资源");
                }
            }
        }

        Integer count = permissionMapper.deleteByIds(idList);
        return count>0 ? Ret.ok() : Ret.fail();
    }
}
