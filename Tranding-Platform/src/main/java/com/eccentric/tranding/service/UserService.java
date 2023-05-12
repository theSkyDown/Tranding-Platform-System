package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.Ret;
import com.eccentric.tranding.pojo.User;

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

}
