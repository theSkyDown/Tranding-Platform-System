package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author eccentric
 */
@Mapper
public interface GoodMapper {

    /**
     * 分页、带关键字查询所有商品
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public List<Good> getAllGood(@Param("num") Integer num, @Param("size") Integer size, @Param("keyword") String keyword);


    /**
     * 统计商品数量
     * @param keyword
     * @return
     */
    public Integer getTotal(@Param("keyword") String keyword);


    /**
     * 通过商品标识获取商品信息
     * @param goodId
     * @return
     */
    @Select("select * from goods where good_id = #{goodId}")
    public Good getGoodById(@Param("goodId") Integer goodId);


    /**
     * 通过商品名称，商品类型，发布人获取商品信息
     * @param goodName
     * @param categoryId
     * @return
     */
    @Select("select * from goods where good_name=#{goodName} and category_id = #{categoryId} and user_id = #{userId}")
    public List<Good> getGoodByNameAndCategoryAndUserId(@Param("goodName") String goodName,@Param("categoryId") Integer categoryId,@Param("userId") Integer userId);

    /**
     * 添加用户信息
     * @param good
     * @return
     */
    public Integer addGood(Good good);


    /**
     * 通过商品标识删除商品
     * @param goodId
     * @return
     */
    @Delete("delete from goods where good_id = #{goodId}")
    public Integer deleteGoodById(@Param("goodId") Integer goodId);


    /**
     * 批量删除商品
     * @param idList
     * @return
     */
    public Integer deleteByBatchIds(@Param("idList") List<Integer> idList);

    /**
     * 修改商品信息
     * @param good
     * @return
     */
    public Integer updateGood(Good good);

    /**
     * 获取所有正在销售的商品
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public List<Good> getAllGoodOnSale(Integer num, Integer size, String keyword);

    /**
     * 统计所有正在销售的商品数量
     * @param keyword
     * @return
     */
    public Integer getTotalOnSale(String keyword);

    /**
     * 通过用户标识获取该用户出售的所有商品
     * @param userId
     * @return
     */
    public List<Good> getGoodByUserId(@Param("num") Integer num,@Param("size") Integer size,@Param("keyword") String keyword,@Param("userId") Integer userId);


    /**
     * 通过用户标识统计该用户出售的商品的数量
     * @param keyword
     * @param userId
     * @return
     */
    public Integer getTotalByUserId(@Param("keyword") String keyword,@Param("userId") Integer userId);
}
