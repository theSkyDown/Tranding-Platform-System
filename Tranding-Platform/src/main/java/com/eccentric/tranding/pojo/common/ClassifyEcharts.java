package com.eccentric.tranding.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于商品种类的echarts图
 * @author eccentric
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClassifyEcharts {
    //分类名称
    private String name;
    //该分类发布了商品数量
    private Integer value;
}
