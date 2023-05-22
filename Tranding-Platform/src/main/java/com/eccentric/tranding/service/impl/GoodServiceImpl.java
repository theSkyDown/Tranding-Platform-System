package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.dictionary.Encrypt;
import com.eccentric.tranding.dictionary.Identity;
import com.eccentric.tranding.mappers.CategoryMapper;
import com.eccentric.tranding.mappers.GoodMapper;
import com.eccentric.tranding.mappers.UserMapper;
import com.eccentric.tranding.pojo.Category;
import com.eccentric.tranding.pojo.Good;
import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.User;
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
    public Ret updateGood(Good good) {
        //拦截不存在的商品
        if (!isExist(good)){
            return Ret.fail("商品不存在，修改失败");
        }
        //执行修改操作
        boolean result = goodMapper.updateGood(good)==1;
        return result?Ret.ok():Ret.fail();
    }
}
