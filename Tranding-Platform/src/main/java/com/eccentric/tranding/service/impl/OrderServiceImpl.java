package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.OrderMapper;
import com.eccentric.tranding.pojo.Order;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eccentric
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrders(Integer num, Integer size, String keyword) {
        return orderMapper.getAllOrders(num, size, keyword);
    }

    @Override
    public Ret getTotal(String keyword) {
        return Ret.ok(null,orderMapper.getTotal(keyword));
    }

    @Override
    public Ret deleteOrder(Integer orderId) {
        Order order = orderMapper.getOrderById(orderId);
        if (order == null){
            return Ret.fail("角色不存在，修改失败");
        }
        Integer count = orderMapper.deleteOrder(orderId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByIds(List<Integer> idList) {
        Integer count = orderMapper.deleteByBatchIds(idList);
        return count > 0 ? Ret.ok() : Ret.fail();
    }
}
