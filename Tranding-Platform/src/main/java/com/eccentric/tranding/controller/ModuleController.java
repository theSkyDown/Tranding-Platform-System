package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.ModuleService;
import com.eccentric.tranding.utils.UserHolder;
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
    public Ret getAllModule(@RequestParam("num") Integer num, @RequestParam("size") Integer size, @RequestParam("keyword") String keyword){
        if (num == null || num < 0 || size == null || size <= 0){
            return Ret.fail("参数异常");
        }
        return Ret.ok(null,moduleService.getAllModule(num,size,keyword));
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
        User actionUser = UserHolder.getUser();
        return moduleService.addModule(module,actionUser);
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
        User actionUser = UserHolder.getUser();
        return moduleService.deleteModule(moduleId,actionUser);
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
        User actionUser = UserHolder.getUser();
        return moduleService.deleteByBatchIds(idList,actionUser);
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
        User actionUser = UserHolder.getUser();
        return moduleService.updateModule(module,actionUser);
    }


    /**
     * 获取一共有多少条资源数据
     * @return
     */
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotal(@RequestParam("keyword") String keyword){
        return moduleService.getTotalModule(keyword);
    }
}
