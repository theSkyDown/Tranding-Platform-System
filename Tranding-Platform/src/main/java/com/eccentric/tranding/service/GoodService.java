package com.eccentric.tranding.service;


import com.eccentric.tranding.pojo.Good;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;

import java.util.List;

/**
 * @author eccentric
 */
public interface GoodService {

    /**
     * 获取所有商品信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public Ret getAllGood(Integer num, Integer size, String keyword);


    /**
     * 统计商品数量
     * @param keyword
     * @return
     */
    public Ret getTotal(String keyword);


    /**
     * 判断商品是否已经存在
     * @param good
     * @return
     */
    public Boolean isExist(Good good);

    /**
     * 添加商品
     * @param good
     * @return
     */
    public Ret addGood(Good good);


    /**
     * 通过商品标识删除商品
     * @param goodId
     * @return
     */
    public Ret deleteGood(Integer goodId);


    /**
     * 批量删除商品信息
     * @param idList
     * @return
     */
    public Ret deleteByIds(List<Integer> idList);

    /**
     * 修改商品信息
     * @param good
     * @return
     */
    public Ret updateGood(Good good,User actionUser);

    /**
     * 获取所有正在销售的商品
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public Ret getAllGoodOnSale(Integer num, Integer size, String keyword);

    /**
     * 统计所有正在销售的商品数量
     * @param keyword
     * @return
     */
    public Ret getTotalOnSale(String keyword);

    /**
     * 用户购买商品
     * @param goodId
     * @param actionUser
     * @return
     */
    public Ret buyGood(Integer goodId, User actionUser);

    /**
     * 通过标识获取商品的信息
     * @param goodId
     * @return
     */
    public Ret getGoodById(Integer goodId);

    /**
     * 通过用户标识获取该用户出售的所有商品
     * @param userId
     * @return
     */
    public Ret getGoodByUserId(Integer num,Integer size,String keyword,Integer userId);

    /**
     * 通过用户标识统计该用户出售的商品的数量
     * @param keyword
     * @param userId
     * @return
     */
    public Ret getTotalByUserId(String keyword,Integer userId);

    /**
     * 用户下架商品
     * @param goodId
     * @param actionUser
     * @return
     */
    public Ret takeDownGood(Integer goodId, User actionUser);
}
