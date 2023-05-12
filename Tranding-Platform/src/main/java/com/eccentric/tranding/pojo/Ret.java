package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 统一返回类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ret {
    //返回的状态，表示是否成功
    private Boolean status;
    //返回的信息
    private String message;
    //返回的数据
    private Object data;


    public static Ret ok(){
        return new Ret(true,"成功",null);
    }
    public static Ret ok(String message){
        return new Ret(true,message,null);
    }
    public static Ret ok(String message,Object data){
        return new Ret(true,message,data);
    }
    public static Ret fail(){
        return new Ret(false,"失败",null);
    }
    public static Ret fail(String message){
        return new Ret(false,message,null);
    }
    public static Ret fail(String message,Object data){
        return new Ret(false,message,data);
    }
}
