package net.top.framework.controller;

import net.top.framework.domain.ResourceType;
import net.top.framework.service.ResourceTypeService;
import net.top.framework.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/6/23
 * @version： V1.0
 */
@Controller
public class ResourceTypeAction {

    @Autowired
    private ResourceTypeService resourceTypeService;

}
