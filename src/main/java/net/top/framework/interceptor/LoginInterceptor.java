package net.top.framework.interceptor;

import net.top.framework.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.interceptor
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(!accountService.isLogin(httpServletRequest)) {
            httpServletResponse.sendRedirect("/user/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
