package com.eccentric.tranding.interceptor;

import com.eccentric.tranding.mappers.ModuleMapper;
import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录的拦截器
 * @author eccentric
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求到达控制器之前记录日志
        Module module = moduleMapper.getModuleByUrl(request.getServletPath());
        if (module != null){
            logger.info("{} 使用资源：{}，访问路径：{}",UserHolder.getUser().getUsername(),module.getModuleName(),request.getRequestURL());
        }else{
            logger.info("Request URL: {}", request.getRequestURL());
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理完成后记录日志
        logger.info("Response Status: {}", response.getStatus());
    }
}
