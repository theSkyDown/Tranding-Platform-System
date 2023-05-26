package com.eccentric.tranding.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于统计分类的echarts图
 * @author eccentric
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RateEcharts {
    //评分等级（0-5）
    private Integer rate;
    //该评分等级有多少人
    private Integer value;
}
