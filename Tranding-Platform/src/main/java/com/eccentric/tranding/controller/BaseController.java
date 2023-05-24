package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;

import java.lang.reflect.Field;

/**
 * 基础Controller类
 * @author eccentric
 */
public abstract class BaseController {
    /**
     * 用于判断double的值是否合理
     * @param value
     * @return
     */
    public Boolean isOk(Double value){
        if (value == null || value <= 0){
            return false;
        }
        return true;
    }

    /**
     * 用于判断批量删除的参数是否合理
     * @param ids
     * @return
     */
    public Boolean isIdsOk(String ids){
        //判断参数是否正常
        String[] idArray = ids.split(",");
        for (String id : idArray){
            if (!id.matches("\\d+")){
                return false;
            }
        }
        //防止最后一个为逗号
        if (ids.lastIndexOf(",") == ids.length()-1){
            return false;
        }
        return true;
    }

    /**
     * 检测id类型的数据是否合理
     * @param id
     * @return
     */
    public static Boolean isOk(Integer id){
        if (id == null || id <= 0){
            return false;
        }
        return true;
    }


    /**
     * 检测字符串类型的数据是否合理
     * @param str
     * @return
     */
    public static Boolean isOk(String str){
        if (str == null || "".equals(str.trim())){
            return false;
        }
        return true;
    }


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

