package net.top.framework.interceptor;

import net.top.framework.annocations.LogAnnocation;
import net.top.framework.domain.Account;
import net.top.framework.domain.OpLog;
import net.top.framework.service.AccountService;
import net.top.framework.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.interceptor
 * @Description:
 * @author: xsy
 * @date： 2016/7/15
 * @version： V1.0
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AccountService accountService;
    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 日志记录
            LogAnnocation logAnnocation = method.getDeclaredAnnotation(LogAnnocation.class);
            if (logAnnocation != null) {
                Account account = accountService.getLoginAccount(request);
                if(account != null) {
                    OpLog log = new OpLog();
                    log.setAccount(account.getAccount());
                    log.setName(account.getName());
                    log.setRoleName(account.getRole().getName());
                    log.setOpName(logAnnocation.opName());
                    log.setOpDescription(logAnnocation.opDescription());
                    log.setOpTime(new Date());
                    log.setOpIp(request.getRemoteAddr());
                    logService.add(log);
                }
            }
        }
    }
}
