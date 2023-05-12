package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 分类表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    //分类标识
    private Integer categoryId;
    //分类名称
    private String categoryName;
    //创建时间
    private LocalDateTime createTime;
}
