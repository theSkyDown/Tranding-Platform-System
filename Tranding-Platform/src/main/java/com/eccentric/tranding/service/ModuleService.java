package com.eccentric.tranding.service;

import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.common.Ret;

import java.util.List;

/**
 * 对资源管理提供服务
 * @author eccentric
 */
public interface ModuleService {

    /**
     * 获取所有资源信息(分页)
     * @param num
     * @param size
     * @return
     */
    public List<Module> getAllModule(Integer num,Integer size);

    /**
     * 判断资源是否存在
     * @param module
     * @return
     */
    public Boolean isExist(Module module);

    /**
     *  根据id查找资源
     * @param moduleId
     * @return
     */
    public Module getModuleById(Integer moduleId);

    /**
     * 根据路径查找资源
     * @param url
     * @return
     */
    public Module getModuleByUrl(String url);


    /**
     * 添加资源
     * @param module
     * @return
     */
    public Ret addModule(Module module);


    /**
     * 通过id删除资源
     * @param moduleId
     * @return
     */
    public Ret deleteModule(Integer moduleId);


    /**
     * 批量删除资源
     * @param idList
     * @return
     */
    public Ret deleteByBatchIds(List<Integer> idList);


    /**
     * 修改资源信息
     * @param module
     * @return
     */
    public Ret updateModule(Module module);
}