package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.OrderService;
import com.eccentric.tranding.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eccentric
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取所有订单信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllOrder(@RequestParam("num") Integer num,@RequestParam("size") Integer size,@RequestParam("keyword") String keyword){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return Ret.ok(null,orderService.getAllOrders(num,size,keyword));
    }

    /**
     * 统计所有订单的数量
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotal(@RequestParam("keyword") String keyword){
        return orderService.getTotal(keyword);
    }


    /**
     * 删除指定的订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteOrder(@RequestParam("orderId") Integer orderId){
        if (!isOk(orderId)){
            return Ret.fail("参数异常");
        }
        return orderService.deleteOrder(orderId);
    }


    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/ids",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteOrdersByIds(@RequestParam("ids") String ids){
        List<Integer> idList = new ArrayList<>();
        //判断参数是否正常
        String[] idArray = ids.split(",");
        for (String id : idArray){
            if (!id.matches("\\d+")){
                return Ret.fail("参数异常");
            }
            idList.add(Integer.valueOf(id));
        }
        //防止最后一个为逗号
        if (ids.lastIndexOf(",") == ids.length()-1){
            return Ret.fail("参数异常");
        }
        return orderService.deleteByIds(idList);
    }
}
