package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;

import java.util.List;

/**
 * 对角色管理提供服务
 * @author eccentric
 */
public interface RoleService {

    /**
     * 获取所有角色信息(分页)
     * @param num
     * @param size
     * @return
     */
    public List<Role> getAllRole(Integer num,Integer size);


    /**
     * 判断角色是否已经存在
     * @param roleId
     * @return
     */
    public Boolean isExist(Integer roleId);

    /**
     * 通过id查找角色
     * @param id
     * @return
     */
    public Role getRoleById(Integer id);

    /**
     * 添加角色
     * @param role
     * @return
     */
    public Ret addRole(Role role);


    /**
     * 通过id将角色删除
     * @param roleId
     * @return
     */
    public Ret deleteRole(Integer roleId);


    /**
     * 批量删除角色
     * @param idList
     * @return
     */
    public Ret deleteByIds(List<Integer> idList);
}
