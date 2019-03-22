package net.top.framework.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.interceptor
 * @Description:
 * @author: xsy
 * @date： 2016/6/18
 * @version： V1.0
 */
@Controller
public class OverallExceptionResolver implements HandlerExceptionResolver {
    /**
     * 进行全局异常的过滤和处理
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        //handler为当前处理器适配器执行的对象
        String message = ex.getMessage();

        ModelAndView modelAndView = new ModelAndView("/system/error/error");
        //跳转到相应的处理页面
        modelAndView.addObject("errorMsg", message);
        return modelAndView;
    }

}
