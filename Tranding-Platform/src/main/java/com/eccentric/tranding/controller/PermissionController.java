package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Permission;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eccentric
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @Autowired
    private PermissionService permissionService;


    /**
     * 获取所有权限信息
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllPermission(@RequestParam("num") Integer num,@RequestParam("size") Integer size){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return Ret.ok(null,permissionService.getAllPermission(num,size));
    }


    /**
     * 统计所有权限的数量
     * @return
     */
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotal(){
        return permissionService.getTotalPermission();
    }


    /**
     * 给角色分配权限
     * @param permission
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addPermission(@RequestBody Permission permission){
        if (!isOk(permission) || !isOk(permission.getRoleId()) || !isOk(permission.getModuleId())){
            return Ret.fail("参数异常");
        }
        return permissionService.addPermission(permission);
    }


    /**
     * 删除指定权限
     * @param permissionId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deletePermission(@RequestParam("permissionId") Integer permissionId){
        if (!isOk(permissionId)){
            return Ret.fail("参数异常");
        }
        return permissionService.deletePermission(permissionId);
    }


    /**
     * 批量删除权限
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
        return permissionService.deleteByIds(idList);
    }

}
