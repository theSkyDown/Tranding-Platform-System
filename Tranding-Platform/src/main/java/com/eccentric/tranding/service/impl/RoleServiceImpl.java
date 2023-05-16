package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.RoleMapper;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 对角色管理提供服务
 * @author eccentric
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getAllRole(Integer num, Integer size) {
        return roleMapper.getAllRolePage(num, size);
    }

    @Override
    public Boolean isExist(Integer roleId) {
        if (roleId == null || roleId <= 0){
            return false;
        }
        return roleMapper.getRoleById(roleId)!=null;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public Ret addRole(Role role) {
        if (isExist(role.getRoleId())){
            return Ret.fail("角色已存在");
        }
        //设置创建时间
        role.setCreateTime(LocalDateTime.now());

        //执行添加操作
        Integer count = roleMapper.insertRole(role);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret deleteRole(Integer roleId) {
        if (!isExist(roleId)){
            return Ret.fail("角色不存在");
        }
        //执行删除操作
        Integer count = roleMapper.deleteById(roleId);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret deleteByIds(List<Integer> idList) {
        Integer count = roleMapper.deleteByBatchIds(idList);
        return count > 0 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret updateRole(Role role) {
        if (!isExist(role.getRoleId())){
            return Ret.fail("角色不存在，修改失败");
        }
        Integer count = roleMapper.updateRole(role);
        return count==1 ? Ret.ok() : Ret.fail();
    }
}
