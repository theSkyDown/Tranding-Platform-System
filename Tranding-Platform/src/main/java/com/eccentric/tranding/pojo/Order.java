package com.eccentric.tranding.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 订单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    //订单标识
    private Integer orderId;
    //商品标识
    private Integer goodId;
    //购买用户标识
    private Integer userId;
    //订单状态
    private Integer status;
    //评分
    private Double rate;
    //创建时间
    @JsonFormat(shape= JsonFormat.Shape.STRING ,pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //最后更新时间
    @JsonFormat(shape= JsonFormat.Shape.STRING ,pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //发布者用户名
    private String saleUsername;
    //购买者用户名
    private String buyUsername;
    //商品名称
    private String goodName;
}
