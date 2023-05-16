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
import java.util.ArrayList;
import java.util.List;

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
        if (userMapper.getUserByUserId(userId)!=null){
            return true;
        }

        //通过手机号标识判断用户是否存在
        String phone = user.getPhone();
        if (userMapper.getUserByPhone(phone) != null){
            return true;
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
            return Ret.fail("用户已存在");
        }
        //拦截手机格式错误的
        String phone = user.getPhone();
        if (!phone.matches("^1[3-9]\\d{9}")){
            return Ret.fail("手机格式存在错误");
        }

        //设置默认参数
        setDefault(user);

        //对密码进行加密
        String password = Md5Util.getMD5(user.getPassword() + Encrypt.MD5_HELPER);
        user.setPassword(password);

        //进行添加
        boolean result = userMapper.insertUser(user)==1;

        return result?Ret.ok():Ret.fail();
    }


    @Override
    public Ret deleteUser(Integer userId) {
        //判断用户是否存在
        User tempUser = new User();
        tempUser.setUserId(userId);
        if (!isExist(tempUser)){
            return Ret.fail("用户不存在");
        }
        //执行删除操作
        boolean result =  userMapper.deleteUser(userId) == 1;
        return result?Ret.ok():Ret.fail();
    }


    @Override
    public Ret deleteByIds(List<Integer> idList) {
        //批量删除
        Integer count = userMapper.deleteByIds(idList);
        return count>0 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret updateUser(User user) {
        //拦截不存在的用户
        if (!isExist(user)){
            return Ret.fail("用户不存在，修改失败");
        }

        //防止修改的手机号已经被使用
        User tempUser = userMapper.getUserByPhone(user.getPhone());
        if (tempUser!=null && !user.getUserId().equals(tempUser.getUserId())){
            return Ret.fail("手机号修改失败，手机号已被使用");
        }
        //拦截手机格式错误的
        String phone = user.getPhone();
        if (!phone.matches("^1[3-9]\\d{9}")){
            return Ret.fail("手机格式存在错误");
        }


        //设置默认值，防止参数为空
        setDefault(user);

        //执行修改操作
        boolean result = userMapper.updateUser(user)==1;
        return result?Ret.ok():Ret.fail();
    }


    @Override
    public Ret login(User user) throws Exception {
        User userByPhone = userMapper.getUserByPhone(user.getPhone());
        if (userByPhone == null){
            return Ret.fail("用户不存在");
        }

        String md5 = Md5Util.getMD5(user.getPassword() + Encrypt.MD5_HELPER);
        if (!md5.equals(userByPhone.getPassword())){
            return Ret.fail("用户名或密码错误");
        }
        //密码不传回去
        userByPhone.setPassword(null);
        return Ret.ok(null,userByPhone);
    }


    @Override
    public Ret resetPassword(Integer userId) throws Exception {
        User temp = new User();
        temp.setUserId(userId);
        if (!isExist(temp)){
            return Ret.fail("用户不存在");
        }
        //生成密码
        String pwd = Md5Util.getMD5(Encrypt.PWD_RESET + Encrypt.MD5_HELPER);
        Integer count = userMapper.updatePassword(userId, pwd);
        return count==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public User getUserById(Integer userId) {
        User user = userMapper.getUserByUserId(userId);
        if (user != null){
            user.setPassword(null);
        }
        return user;
    }


    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public Ret updatePassword(Integer userId, String password) throws Exception {
        User temp = new User();
        temp.setUserId(userId);
        if (!isExist(temp)){
            return Ret.fail("用户不存在");
        }
        //对密码加密
        String md5 = Md5Util.getMD5(password + Encrypt.MD5_HELPER);

        Integer count = userMapper.updatePassword(userId, md5);
        return count==1 ? Ret.ok():Ret.fail();
    }

    @Override
    public Ret getAllUser(Integer pageNum, Integer pageSize) {
        List<User> userList = userMapper.getAllUserPage(pageNum, pageSize);
        userList.forEach(user -> {user.setPassword(null);});
        return Ret.ok(null,userList);
    }




}
