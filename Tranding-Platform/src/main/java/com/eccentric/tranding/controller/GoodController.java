package com.eccentric.tranding.controller;


import com.eccentric.tranding.pojo.Good;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.GoodService;
import com.eccentric.tranding.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author eccentric
 */
@RestController
@RequestMapping("/good")
public class GoodController extends BaseController{

    @Autowired
    private GoodService goodService;


    /**
     * 获取所有商品的信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllGood(@RequestParam("num") Integer num,@RequestParam("size") Integer size,@RequestParam("keyword") String keyword){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return goodService.getAllGood(num,size,keyword);
    }

    /**
     * 获取所有在售商品的信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/all/trading",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllGoodOnSale(@RequestParam("num") Integer num,@RequestParam("size") Integer size,@RequestParam("keyword") String keyword){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return goodService.getAllGoodOnSale(num,size,keyword);
    }


    /**
     * 获取一共有多少条商品数据
     * @return
     */
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotal(@RequestParam("keyword") String keyword){
        return goodService.getTotal(keyword);
    }

    /**
     * 获取一共有多少条在售的商品数据
     * @return
     */
    @RequestMapping(value = "/total/trading",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotalOnSale(@RequestParam("keyword") String keyword){
        return goodService.getTotalOnSale(keyword);
    }

    /**
     * 商品添加功能
     * @param good
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addGood(@RequestBody Good good) throws Exception {
        //拦截所有参数都为空的情况
        if (
                !isOk(good) || !isOk(good.getGoodName()) ||
                !isOk(good.getPrice()) || !isOk(good.getCategoryId())||
                !isOk(good.getDescription())|| !isOk(good.getPayType()) ||
                !isOk(good.getGoodImg())
        ){
            return Ret.fail("参数异常");
        }

        //获取执行添加的用户信息
        User user = UserHolder.getUser();
        good.setUserId(user.getUserId());
        //添加商品
        return goodService.addGood(good);
    }


    /**
     * 删除商品
     * @param goodId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteGood(@RequestParam("goodId") Integer goodId){
        //拦截参数为空的情况
        if (!isOk(goodId)){
            return Ret.fail("参数异常");
        }
        //执行删除操作
        return goodService.deleteGood(goodId);
    }


    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/ids",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteByBatchId(@RequestParam("ids") String ids){
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
        return goodService.deleteByIds(idList);
    }

    /**
     * 修改商品信息
     * @param good
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updateGood(@RequestBody Good good){
        //拦截所有参数都为空的情况
        if (
                !isOk(good) || !isOk(good.getGoodName()) ||
                        !isOk(good.getPrice()) || !isOk(good.getCategoryId())||
                        !isOk(good.getDescription()) || !isOk(good.getUserId()) ||
                        !isOk(good.getGoodImg()) || !isOk(good.getGoodId()) ||
                        !isOk(good.getPayType())
        ){
            return Ret.fail("参数异常");
        }
        //更新用户信息
        return goodService.updateGood(good);
    }


    /**
     * 购买商品
     * @return
     */
    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    @ResponseBody
    public Ret buyGood(@RequestParam("goodId") Integer goodId){
        if (!isOk(goodId)){
            return Ret.fail("参数异常");
        }
        User actionUser = UserHolder.getUser();
        return goodService.buyGood(goodId,actionUser);
    }
}
