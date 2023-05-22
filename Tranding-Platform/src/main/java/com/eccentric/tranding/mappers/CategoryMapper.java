package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author eccentric
 */
@Mapper
public interface CategoryMapper {

    /**
     * 分页、关键字查询所有分类信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    public List<Category> getAllCategory(@Param("num") Integer num, @Param("size") Integer size, @Param("keyword") String keyword);


    /**
     * 统计所有分类的数量
     * @param keyword
     * @return
     */
    public Integer getTotalCategory(@Param("keyword") String keyword);


    /**
     * 通过分类id获取分类信息
     * @param categoryId
     * @return
     */
    @Select("select * from category where category_id = #{categoryId}")
    public Category getCategoryById(@Param("categoryId") Integer categoryId);


    /**
     * 通过分类名称获取分类
     * @param categoryName
     * @return
     */
    @Select("select * from category where category_name = #{categoryName}")
    public Category getCategoryByName(@Param("categoryName") String categoryName);


    /**
     * 添加分类信息
     * @param category
     * @return
     */
    @Insert("insert into category values(#{categoryId},#{categoryName},#{createTime})")
    public Integer insertCategory(Category category);

    /**
     * 通过分类标识删除分类信息
     * @param categoryId
     * @return
     */
    @Delete("delete from category where category_id = #{categoryId}")
    public Integer deleteById(@Param("categoryId") Integer categoryId);


    /**
     * 批量删除分类信息
     * @param idList
     * @return
     */
    public Integer deleteByBatchIds(List<Integer> idList);

    /**
     * 修改分类的信息
     * @param category
     * @return
     */
    @Update("update category set category_name = #{categoryName} where category_id = #{categoryId}")
    public Integer updateCategory(Category category);
}
