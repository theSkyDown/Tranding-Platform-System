package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.dictionary.Status;
import com.eccentric.tranding.mappers.GoodMapper;
import com.eccentric.tranding.mappers.OrderMapper;
import com.eccentric.tranding.pojo.Good;
import com.eccentric.tranding.pojo.Order;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.User;
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

    @Autowired
    private GoodMapper goodMapper;

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
            return Ret.fail("订单不存在，删除失败");
        }
        Integer count = orderMapper.deleteOrder(orderId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByIds(List<Integer> idList) {
        Integer count = orderMapper.deleteByBatchIds(idList);
        return count > 0 ? Ret.ok() : Ret.fail();
    }

    @Override
    public List<Order> getAllOrderByActionUser(Integer num, Integer size, String keyword,Integer actionUserId) {
        return orderMapper.getAllOrderByActionUser(num, size, keyword,actionUserId);
    }

    @Override
    public Ret getTotalByActionUser(String keyword, Integer actionUserId) {
        return Ret.ok(null,orderMapper.getTotalByActionUser(keyword,actionUserId));
    }

    @Override
    public Ret cancelOrder(Integer orderId, User actionUser) {
        Order order = orderMapper.getOrderById(orderId);
        if (order == null){
            return Ret.fail("订单不存在");
        }

        if (order.getStatus().equals(Status.ENABLE)){
            return Ret.fail("订单已经完成，无法取消");
        }

        //判断是否有权限取消订单
        if (!order.getUserId().equals(actionUser.getUserId())){
            return Ret.fail("不是你购买的商品，无法取消");
        }

        Good good = goodMapper.getGoodById(order.getGoodId());
        if (good == null){
            return Ret.fail("商品不存在，无法取消");
        }

        //将商品的状态修改为在售
        good.setStatus(Status.ENABLE);
        Integer updateGoodCount = goodMapper.updateGood(good);

        Integer cancelOrderCount = 0;
        if (updateGoodCount == 1){
            cancelOrderCount = orderMapper.deleteOrder(orderId);
        }
        return cancelOrderCount==1 ? Ret.ok() : Ret.fail();
    }


    @Override
    public Ret completeOrder(Integer orderId,Double rate, User actionUser) {
        Order order = orderMapper.getOrderById(orderId);
        if (order == null){
            return Ret.fail("订单不存在");
        }
        Integer count = orderMapper.completeOrder(orderId,rate);
        return count==1 ? Ret.ok() : Ret.fail();
    }
}
