package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.RoleMapper;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.User;
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
        //设置默认的权限等级(level)
        role.setLevel(3);
        //执行添加操作
        Integer count = roleMapper.insertRole(role);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret deleteRole(Integer roleId, User actionUser) {
        if (roleId == 1 || roleId == 2 || roleId ==3){
            return Ret.fail("基础角色无法被删除");
        }

        Role deleteRole = roleMapper.getRoleById(roleId);
        if (deleteRole == null){
            return Ret.fail("角色不存在");
        }

        //判断是否有权限删除这个角色
        Role actionRole = roleMapper.getRoleById(actionUser.getRoleId());
        if (actionRole == null){
            return Ret.fail("你的角色信息异常");
        }
        if (actionUser.getUserId() != 1 && actionRole.getLevel() >= deleteRole.getLevel()){
            return Ret.fail("你没有权限删除该角色");
        }

        //执行删除操作
        Integer count = roleMapper.deleteById(roleId);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret deleteByIds(List<Integer> idList,User actionUser) {
        if (idList.contains(1) || idList.contains(2) || idList.contains(3)){
            return Ret.fail("基础角色无法被删除");
        }

        //循环判断是否有权限删除这些角色
        for (Integer id : idList){
            Role actionRole = roleMapper.getRoleById(actionUser.getRoleId());
            Role deleteRole = roleMapper.getRoleById(id);
            if (actionRole == null){
                return Ret.fail("你的角色信息异常");
            }
            if (deleteRole!=null
                    && actionUser.getUserId() != 1
                    && actionRole.getLevel() >= deleteRole.getLevel()){
                return Ret.fail("你没有权限删除 '" + deleteRole.getRoleName() + "' 这个角色");
            }
        }


        Integer count = roleMapper.deleteByBatchIds(idList);
        return count > 0 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret updateRole(Role role,User actionUser) {
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

        //判断是否有权限修改这个角色
        Role actionRole = roleMapper.getRoleById(actionUser.getRoleId());
        Role updateRole = roleMapper.getRoleById(role.getRoleId());
        if (actionRole == null){
            return Ret.fail("你的角色信息异常");
        }
        if (updateRole!=null && actionUser.getUserId() != 1){
            if (actionRole.getLevel() >= updateRole.getLevel()){
                return Ret.fail("你没有权限修改该角色");
            }
            //防止操作者将角色设置成权限等级比自己高的角色
            if (actionRole.getLevel() >= role.getLevel()){
                return Ret.fail("你没有权限将角色等级设置为 '" + role.getLevel() + "'");
            }
        }

        Integer count = roleMapper.updateRole(role);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret getTotalRole(String keyword) {
        return Ret.ok(null,roleMapper.getTotalRole(keyword));
    }
}
