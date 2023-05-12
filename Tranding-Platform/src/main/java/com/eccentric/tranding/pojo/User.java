/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    //用户标识
    private Integer userId;
    //用户名
    private String username;
    //用户手机号
    private String phone;
    // 用户密码
    private String password;
    // 用户性别
    private Integer gender;
    // 角色标识
    private Integer roleId;
    // 头像
    private String avatar;
    // 用户创建时间
    private LocalDateTime createTime;
    //用户状态
    private Integer status;
}
