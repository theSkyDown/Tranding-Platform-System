/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author eccentric
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户添加功能
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addUser(@RequestBody User user) throws Exception {
        System.out.println(user);
        //判断是否有权限操作

        //拦截所有参数都为空的情况
        if (!isOk(user)){
            return Ret.fail("参数存在问题");
        }
        if (user.getUsername()==null|| user.getPhone() == null|| user.getPassword()==null){
            return Ret.fail("参数存在问题");
        }
        //添加用户
        return userService.addUser(user);
    }


    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete/{userId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteUser(@PathVariable Integer userId){
        System.out.println("userId:" + userId);
        //判断是否有权限

        //拦截参数为空的情况
        if (userId == null){
            return Ret.fail("参数存在问题");
        }
        //执行删除操作
        return userService.deleteUser(userId);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updateUser(@RequestBody User user){
        System.out.println(user);
        //判断是否有权限

        //拦截所有参数都为空的状态
        if (!isOk(user)){
            return Ret.fail("参数存在错误");
        }
        if (user.getUserId()==null){
            return Ret.fail("参数存在错误");
        }
        //更新用户信息
        return userService.updateUser(user);
    }




}
