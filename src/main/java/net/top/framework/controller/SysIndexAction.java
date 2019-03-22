package net.top.framework.controller;

import net.top.framework.domain.Resource;
import net.top.framework.service.AccountService;
import net.top.framework.util.JsonUtil;
import net.top.framework.webform.MenuItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/11/17
 * @version： V1.0
 */
@Controller
public class SysIndexAction {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "system/index")
    @ResponseBody
    public String index(HttpServletRequest request) {
        List<Resource> resourceList = accountService.getResourceByLoginAccount(request);

        List<MenuItems> list = new ArrayList<>();

        MenuItems menuItems = new MenuItems();
        for(int i = 0; i < resourceList.size(); i++) {
            Resource rec = resourceList.get(i);
            if(menuItems.getName() == null) {
                menuItems.setName(rec.getResourceType().getName());
                menuItems.setIcon(rec.getResourceType().getIcon());
                menuItems.setId(rec.getResourceTypeId());
            }

            if(!menuItems.getName().equalsIgnoreCase(rec.getResourceType().getName())) {
                list.add(menuItems);
                menuItems = new MenuItems();
                menuItems.setName(rec.getResourceType().getName());
                menuItems.setIcon(rec.getResourceType().getIcon());
            }

            Map items = new LinkedHashMap();
            items.put("name", rec.getName());
            items.put("url", rec.getUrl());
            menuItems.addItem(items);

            if(i == resourceList.size() - 1) {
                list.add(menuItems);
                menuItems = new MenuItems();
            }
        }

        return JsonUtil.objectToJsonString(list);
    }
}
