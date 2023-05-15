package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.pojo.User;

import java.util.List;

/**
 * 对用户操作提供服务
 * @author eccentric
 */
public interface UserService {

    /**
     * 用于设置一些默认参数
     * @param user
     */
    public void setDefault(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public Ret addUser(User user) throws Exception;

    /**
     * 用于判断用户是否存在
     * @param user
     * @return
     */
    public Boolean isExist(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public Ret deleteUser(Integer userId);


    /**
     * 批量删除用户
     * @param idList
     * @return
     */
    public Ret deleteByIds(List<Integer> idList);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Ret updateUser(User user);

    /**
     * 用户登陆
     * @param user
     * @return
     */
    public Ret login(User user) throws Exception;


    /**
     * 重置用户的密码
     * @param userId
     * @return
     */
    public Ret resetPassword(Integer userId) throws Exception;

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User getUserById(Integer userId);


    /**
     * 用户修改密码
     * @param userId
     * @param password
     * @return
     */
    public Ret updatePassword(Integer userId,String password) throws Exception;


    /**
     * 获取所有用户（分页）
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Ret getAllUser(Integer pageNum,Integer pageSize);
}
