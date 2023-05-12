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
public class Good {
    //商品标识
    private Integer goodId;
    //商品名称
    private String name;
    //商品价格
    private Double price;
    //发布用户的标识
    private Integer userId;
    //商品描述
    private String description;
    //商品分类
    private String classify;
    //商品图片
    private String goodImg;
}
