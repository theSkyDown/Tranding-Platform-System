package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eccentric
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色的信息(分页)
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value = "/all/{num}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllRole(@PathVariable("num") Integer num,@PathVariable("size") Integer size){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return Ret.ok(null,roleService.getAllRole(num,size));
    }


    /**
     * 角色添加功能
     * @param role
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addRole(@RequestBody Role role){
        if (!isOk(role)){
            return Ret.fail("参数异常");
        }
        if (role.getRoleName()==null){
            return Ret.fail("参数异常");
        }
        return roleService.addRole(role);
    }


    /**
     * 角色删除功能
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/delete/{roleId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteRole(@PathVariable Integer roleId){
        if (!isOk(roleId)){
            return Ret.fail("参数异常");
        }
        return roleService.deleteRole(roleId);
    }


    /**
     * 角色批量删除功能
     * @param ids
     * @return
     */
    @RequestMapping("/delete/ids/{ids}")
    @ResponseBody
    public Ret deleteByBatchIds(@PathVariable String ids){
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
        return roleService.deleteByIds(idList);
    }

}
