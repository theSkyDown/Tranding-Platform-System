package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 资源表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Module {
    //资源标识
    private Integer moduleId;
    //功能名称
    private String moduleName;
    //接口路径
    private String url;
    //是否为菜单
    private Integer isMenu;
}
