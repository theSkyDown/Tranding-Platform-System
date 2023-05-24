package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.dictionary.Encrypt;
import com.eccentric.tranding.dictionary.Identity;
import com.eccentric.tranding.dictionary.Status;
import com.eccentric.tranding.mappers.CategoryMapper;
import com.eccentric.tranding.mappers.GoodMapper;
import com.eccentric.tranding.mappers.OrderMapper;
import com.eccentric.tranding.mappers.UserMapper;
import com.eccentric.tranding.pojo.*;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.GoodService;
import com.eccentric.tranding.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author eccentric
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Ret getAllGood(Integer num, Integer size, String keyword) {
        List<Good> goodList = goodMapper.getAllGood(num, size,keyword);
        goodList.forEach(good -> {
            //设置分类名称
            Category categoryById = categoryMapper.getCategoryById(good.getCategoryId());
            if (categoryById != null){
                good.setCategoryName(categoryById.getCategoryName());
            }else{
                good.setCategoryName("分类信息异常");
            }
            //设置发布者名称
            User userByUserId = userMapper.getUserByUserId(good.getUserId());
            if (userByUserId != null){
                good.setUserUsername(userByUserId.getUsername());
            }else{
                good.setUserUsername("用户信息异常");
            }
        });
        return Ret.ok(null,goodList);
    }


    @Override
    public Ret getTotal(String keyword) {
        return Ret.ok(null,goodMapper.getTotal(keyword));
    }

    @Override
    public Boolean isExist(Good good) {
        if (good == null){
            return false;
        }
        Integer goodId = good.getGoodId();
        if (goodMapper.getGoodById(goodId)!=null){
            return true;
        }
        String goodName = good.getGoodName();
        Integer categoryId = good.getCategoryId();
        Integer userId = good.getUserId();
        if (goodMapper.getGoodByNameAndCategoryAndUserId(goodName,categoryId,userId).size() != 0){
            return true;
        }
        return false;
    }

    @Override
    public Ret addGood(Good good) {
        //拦截用户已经存在
        if (isExist(good)) {
            return Ret.fail("商品已存在");
        }

        //设置默认状态，在售
        good.setStatus(Status.ENABLE);
        //设置创建时间
        good.setCreateTime(LocalDateTime.now());

        //进行添加
        boolean result = goodMapper.addGood(good)==1;
        return result?Ret.ok():Ret.fail();
    }

    @Override
    public Ret deleteGood(Integer goodId){
        //判断要删除商品是否存在
        Good temp = new Good();
        temp.setGoodId(goodId);
        if (!isExist(temp)){
            return Ret.fail("商品不存在");
        }
        //执行删除操作
        Integer count = goodMapper.deleteGoodById(goodId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByIds(List<Integer> idList) {
        //批量删除
        Integer count = goodMapper.deleteByBatchIds(idList);
        return count>0 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret updateGood(Good good,User actionUser) {
        //拦截不存在的商品
        if (!isExist(good)){
            return Ret.fail("商品不存在，修改失败");
        }
        //判断是否有权限修改商品信息(如果不是发布者，也不是admin无法修改商品的信息)
        if (!good.getUserId().equals(actionUser.getUserId()) && !actionUser.getUserId().equals(1)){
            return Ret.fail("你没有权限修改该商品的信息");
        }

        //执行修改操作
        boolean result = goodMapper.updateGood(good)==1;
        return result?Ret.ok():Ret.fail();
    }

    @Override
    public Ret getAllGoodOnSale(Integer num, Integer size, String keyword) {
        List<Good> goodList = goodMapper.getAllGoodOnSale(num, size,keyword);
        goodList.forEach(good -> {
            //设置分类名称
            Category categoryById = categoryMapper.getCategoryById(good.getCategoryId());
            if (categoryById != null){
                good.setCategoryName(categoryById.getCategoryName());
            }else{
                good.setCategoryName("分类信息异常");
            }
            //设置发布者名称
            User userByUserId = userMapper.getUserByUserId(good.getUserId());
            if (userByUserId != null){
                good.setUserUsername(userByUserId.getUsername());
            }else{
                good.setUserUsername("用户信息异常");
            }
        });
        return Ret.ok(null,goodList);
    }

    @Override
    public Ret getTotalOnSale(String keyword) {
        return Ret.ok(null,goodMapper.getTotalOnSale(keyword));
    }

    @Override
    public Ret buyGood(Integer goodId, User actionUser) {
        Good good = goodMapper.getGoodById(goodId);
        if (good == null){
            return Ret.fail("商品不存在，购买失败");
        }
        if (Status.DISABLE.equals(good.getStatus())){
            return Ret.fail("商品已被购买");
        }
        if (actionUser.getUserId().equals(good.getUserId())){
            return Ret.fail("无法购买自己发布的商品");
        }
        //判断订单是否已经存在
        Order tempOrder = orderMapper.getOrderByGoodAndUser(goodId, actionUser.getUserId());
        if (tempOrder != null){
            return Ret.fail("订单已经存在");
        }
        //将商品状态切换至售出
        good.setStatus(Status.DISABLE);
        //添加订单信息
        Order order = new Order();
        order.setGoodId(good.getGoodId());
        order.setUserId(actionUser.getUserId());
        //1表示完成交易，2表示未完成
        order.setStatus(Status.DISABLE);
        order.setRate(0.0);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        //执行添加操作
        Integer addOrderCount = orderMapper.addOrder(order);
        Integer updateGoodCount = 0;
        //如果订单添加成功，则执行修改商品状态的操作
        if (addOrderCount==1){
             updateGoodCount = goodMapper.updateGood(good);
        }
        return updateGoodCount==1 ? Ret.ok() : Ret.fail();

    }


    @Override
    public Ret getGoodById(Integer goodId) {
        Good good = goodMapper.getGoodById(goodId);
        if (good == null) {
            return Ret.fail("商品不存在");
        }
        //填入详细信息
        //分类信息
        Category category = categoryMapper.getCategoryById(good.getCategoryId());
        good.setCategoryName(category.getCategoryName());
        //发布人
        User user = userMapper.getUserByUserId(good.getUserId());
        good.setUserUsername(user.getUsername());

        return Ret.ok(null,good);
    }

    @Override
    public Ret getGoodByUserId(Integer num,Integer size,String keyword,Integer userId) {
        List<Good> goodList = goodMapper.getGoodByUserId(num,size,keyword,userId);
        goodList.forEach(good -> {
            //设置分类名称
            Category categoryById = categoryMapper.getCategoryById(good.getCategoryId());
            if (categoryById != null){
                good.setCategoryName(categoryById.getCategoryName());
            }else{
                good.setCategoryName("分类信息异常");
            }
            //设置发布者名称
            User userByUserId = userMapper.getUserByUserId(good.getUserId());
            if (userByUserId != null){
                good.setUserUsername(userByUserId.getUsername());
            }else{
                good.setUserUsername("用户信息异常");
            }
        });
        return Ret.ok(null,goodList);
    }


    @Override
    public Ret getTotalByUserId(String keyword, Integer userId) {
        return Ret.ok(null,goodMapper.getTotalByUserId(keyword,userId));
    }

    @Override
    public Ret takeDownGood(Integer goodId, User actionUser) {
        //判断要删除商品是否存在
        Good temp = goodMapper.getGoodById(goodId);
        if (temp == null){
            return Ret.fail("商品不存在");
        }

        //拦截商品已经售出
        if (Status.DISABLE.equals(temp.getStatus())){
            return Ret.fail("商品已经售出，无法下架");
        }

        if (!temp.getUserId().equals(actionUser.getUserId()) && actionUser.getUserId().equals(1)){
            return Ret.fail("你没有权限下架该商品");
        }

        //执行删除操作
        Integer count = goodMapper.deleteGoodById(goodId);
        return count==1 ? Ret.ok() : Ret.fail();
    }
}
