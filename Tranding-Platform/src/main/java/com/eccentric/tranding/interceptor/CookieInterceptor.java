package com.eccentric.tranding.interceptor;

import com.eccentric.tranding.dictionary.Encrypt;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.UserService;
import com.eccentric.tranding.utils.Md5Util;
import com.eccentric.tranding.utils.UserHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 非法用户拦截器
 * @author eccentric
 */
@Component
public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截非法用户
        Cookie[] cookies = request.getCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        if (cookies==null){
            return true;
        }
        for (Cookie cookie : cookies){
            if ("user".equals(cookie.getName())){
                String cookieInfo = cookie.getValue();

                //非法用户
                if (cookieInfo == null || !cookieInfo.matches("\\w+\\#\\w+")){
                    response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("非法用户")));
                    return false;
                }
                String md5 = cookieInfo.split("#")[0];
                String phone = cookieInfo.split("#")[1];
                //非法用户
                if (!md5.equals(Md5Util.getMD5(phone+ Encrypt.MD5_HELPER))){
                    response.getWriter().write(objectMapper.writeValueAsString(Ret.fail("非法用户")));
                    return false;
                }
            }
        }
        return true;
    }
}
