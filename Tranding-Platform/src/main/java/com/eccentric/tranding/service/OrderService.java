package com.eccentric.tranding.service;


import com.eccentric.tranding.pojo.Order;
import com.eccentric.tranding.pojo.common.Ret;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eccentric
 */
public interface OrderService {


    /**
     * 获取所有订单信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public List<Order> getAllOrders(Integer num, Integer size, String keyword);


    /**
     * 统计所有订单的数量
     * @param keyword
     * @return
     */
    public Ret getTotal(String keyword);

    /**
     * 删除指定的订单
     * @param orderId
     * @return
     */
    public Ret deleteOrder(Integer orderId);

    /**
     * 批量删除订单
     * @param idList
     * @return
     */
    public Ret deleteByIds(List<Integer> idList);
}
