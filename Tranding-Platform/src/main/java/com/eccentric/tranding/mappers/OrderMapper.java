package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author eccentric
 */
@Mapper
public interface OrderMapper {

    /**
     * 添加订单
     * @param order
     * @return
     */
    public Integer addOrder(Order order);

    /**
     * 通过商品id和发布人id获取订单
     * @param goodId
     * @param userId
     * @return
     */
    @Select("select * from orders where good_id=#{goodId} and user_id=#{userId}")
    public Order getOrderByGoodAndUser(@Param("goodId") Integer goodId,@Param("userId") Integer userId);

    /**
     * 获取所有订单信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public List<Order> getAllOrders(@Param("num") Integer num,@Param("size") Integer size,@Param("keyword") String keyword);


    /**
     * 统计所有订单的数量
     * @param keyword
     * @return
     */
    public Integer getTotal(@Param("keyword") String keyword);

    /**
     * 通过订单标识找到订单
     * @param orderId
     * @return
     */
    @Select("select * from orders where order_id = #{orderId}")
    public Order getOrderById(@Param("orderId") Integer orderId);

    /**
     * 通过订单标识删除订单
     * @param orderId
     * @return
     */
    @Delete("delete from orders where order_id = #{orderId}")
    public Integer deleteOrder(@Param("orderId") Integer orderId);


    /**
     * 批量删除订单
     * @param idList
     * @return
     */
    public Integer deleteByBatchIds(List<Integer> idList);

    /**
     * 我的订单获取所有购买的订单信息
     * @return
     */
    public List<Order> getAllOrderByActionUser(@Param("num") Integer num,@Param("size") Integer size,@Param("keyword") String keyword,@Param("actionUserId") Integer actionUserId);

    /**
     * 统计我的订单中所有订单的数量
     * @param keyword
     * @param actionUserId
     * @return
     */
    public Integer getTotalByActionUser(@Param("keyword") String keyword,@Param("actionUserId") Integer actionUserId);


    /**
     * 完成订单并评分操作
     * @param orderId
     * @param rate
     * @return
     */
    @Update("update orders set rate=#{rate},status=1 where order_id = #{orderId}")
    public Integer completeOrder(Integer orderId,Double rate);
}
