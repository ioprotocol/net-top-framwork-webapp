package net.top.framework.controller;

import net.top.framework.domain.Account;
import net.top.framework.domain.OpLog;
import net.top.framework.service.AccountService;
import net.top.framework.service.LogService;
import net.top.framework.util.JsonUtil;
import net.top.framework.util.MyUtils;
import net.top.framework.webform.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/7/20
 * @version： V1.0
 */
@Controller
public class SysLogAction {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private LogService logService;
    @Autowired
    private AccountService accountService;

    public SysLogAction() {
    }

    @RequestMapping(value = "system/oplog/list")
    @ResponseBody
    public String list(String startTime, String endTime, Integer draw, Integer start, Integer length) {
        if(start == null)
            start = 0;
        if(length == null)
            length = 10;
        List<OpLog> list = null;
        long totalRows = 0;
        try {
            Date startDate = null;
            Date endDate = null;
            if(startTime == null || startTime.length() < 1) {
                startDate = new Date(System.currentTimeMillis() - 7*24*3600*1000L);
            } else {
                startDate = sdf.parse(startTime);
            }
            if(endTime == null || endTime.length() < 1) {
                endDate = new Date();
            } else {
                endDate = sdf.parse(endTime);
            }

            list = logService.list(startDate, endDate, start, length);
            totalRows = logService.count(startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonUtil.objectToJsonString(new DataTable(draw, totalRows, list));
    }
}
