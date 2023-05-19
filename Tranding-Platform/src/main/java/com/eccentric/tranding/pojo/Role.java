package com.eccentric.tranding.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape= JsonFormat.Shape.STRING ,pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //权限等级
    private Integer level;

}
