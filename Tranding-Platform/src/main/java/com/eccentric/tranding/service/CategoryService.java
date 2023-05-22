package com.eccentric.tranding.service;


import com.eccentric.tranding.pojo.Category;
import com.eccentric.tranding.pojo.common.Ret;

import java.util.List;

/**
 * 为分类管理提供服务
 * @author eccentric
 */
public interface CategoryService {

    /**
     * 获取所有分类信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public Ret getAllCategory(Integer num,Integer size,String keyword);


    /**
     * 统计所有分类的数量
     * @param keyword
     * @return
     */
    public Ret getTotalCategory(String keyword);


    /**
     * 判断分类是否存在
     * @param category
     * @return
     */
    public Boolean isExist(Category category);

    /**
     * 添加分类信息
     * @param category
     * @return
     */
    public Ret addCategory(Category category);


    /**
     * 删除指定的分类信息
     * @param categoryId
     * @return
     */
    public Ret deleteCategory(Integer categoryId);


    /**
     * 批量删除分类信息
     * @param idList
     * @return
     */
    public Ret deleteByIds(List<Integer> idList);


    /**
     * 修改分类信息
     * @param category
     * @return
     */
    public Ret updateCategory(Category category);
}
