package com.eccentric.tranding.config;

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
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor());
        registry.addInterceptor(new PermissionInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
