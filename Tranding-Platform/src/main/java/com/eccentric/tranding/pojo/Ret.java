/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户前后端发请求，统一返回数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ret {
    //状态
    private Boolean status;
    //消息
    private String message;
    //数据
    private Object data;

}
