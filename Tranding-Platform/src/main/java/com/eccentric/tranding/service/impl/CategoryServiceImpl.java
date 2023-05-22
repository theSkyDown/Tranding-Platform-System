package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.CategoryMapper;
import com.eccentric.tranding.pojo.Category;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author eccentric
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Ret getAllCategory(Integer num, Integer size, String keyword) {
        List<Category> categoryList = categoryMapper.getAllCategory(num, size,keyword);
        return Ret.ok(null,categoryList);

    }

    @Override
    public Ret getTotalCategory(String keyword) {
        return Ret.ok(null,categoryMapper.getTotalCategory(keyword));
    }


    @Override
    public Boolean isExist(Category category) {
        if (category == null){
            return false;
        }
        //通过分类标识判断分类是否存在
        Integer categoryId = category.getCategoryId();
        if (categoryMapper.getCategoryById(categoryId) != null){
            return true;
        }
        //通过分类名称判断分类是否存在
        String categoryName = category.getCategoryName();
        if(categoryMapper.getCategoryByName(categoryName) != null){
            return true;
        }
        return false;
    }

    @Override
    public Ret addCategory(Category category) {
        if (isExist(category)){
            return Ret.fail("分类已存在");
        }
        //设置创建时间
        category.setCreateTime(LocalDateTime.now());
        //执行添加操作
        Integer count = categoryMapper.insertCategory(category);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteCategory(Integer categoryId) {
        Category deleteCategory = categoryMapper.getCategoryById(categoryId);
        if (deleteCategory == null){
            return Ret.fail("分类信息不存在");
        }
        //执行删除操作
        Integer count = categoryMapper.deleteById(categoryId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByIds(List<Integer> idList) {
        Integer count = categoryMapper.deleteByBatchIds(idList);
        return count > 0 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret updateCategory(Category category) {
        if (!isExist(category)){
            return Ret.fail("分类不存在，修改失败");
        }

        //防止修改的角色名称已经被使用
        Category categoryByName = categoryMapper.getCategoryByName(category.getCategoryName());
        if (categoryByName != null && !category.getCategoryId().equals(categoryByName.getCategoryId())){
            return Ret.fail("分类信息修改失败，分类名称以被使用");
        }

        Integer count = categoryMapper.updateCategory(category);
        return count==1 ? Ret.ok() : Ret.fail();
    }
}
