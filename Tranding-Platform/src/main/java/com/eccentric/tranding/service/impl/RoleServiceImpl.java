package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.RoleMapper;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Role> getAllRole(Integer num, Integer size,String keyword) {
        return roleMapper.getAllRolePage(num, size, keyword);
    }

    @Override
    public Boolean isExist(Role role) {
        if (role == null){
            return false;
        }
        //通过角色表示判断角色是否存在
        Integer roleId = role.getRoleId();
        if (roleMapper.getRoleById(roleId) != null){
            return true;
        }
        //通过角色名称判断角色是否存在
        String roleName = role.getRoleName();
        if(roleMapper.getRoleByName(roleName) != null){
            return true;
        }
        return false;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public Ret addRole(Role role) {
        if (isExist(role)){
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
        if (roleId == 1 || roleId == 2 || roleId ==3){
            return Ret.fail("基础角色无法被删除");
        }

        Role temp = new Role();
        temp.setRoleId(roleId);
        if (!isExist(temp)){
            return Ret.fail("角色不存在");
        }
        //执行删除操作
        Integer count = roleMapper.deleteById(roleId);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret deleteByIds(List<Integer> idList) {
        if (idList.contains(1) || idList.contains(2) || idList.contains(3)){
            return Ret.fail("基础角色无法被删除");
        }
        Integer count = roleMapper.deleteByBatchIds(idList);
        return count > 0 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret updateRole(Role role) {
        if (!isExist(role)){
            return Ret.fail("角色不存在，修改失败");
        }
        if (role.getRoleId()==1||role.getRoleId()==2||role.getRoleId()==3){
            return Ret.fail("基础角色无法被修改");
        }
        //防止修改的角色名称已经被使用
        Role roleByName = roleMapper.getRoleByName(role.getRoleName());
        if (roleByName != null && !role.getRoleId().equals(roleByName.getRoleId())){
            return Ret.fail("角色修改失败，角色名称以被使用");
        }

        Integer count = roleMapper.updateRole(role);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret getTotalRole() {
        return Ret.ok(null,roleMapper.getTotalRole());
    }
}
