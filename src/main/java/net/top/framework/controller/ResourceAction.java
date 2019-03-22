package net.top.framework.controller;

import net.top.framework.service.ResourceService;
import net.top.framework.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/6/26
 * @version： V1.0
 */
@Controller
public class ResourceAction {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceTypeService resourceTypeService;
}
