package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.Role;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.RoleService;
import com.eccentric.tranding.utils.UserHolder;
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
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Ret getAllRole(@RequestParam("num") Integer num,@RequestParam("size") Integer size,@RequestParam("keyword") String keyword){
        if (num == null || size == null || num < 0 || size <= 0){
            return Ret.fail("参数异常");
        }
        return Ret.ok(null,roleService.getAllRole(num,size,keyword));
    }


    /**
     * 角色添加功能
     * @param role
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Ret addRole(@RequestBody Role role){
        if (!isOk(role) || !isOk(role.getRoleName())){
            return Ret.fail("参数异常");
        }
        return roleService.addRole(role);
    }


    /**
     * 角色删除功能
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Ret deleteRole(@RequestParam("roleId") Integer roleId){
        if (!isOk(roleId)){
            return Ret.fail("参数异常");
        }
        //获取执行操作的用户
        User actionUser = UserHolder.getUser();
        return roleService.deleteRole(roleId,actionUser);
    }


    /**
     * 角色批量删除功能
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
        //获取操作者的信息
        User actionUser = UserHolder.getUser();
        return roleService.deleteByIds(idList,actionUser);
    }


    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Ret updateRole(@RequestBody Role role){
        if (!isOk(role) || !isOk(role.getRoleName()) || !isOk(role.getRoleId()) || !isOk(role.getLevel())){
            return Ret.fail("参数异常");
        }
        //获取操作者的信息
        User actionUser = UserHolder.getUser();
        return roleService.updateRole(role,actionUser);
    }

    /**
     * 获取一共有多少条角色数据
     * @return
     */
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    @ResponseBody
    public Ret getTotal(@RequestParam("keyword") String keyword){
        return roleService.getTotalRole(keyword);
    }
}
