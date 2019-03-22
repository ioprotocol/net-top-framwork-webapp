package net.top.framework.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import net.top.framework.annocations.LogAnnocation;
import net.top.framework.domain.Account;
import net.top.framework.domain.OpLog;
import net.top.framework.service.AccountService;
import net.top.framework.service.LogService;
import net.top.framework.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Map;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
@Controller
public class SysLoginAction {
    @Autowired
    private Producer captchaProducer = null;
    @Autowired
    private AccountService accountService;

    public SysLoginAction() {
    }

    @RequestMapping(value = "system/user/auth", method = RequestMethod.POST)
    @ResponseBody
    public String auth(HttpServletRequest request, String json) {
        Map<String, String> form = JsonUtil.jsonToObject(json, Map.class);

        String authcodeServer = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if(!authcodeServer.equalsIgnoreCase(form.get("authcode"))) {
            return "1";
        }

        if(!accountService.authLogin(form.get("account"), form.get("password"), request)) {
            return "2";
        }

        return "0";
    }

    @RequestMapping(value = "system/user/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        accountService.logout(request);
        return "0";
    }

    @RequestMapping("system/user/safecode")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
}
