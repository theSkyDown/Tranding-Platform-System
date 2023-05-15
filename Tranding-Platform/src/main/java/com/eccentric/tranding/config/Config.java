package com.eccentric.tranding.config;

import com.eccentric.tranding.interceptor.CookieInterceptor;
import com.eccentric.tranding.interceptor.CorsInterceptor;
import com.eccentric.tranding.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author eccentric
 */
@Configuration
public class Config implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor());
        registry.addInterceptor(new CookieInterceptor());
        registry.addInterceptor(new PermissionInterceptor()).excludePathPatterns("/test");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
