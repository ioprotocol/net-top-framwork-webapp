package net.top.framework.controller;

import net.top.framework.domain.AppLogConfig;
import net.top.framework.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Controller
public class PostFileTestAction {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static AppLogConfig appLogConfig = null;

    @RequestMapping("/uhome/acbiz/data")
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        appLogConfig = loadConfig(request);

        if(!file.getOriginalFilename().contains("-")) {
            return JsonUtil.objectToJsonString("file name format is not conform");
        }

        String userId = file.getOriginalFilename().split("-")[0];

        if(file.getSize() > appLogConfig.getAllLogPermittedMaxSize()*1024) {
            return JsonUtil.objectToJsonString("file size too big,server reject");
        }

        // mset取值userId
        long lastRequstTime = 0L;
        long firstRequestTime = 0L;
        // 频率限制
        if(appLogConfig.getAllLogLimitPeriod() != 0) {
            if(System.currentTimeMillis() - lastRequstTime < appLogConfig.getAllLogLimitPeriod()*1000L) {
                return JsonUtil.objectToJsonString("request to frequency");
            }
        }

        // 次数限制
        if(System.currentTimeMillis() - firstRequestTime < appLogConfig.getAllLogCountCircle()*1000) {

            int requestTimes = 0;
            if(requestTimes > appLogConfig.getAllLogPermitNum()) {
                return JsonUtil.objectToJsonString("超过规定时间内的次数");
            } else {
                // mset(userid_requestTimes, requestTimes+1);
            }
        } else {
            // mset(userid_requestTimes, 1);
            // mset(userid_firstRequestTime, System.currentTimeMillis());
        }
        // mset(userid_firstRequestTime, System.currentTimeMillis());


        String backUpPath = appLogConfig.getAllLogPath() + System.getProperty("file.separator") + sdf.format(new Date());

        String fileName = file.getOriginalFilename();
        File targetFile = new File(backUpPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonUtil.objectToJsonString(file.getOriginalFilename());
    }

    /**
     *
     * @param request
     * @return
     */
    private AppLogConfig loadConfig(HttpServletRequest request) throws IOException {
        if(appLogConfig == null) {
            ServletContext context = request.getServletContext();
            String realPath = context.getRealPath("WEB-INF/classes/appLogFile.properties");
            FileInputStream fis = new FileInputStream(realPath);
            Properties pt = new Properties();
            pt.load(fis);

            appLogConfig = new AppLogConfig();

            appLogConfig.setSimpleLogCountCircle(Integer.parseInt(pt.getProperty("simpleLogCountCircle", "3600")));
            appLogConfig.setSimpleLogPermitNum(Integer.parseInt(pt.getProperty("simpleLogPermitNum", "3")));
            appLogConfig.setSimpleLogLimitPeriod(Integer.parseInt(pt.getProperty("simpleLogLimitPeriod", "0")));
            appLogConfig.setAllLogPath(pt.getProperty("allLogPath", "/export/logs/appuploadlog"));
            appLogConfig.setAllLogPermittedMaxSize(Integer.parseInt(pt.getProperty("allLogPermittedMaxSize", "5120")));
            appLogConfig.setAllLogCountCircle(Integer.parseInt(pt.getProperty("allLogCountCircle", "86400")));
            appLogConfig.setAllLogPermitNum(Integer.parseInt(pt.getProperty("allLogPermitNum", "1")));
            appLogConfig.setAllLogLimitPeriod(Integer.parseInt(pt.getProperty("allLogLimitPeriod", "0")));
        }

        return appLogConfig;
    }
}
