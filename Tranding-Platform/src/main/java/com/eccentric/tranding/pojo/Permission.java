package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 权限表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {
    //权限标识
    private Integer permissionId;
    //角色标识
    private Integer roleId;
    //资源标识
    private Integer moduleId;

    //角色名称
    private String roleName;
    //资源名称
    private String moduleName;

}
