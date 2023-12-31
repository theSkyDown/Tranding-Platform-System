package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.Category;
import com.eccentric.tranding.pojo.common.ClassifyEcharts;
import com.eccentric.tranding.pojo.common.RateEcharts;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.TradingEcharts;

import java.util.List;

/**
 * 对用户操作提供服务
 * @author eccentric
 */
public interface UserService {

    /**
     * 用于设置一些默认参数
     * @param user
     */
    public void setDefault(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public Ret addUser(User user) throws Exception;

    /**
     * 用于判断用户是否存在
     * @param user
     * @return
     */
    public Boolean isExist(User user);

    /**
     * 删除用户
     * @param userId
     * @param actionUser 执行操作的用户
     * @return
     */
    public Ret deleteUser(Integer userId,User actionUser);


    /**
     * 批量删除用户
     * @param idList
     * @param actionUser 执行操作的用户
     * @return
     */
    public Ret deleteByIds(List<Integer> idList,User actionUser);

    /**
     * 修改用户信息
     * @param user
     * @param actionUser 执行操作的用户
     * @return
     */
    public Ret updateUser(User user,User actionUser);

    /**
     * 用户登陆
     * @param user
     * @return
     */
    public Ret login(User user) throws Exception;


    /**
     * 重置用户的密码
     * @param userId
     * @param actionUser 执行操作的用户
     * @return
     */
    public Ret resetPassword(Integer userId,User actionUser) throws Exception;

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User getUserById(Integer userId);

    /**
     * 通过电话查找用户
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);


    /**
     * 用户修改密码
     * @param userId
     * @param password
     * @return
     */
    public Ret updatePassword(Integer userId,String password) throws Exception;


    /**
     * 获取所有用户（分页）
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    public Ret getAllUser(Integer pageNum,Integer pageSize,String keyword);


    /**
     * 切换用户的状态
     * @param userId
     * @param actionUser
     * @return
     */
    public Ret toggleUserStatus(Integer userId,User actionUser);


    /**
     * 统计一共有多少用户
     * @return
     */
    public Ret getTotalUser(String keyword);

    /**
     * 获取用户的分数
     * @param actionUser
     * @return
     */
    public Double getUserRate(User actionUser);

    /**
     * 获取该用户出售的商品分类信息
     * @param actionUser
     * @return
     */
    public List<ClassifyEcharts> getSaleCategoryEcharts(User actionUser);

    /**
     * 获取统计评分的echarts图
     * @param actionUser
     * @return
     */
    public List<RateEcharts> getRateTotalEcharts(User actionUser);

    /**
     * 获取一年销售额的echarts图
     * @param actionUser
     * @return
     */
    public List<TradingEcharts> getYearTradingEcharts(User actionUser);
}
