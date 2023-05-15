package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.dictionary.Encrypt;
import com.eccentric.tranding.dictionary.Gender;
import com.eccentric.tranding.dictionary.Identity;
import com.eccentric.tranding.dictionary.Status;
import com.eccentric.tranding.mappers.UserMapper;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.service.UserService;
import com.eccentric.tranding.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 对用户操作提供服务
 * @author eccentric
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean isExist(User user) {
        //参数直接为空直接返回false
        if (user == null){
            return false;
        }
        //通过用户标识判断用户是否存在
        Integer userId = user.getUserId();
        if (userId!=null){
            User isExistUser = userMapper.getUserByUserId(userId);
            return isExistUser!=null;
        }

        //通过手机号标识判断用户是否存在
        String phone = user.getPhone();
        if (phone != null){
            User isExistUser = userMapper.getUserByPhone(phone);
            return isExistUser!=null;
        }
        return false;
    }

    @Override
    public void setDefault(User user) {
        //防止参数为空
        if (user == null){
            return;
        }
        //设置默认的性别
        if (user.getGender()==null){
            user.setGender(Gender.MALE);
        }
        //设置默认的角色
        if (user.getRoleId()==null){
            user.setRoleId(Identity.USER);
        }
        //设置默认的头像路径
        if (user.getAvatar()==null){
            user.setAvatar("/avatar/default");
        }
        //设置创建时间
        if (user.getCreateTime()==null){
            user.setCreateTime(LocalDateTime.now());
        }
        if (user.getUpdateTime()==null){
            user.setUpdateTime(LocalDateTime.now());
        }
        //设置默认的用户状态，启用
        if (user.getStatus()==null){
            user.setStatus(Status.ENABLE);
        }
    }

    @Override
    public Ret addUser(User user) throws Exception {
        //拦截用户已经存在
        if (isExist(user)) {
            return Ret.fail("用户已经存在");
        }
        //拦截手机格式错误的

        //设置默认参数
        setDefault(user);

        //对密码进行加密
        String password = Md5Util.getMD5(user.getPassword() + Encrypt.MD5_HELPER);
        user.setPassword(password);

        //进行添加
        boolean result = userMapper.insertUser(user)==1;

        return result?Ret.ok():Ret.fail();
    }



}
