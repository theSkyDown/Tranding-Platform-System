package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eccentric
 */
@RestController
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    /**
     * 获取所有资源信息（分页）
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllModule(@RequestParam("num") Integer num, @RequestParam("size") Integer size){
        if (num == null || num < 0 || size == null || size <= 0){
            return Ret.fail("参数异常");
        }
        return Ret.ok(null,moduleService.getAllModule(num,size));
    }


    /**
     * 资源添加功能
     * @param module
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addModule(@RequestBody Module module){
        if (!isOk(module) || !isOk(module.getModuleName()) || !isOk(module.getUrl())){
            return Ret.fail("参数异常");
        }
        return moduleService.addModule(module);
    }


    /**
     * 通过id删除资源
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteModule(@RequestParam("moduleId") Integer moduleId){
        if (!isOk(moduleId)){
            return Ret.fail("参数异常");
        }
        return moduleService.deleteModule(moduleId);
    }

    /**
     * 批量删除资源功能
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/ids",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteByBatchIds(@RequestParam("ids") String ids){
        if (!isIdsOk(ids)){
            return Ret.fail("参数异常");
        }
        List<Integer> idList = new ArrayList<>();
        String[] idArray = ids.split(",");
        for (String id : idArray){
            idList.add(Integer.valueOf(id));
        }
        return moduleService.deleteByBatchIds(idList);
    }


    /**
     * 资源修改功能
     * @param module
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updateModule(@RequestBody Module module){
        if (!isOk(module) || !isOk(module.getModuleName()) || !isOk(module.getUrl())){
            return Ret.fail("参数异常");
        }
        return moduleService.updateModule(module);
    }
}