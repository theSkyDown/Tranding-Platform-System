package com.eccentric.tranding.interceptor;


import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.ModuleService;
import com.eccentric.tranding.service.PermissionService;
import com.eccentric.tranding.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * @author eccentric
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private PermissionService permissionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看用户是否登陆
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                //已经登陆
                if ("user".equals(cookie.getName())){
                    //获取用户信息
                    String phone = cookie.getValue().split("#")[1];
                    User user = userService.getUserByPhone(phone);
                    if (user == null){
                        response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("用户信息错误")));
                        return false;
                    }
                    //如果是超级管理员则直接过
                    if (user.getRoleId()==1){
                        return true;
                    }
                    //拦截已经被禁用的用户
                    if (user.getStatus() == 2){
                        response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("用户已经被禁用")));
                        return false;
                    }

                    //获取资源信息
                    String url = request.getServletPath();
                    Module module = moduleService.getModuleByUrl(url);
                    //没有这个资源
                    if (module == null){
                        response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("没有该资源")));
                        return false;
                    }
                    //查询权限表，判断该用户是否有权限访问该资源
                    Boolean accessibility = permissionService.accessibility(user.getRoleId(), module.getModuleId());
                    if (!accessibility){
                        response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("你没有权限访问该资源")));
                    }
                    return accessibility;
                }
            }
        }
        response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("请登陆后在访问")));
        return false;
    }
}
