package net.top.framework.interceptor;

import net.top.framework.annocations.RolePopedom;
import net.top.framework.service.AccountService;
import net.top.framework.service.LogService;
import net.top.framework.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.interceptor
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AccountService accountService;
    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 权限认证
        RolePopedom rolePopedom = method.getDeclaredAnnotation(RolePopedom.class);
        if(rolePopedom != null) {
            String authKey = rolePopedom.number();
            if(!MyUtils.isBlank(authKey)) {
                if (!accountService.isPermitOperation(authKey, request)) {
                    response.sendRedirect("/system/error/opfailed");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
