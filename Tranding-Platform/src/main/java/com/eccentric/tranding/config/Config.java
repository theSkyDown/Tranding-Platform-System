package com.eccentric.tranding.config;

import com.eccentric.tranding.interceptor.CookieInterceptor;
import com.eccentric.tranding.interceptor.CorsInterceptor;
import com.eccentric.tranding.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author eccentric
 */
@Configuration
public class Config implements WebMvcConfigurer {

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Autowired
    private CookieInterceptor cookieInterceptor;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //跨域拦截器
        registry.addInterceptor(corsInterceptor);
        //非法用户拦截器
        registry.addInterceptor(cookieInterceptor);
        //权限拦截器(登陆，验证码请求不拦截)
        registry.addInterceptor(permissionInterceptor)
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/captcha/img")
                .excludePathPatterns("/qiniu/avatar");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
