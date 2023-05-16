/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.controller;

import com.eccentric.tranding.dictionary.Encrypt;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.service.impl.UserServiceImpl;
import com.eccentric.tranding.utils.Md5Util;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @author eccentric
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取所有用户信息（包含分页功能）
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllUser(@RequestParam("num") Integer num,@RequestParam("size") Integer size){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return userService.getAllUser(num,size);
    }

    /**
     * 用户添加功能
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addUser(@RequestBody User user) throws Exception {
        //拦截所有参数都为空的情况
        if (!isOk(user)){
            return Ret.fail("参数异常");
        }
        if (user.getUsername()==null|| user.getPhone() == null|| user.getPassword()==null){
            return Ret.fail("参数异常");
        }
        //添加用户
        return userService.addUser(user);
    }


    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteUser(@RequestParam("userId") Integer userId){
        //拦截参数为空的情况
        if (!isOk(userId)){
            return Ret.fail("参数异常");
        }
        //执行删除操作
        return userService.deleteUser(userId);
    }


    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/ids",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteByBatchId(@RequestParam("ids") String ids){
        List<Integer> idList = new ArrayList<>();
        //判断参数是否正常
        String[] idArray = ids.split(",");
        for (String id : idArray){
            if (!id.matches("\\d+")){
                return Ret.fail("参数异常");
            }
            idList.add(Integer.valueOf(id));
        }
        //防止最后一个为逗号
        if (ids.lastIndexOf(",") == ids.length()-1){
            return Ret.fail("参数异常");
        }
        return userService.deleteByIds(idList);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updateUser(@RequestBody User user){
        //判断是否有权限

        //拦截所有参数都为空的状态
        if (!isOk(user)){
            return Ret.fail("参数异常");
        }
        if (user.getUserId()==null){
            return Ret.fail("参数异常");
        }
        //更新用户信息
        return userService.updateUser(user);
    }


    /**
     * 用户登陆功能
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Ret login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //拦截参数异常的情况
        if (!isOk(user)) {
            return Ret.fail("参数异常");
        }
        if (user.getPhone()==null || user.getPassword()==null){
            return Ret.fail("参数异常");
        }

        //拦截验证码错误的
        if (user.getCaptcha()==null || !CaptchaUtil.ver(user.getCaptcha(),request)){
            return Ret.fail("验证码错误");
        }

        //如果登陆成功则写入cookie
        Ret ret = userService.login(user);
        if (ret.getStatus()){
            //登陆成功的用户
            User tempUser = (User) ret.getData();
            //设置cookie的值
            StringBuilder cookieValue = new StringBuilder(Md5Util.getMD5(tempUser.getPhone() + Encrypt.MD5_HELPER) );
            cookieValue.append("#");
            cookieValue.append(tempUser.getPhone());
            // 写cookie
            ResponseCookie cookie = ResponseCookie.from("user",cookieValue.toString())
                    .sameSite("None")
                    .secure(true)
                    .httpOnly(false)
                    .domain("localhost")
                    .path("/")
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        }

        return ret;
    }


    /**
     * 密码重置功能
     * @param userId
     * @return
     */
    @RequestMapping(value = "/reset/password",method = RequestMethod.PUT)
    @ResponseBody
    public Ret resetPassword(@RequestParam("userId") Integer userId) throws Exception {
        if (!isOk(userId)){
            return Ret.fail("参数异常");
        }
        return userService.resetPassword(userId);
    }


    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Ret getUserInfo(@RequestParam("userId") Integer userId){
        User user = userService.getUserById(userId);
        if (user!=null){
            return Ret.ok(null,user);
        }
        return Ret.fail();
    }


    /**
     * 用户修改密码功能
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update/password",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updatePassword(@RequestBody User user) throws Exception {
        if (!isOk(user)){
            return Ret.fail("参数异常");
        }
        if (user.getUserId()==null || user.getPassword()==null){
            return Ret.fail("参数异常");
        }
        return userService.updatePassword(user.getUserId(),user.getPassword());
    }
}
