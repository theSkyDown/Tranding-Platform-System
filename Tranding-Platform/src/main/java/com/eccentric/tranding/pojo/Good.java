package com.eccentric.tranding.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 商品表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Good {
    //商品标识
    private Integer goodId;
    //商品名称
    private String goodName;
    //商品价格
    private Double price;
    //发布用户标识
    private Integer userId;
    //商品描述
    private String description;
    //商品图片
    private String goodImg;
    //商品分类标识
    private Integer categoryId;
    //创建时间
    @JsonFormat(shape= JsonFormat.Shape.STRING ,pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    //分类名称
    private String categoryName;
    //发布者用户名
    private String userUsername;
}
