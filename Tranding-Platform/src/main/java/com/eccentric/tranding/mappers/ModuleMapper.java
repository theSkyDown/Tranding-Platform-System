package com.eccentric.tranding.mappers;

import com.eccentric.tranding.pojo.Module;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author eccentric
 */
@Mapper
public interface ModuleMapper {

    /**
     * 获取所有资源信息(分页)
     * @param num
     * @param size
     * @return
     */
    public List<Module> getAllModulePage(@Param("num") Integer num,@Param("size") Integer size,@Param("keyword") String keyword);


    /**
     * 根据id查找资源
     * @param moduleId
     * @return
     */
    @Select("select * from module where module_id = #{moduleId}")
    public Module getModuleById(@Param("moduleId") Integer moduleId);

    /**
     * 根据资源路径获取资源
     * @param url
     * @return
     */
    @Select("select * from module where url = #{url}")
    public Module getModuleByUrl(@Param("url") String url);


    /**
     * 添加资源
     * @param module
     * @return
     */
    @Insert("insert into module values(#{moduleId},#{moduleName},#{url},#{isMenu})")
    public Integer insertModule(Module module);


    /**
     * 根据id删除用户
     * @param moduleId
     * @return
     */
    @Delete("delete from module where module_id = #{moduleId}")
    public Integer deleteModule(@Param("moduleId") Integer moduleId);


    /**
     *  批量删除资源
     * @param idList
     * @return
     */
    public Integer deleteByBatchIds(@Param("idList") List<Integer> idList);


    /**
     * 修改资源信息
     * @param module
     * @return
     */
    @Update("update module set module_name=#{moduleName},url=#{url},is_menu=#{isMenu} where module_id=#{moduleId}")
    public Integer updateModule(Module module);

    /**
     * 统计资源数量
     * @param keyword
     * @return
     */
    public Integer getTotalModule(@Param("keyword") String keyword);
}
