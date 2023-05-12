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
public class Permission {
    //权限标识
    private Integer permissionId;
    //功能名称
    private String name;
    //接口路由
    private String url;
    //是否是菜单
    private Integer isMenu;


}
