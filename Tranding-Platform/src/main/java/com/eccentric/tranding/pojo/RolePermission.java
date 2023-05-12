/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolePermission {
    //标识
    private Integer id;
    //角色标识
    private Integer roleId;
    //权限标识
    private Integer permissionId;

}
