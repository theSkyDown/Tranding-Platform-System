package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author eccentric
 */
@RestController
@RequestMapping(value = "/role",method = RequestMethod.GET)
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

}
