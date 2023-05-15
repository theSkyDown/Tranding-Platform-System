package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.User;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * 基础Controller类
 * @author eccentric
 */
public abstract class BaseController {


    /**
     * 用于检测参数是否合理的
     * @param user
     * @return
     */
    public static Boolean isOk(User user){
        if (user == null){
            return false;
        }
        Field[] fields = user.getClass().getDeclaredFields();

        //遍历所有属性，如果存在不为空的值则参数没有问题，如果属性全为空则参数存在问题
        Boolean result = false;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(user) != null) {
                    result = true;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

