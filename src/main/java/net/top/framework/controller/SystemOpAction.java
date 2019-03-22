package net.top.framework.controller;

import net.top.framework.annocations.RolePopedom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description: 系统维护
 * @author: xsy
 * @date： 2016/6/30
 * @version： V1.0
 */
@Controller
public class SystemOpAction {

    @RolePopedom(number = "系统数据维护")
    @RequestMapping(value = "system/database/list")
    public ModelAndView listBackupFiles() {
        return new ModelAndView("/system/database/list");
    }
}
