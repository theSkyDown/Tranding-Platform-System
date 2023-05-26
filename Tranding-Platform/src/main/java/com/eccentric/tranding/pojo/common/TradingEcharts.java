package com.eccentric.tranding.pojo.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用于统计一年销售额的echarts图
 * @author eccentric
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradingEcharts {
    //订单创建的时间
    private Integer date;
    //该时间总的销售金额
    private Double value;
}
