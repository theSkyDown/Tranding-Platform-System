package com.eccentric.tranding.service.impl;

import com.eccentric.tranding.mappers.ModuleMapper;
import com.eccentric.tranding.pojo.Module;
import com.eccentric.tranding.pojo.User;
import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author eccentric
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> getAllModule(Integer num, Integer size) {
        return moduleMapper.getAllModulePage(num,size);
    }

    @Override
    public Boolean isExist(Module module) {
        if (module == null){
            return false;
        }
        Integer moduleId = module.getModuleId();
        if (getModuleById(moduleId) != null){
            return true;
        }
        String url = module.getUrl();
        if (getModuleByUrl(url) != null){
            return true;
        }
        return false;
    }

    @Override
    public Module getModuleById(Integer moduleId) {
        return moduleMapper.getModuleById(moduleId);
    }

    @Override
    public Module getModuleByUrl(String url) {
        return moduleMapper.getModuleByUrl(url);
    }

    @Override
    public Ret addModule(Module module) {
        if (isExist(module)){
            return Ret.fail("资源已存在");
        }
        //设置默认选项-不是菜单
        module.setIsMenu(0);

        //执行添加操作
        Integer count = moduleMapper.insertModule(module);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteModule(Integer moduleId) {
        Module temp = new Module();
        temp.setModuleId(moduleId);
        if (!isExist(temp)){
            return Ret.fail("资源不存在");
        }
        Integer count = moduleMapper.deleteModule(moduleId);
        return count==1 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret deleteByBatchIds(List<Integer> idList) {
        Integer count = moduleMapper.deleteByBatchIds(idList);
        return count>0 ? Ret.ok() : Ret.fail();
    }

    @Override
    public Ret updateModule(Module module) {
        if (!isExist(module)){
            return Ret.fail("资源不存在,修改失败");
        }

        //防止url被使用
        Module tempModule = moduleMapper.getModuleByUrl(module.getUrl());
        if (tempModule!=null && !module.getModuleId().equals(tempModule.getModuleId())){
            return Ret.fail("路由修改失败，路由已被使用");
        }

        //执行修改操作
        Integer count = moduleMapper.updateModule(module);
        return count==1 ? Ret.ok() : Ret.fail();
    }
}
