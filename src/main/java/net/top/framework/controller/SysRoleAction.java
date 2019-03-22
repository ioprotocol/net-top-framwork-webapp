package net.top.framework.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import net.top.framework.domain.Account;
import net.top.framework.domain.Resource;
import net.top.framework.domain.Role;
import net.top.framework.domain.RolePopedom;
import net.top.framework.service.BaseService;
import net.top.framework.service.RoleService;
import net.top.framework.util.JsonUtil;
import net.top.framework.webform.DataTable;
import net.top.framework.webform.Popedom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/6/27
 * @version： V1.0
 */
@Controller
public class SysRoleAction {

    @Autowired
    private RoleService roleService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "system/role/list")
    @ResponseBody
    public String list(Integer draw, Integer start, Integer length) {
        List<Role> list = roleService.listAll();
        return JsonUtil.objectToJsonString(new DataTable(draw, (long) list.size(), list));
    }

    @RequestMapping(value = "system/role/listoption")
    @ResponseBody
    public String listoption() {
        List<Role> list = roleService.listAll();
        Map<Integer, String> mapper = new HashMap<>();
        for(Role role : list) {
            mapper.put(role.getNumber(), role.getName());
        }
        return JsonUtil.objectToJsonString(mapper);
    }

    @RequestMapping(value = "system/role/save")
    @ResponseBody
    public String save(String json) {
        try {
            Role role = JsonUtil.jsonToObject(json, Role.class);
            roleService.saveOrUpdate(role);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "system/role/get")
    @ResponseBody
    public String get(Integer number) {
        Role role = baseService.load(Role.class, number);
        return JsonUtil.objectToJsonString(role);
    }

    @RequestMapping(value = "system/role/del")
    @ResponseBody
    public String del(Integer number) {
        if(number != 1) {
            roleService.del(number);
            String sql = " delete from Account where roleNumber=" + number;
            baseService.executeUpdate(sql);
            sql = " delete from RolePopedom where roleNumber=" + number;
            baseService.executeUpdate(sql);
            return "0";
        }
        return "-1";
    }

    @RequestMapping(value = "system/role/getpopedom")
    @ResponseBody
    public String getRolepopedom(Integer roleNumber) {
        List<Resource> resources = roleService.listResource();
        List<RolePopedom> rolePopedoms = roleService.listPrivilege(roleNumber);
        Map<Integer, Boolean> map = new HashMap<>();
        for(RolePopedom popedom : rolePopedoms) {
            map.put(popedom.getResourceNumber(), true);
        }

        List<Popedom> popedoms = new ArrayList<>();
        for(Resource resource : resources) {
            Popedom popedom = new Popedom();
            popedom.setName(resource.getName());
            popedom.setNumber(resource.getNumber());
            popedom.setUrl(resource.getUrl());
            if(map.get(resource.getNumber()) != null)
                popedom.setChecked(true);
            else
                popedom.setChecked(false);
            popedoms.add(popedom);
        }

        return JsonUtil.objectToJsonString(popedoms);
    }

    @RequestMapping(value = "system/role/savepopedom")
    @ResponseBody
    public String saveRolepopedom(Integer roleNumber, String json) {
        try {
            List<Popedom> popedoms = JsonUtil.jsonToObject(json, new TypeReference<List<Popedom>>() {});
            List<RolePopedom> rolePopedoms = new ArrayList<>();

            for (Popedom popedom : popedoms) {
                if(popedom.getChecked()) {
                    RolePopedom rolePopedom = new RolePopedom();
                    rolePopedom.setRoleNumber(roleNumber);
                    rolePopedom.setResourceNumber(popedom.getNumber());
                    rolePopedoms.add(rolePopedom);
                }
            }

            roleService.batchSaveRolePopedom(rolePopedoms, roleNumber);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }
}
