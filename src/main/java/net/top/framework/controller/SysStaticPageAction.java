package net.top.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/11/18
 * @version： V1.0
 */
@Controller
public class SysStaticPageAction {
    @RequestMapping(value = "static/{root}/{path}/{node}")
    public String page(@PathVariable String root, @PathVariable String path, @PathVariable String node, HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        Set<String> keys = paramsMap.keySet();
        for(String key : keys) {
            if(paramsMap.get(key).length == 1)
                request.setAttribute(key, paramsMap.get(key)[0]);
            else
                request.setAttribute(key, paramsMap.get(key));
        }
        if(node == null)
            return String.format("%s/%s", root, path);
        return String.format("%s/%s/%s", root, path, node);
    }

    @RequestMapping(value = "static/{root}/{path}")
    public String page(@PathVariable String root, @PathVariable String path, HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        Set<String> keys = paramsMap.keySet();
        for(String key : keys) {
            if(paramsMap.get(key).length == 1)
                request.setAttribute(key, paramsMap.get(key)[0]);
            else
                request.setAttribute(key, paramsMap.get(key));
        }
        return String.format("%s/%s", root, path);
    }
}
