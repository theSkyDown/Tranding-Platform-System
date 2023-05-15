/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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
    @RequestMapping("/add")
    @ResponseBody
    public Ret addUser(@RequestBody User user) throws Exception {
        System.out.println(user);
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




}
