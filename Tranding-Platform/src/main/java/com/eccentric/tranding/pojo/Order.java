package com.eccentric.tranding.pojo;

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
    //订单价格
    private Double price;
    //购买方式
    private Integer payType;
    //订单状态
    private Integer status;
    //创建时间
    private LocalDateTime createTime;
    //最后更新时间
    private LocalDateTime updateTime;
}
