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
public class Role {
    //角色标识
    private Integer roleId;
    //角色名称
    private String name;

}
