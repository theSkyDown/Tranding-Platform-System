package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Category;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.CategoryService;
import com.eccentric.tranding.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 * @author eccentric
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类信息
     * @param num
     * @param size
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllCategory(@RequestParam("num") Integer num,@RequestParam("size") Integer size,@RequestParam("keyword") String keyword){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return categoryService.getAllCategory(num, size, keyword);
    }

    /**
     * 统计分类信息的数量
     * @return
     */
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotal(@RequestParam("keyword") String keyword){
        return categoryService.getTotalCategory(keyword);
    }


    /**
     * 分类添加功能
     * @param category
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addCategory(@RequestBody Category category){
        if (!isOk(category) || !isOk(category.getCategoryName())){
            return Ret.fail("参数异常");
        }
        return categoryService.addCategory(category);
    }

    /**
     * 分类删除功能
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteCategory(@RequestParam("categoryId") Integer categoryId){
        if (!isOk(categoryId)){
            return Ret.fail("参数异常");
        }
        return categoryService.deleteCategory(categoryId);
    }

    /**
     * 分类批量删除功能
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/ids",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteByBatchIds(@RequestParam("ids") String ids){
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
        return categoryService.deleteByIds(idList);
    }


    /**
     * 修改分类信息
     * @param category
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updateRole(@RequestBody Category category){
        if (!isOk(category) || !isOk(category.getCategoryName()) || !isOk(category.getCategoryId())){
            return Ret.fail("参数异常");
        }
        return categoryService.updateCategory(category);
    }
}
