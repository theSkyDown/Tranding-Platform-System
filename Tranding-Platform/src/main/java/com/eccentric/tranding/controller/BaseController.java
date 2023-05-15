package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.User;

import java.lang.reflect.Field;

/**
 * 基础Controller类
 * @author eccentric
 */
public abstract class BaseController {


    /**
     * 用于检测参数是否合理的
     * @param obj
     * @return
     */
    public static Boolean isOk(Object obj){
        if (obj == null){
            return false;
        }
        Field[] fields = obj.getClass().getDeclaredFields();

        //遍历所有属性，如果存在不为空的值则参数没有问题，如果属性全为空则参数存在问题
        Boolean result = false;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    result = true;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

