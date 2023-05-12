package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 角色表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    //角色标识
    private Integer roleId;
    //角色名称
    private String roleName;
    //创建时间
    private LocalDateTime createTime;

}
