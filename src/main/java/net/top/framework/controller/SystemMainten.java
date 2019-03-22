package net.top.framework.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.top.framework.domain.DataBack;
import net.top.framework.util.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/7/25
 * @version： V1.0
 */
@Controller
public class SystemMainten {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @RequestMapping(value = "system/mainten/del")
    public ModelAndView del(HttpServletRequest request, String name) {
        ModelAndView mv = new ModelAndView("redirect:./list");
        String path = request.getServletContext().getRealPath("/");
        path += System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack";
        path += System.getProperty("file.separator") + name;

        File file = new File(path);
        file.delete();
        return mv;
    }

    @RequestMapping(value = "system/mainten/list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/mainten/list");
        String path = request.getServletContext().getRealPath("/");
        List<File> filesList = FileUtils.getFilesList(path + System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack", ".sql");

        List<DataBack> infoList = new ArrayList<DataBack>();

        for(int i = 0; i < filesList.size(); i++) {
            DataBack in = new DataBack();
            in.setName(filesList.get(i).getName());

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filesList.get(i));
                in.setSize(fis.available());
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            infoList.add(in);
        }

        mv.addObject("entities", infoList);
        mv.addObject("totalRows", infoList.size());

        return mv;
    }

    @RequestMapping("system/mainten/down")
    public String download(String name, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + name);
        try {
            String path = request.getServletContext().getRealPath("/");
            path += System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack" + System.getProperty("file.separator") + name;
            InputStream inputStream = new FileInputStream(new File(path));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("system/mainten/toupload")
    public String toupload() {
        return "/system/mainten/upload";
    }

    @RequestMapping("system/mainten/upload")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/");
        path += System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack" + System.getProperty("file.separator");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:./list";
    }

    @RequestMapping("system/mainten/dbbackup")
    public String dbbackup(HttpServletRequest request) {
        /**
         * 加载config文件，读取数据库的用户名、密码、数据库名字
         */
        try {
            String cfgPath = request.getServletContext().getRealPath("/") + System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "config" + System.getProperty("file.separator") + "top_config.properties";

            FileInputStream fis;
            Properties p = new Properties();

            fis = new FileInputStream(cfgPath);
            p.load(fis);
            String url = p.getProperty("hibernate.connection.url");
            url = url.substring(url.indexOf(":") + 1);
            url = url.substring(url.indexOf(":") + 1);
            url = url.substring(url.indexOf(":") + 1);
            url = url.substring(url.indexOf("/") + 1);
            String dbName = url.substring(0, url.indexOf("?"));
            String dbUser = p.getProperty("hibernate.connection.username");
            String dbPwd = p.getProperty("hibernate.connection.password");

            fis.close();
            // 备份文件存放目录
            String dbBackupPath = request.getServletContext().getRealPath("/");
            dbBackupPath += System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack" + System.getProperty("file.separator");

            createDirIfNotExist(dbBackupPath);

            String contextPath = request.getSession().getServletContext().getRealPath("/");
            // 脚本存放目录
            String binHome = contextPath + "bin";
            createDirIfNotExist(binHome);

            String os = System.getProperty("os.name").toLowerCase();

            String file = "";

            if(os.startsWith("win")) {
                file = binHome + System.getProperty("file.separator") + "backup.bat";
            } else {
                file = binHome + System.getProperty("file.separator") + "backup.sh";
            }

            StringBuffer strBuff = new StringBuffer();
            strBuff.append(System.getProperty("line.separator"));
            // 切换工作目录
            if(os.startsWith("win")) {
                strBuff.append("pushd " + binHome);
            } else {
                strBuff.append("cd " + binHome);
            }
            strBuff.append(System.getProperty("line.separator"));// 换行

            // 得到文件名
            java.util.Date dt = new java.util.Date(System.currentTimeMillis());
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
            String backFileName = fmt.format(dt);
            backFileName = dbBackupPath + backFileName + ".sql";

            strBuff.append("mysqldump -u" + dbUser
                    + " -p" + dbPwd
                    + " " + dbName
                    + " > " + backFileName );

            strBuff.append(System.getProperty("line.separator"));// 换行
            strBuff.append("exit");

            PrintWriter pw = new PrintWriter(new FileOutputStream(file));

            pw.println(strBuff.toString());
            pw.flush();
            pw.close();

            if (os.startsWith("win")) {
                Process process = Runtime.getRuntime().exec("cmd /c start " + file);
                process.waitFor();
                Thread.sleep(1000);
            } else {
                String[] cmd = {"/bin/sh", "-c", "chmod +x " + file};
                Runtime.getRuntime().exec(cmd);
                Process process = Runtime.getRuntime().exec(file);
                process.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:./list";
    }

    @RequestMapping("system/mainten/dbrestore")
    public String dbrestore(HttpServletRequest request, String name) {
        /**
         * 加载config文件，读取数据库的用户名、密码、数据库名字
         */
        try {
            String cfgPath = request.getServletContext().getRealPath("/") + System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "config" + System.getProperty("file.separator") + "top_config.properties";

            FileInputStream fis;
            Properties p = new Properties();

            fis = new FileInputStream(cfgPath);
            p.load(fis);
            String url = p.getProperty("hibernate.connection.url");
            url = url.substring(url.indexOf(":") + 1);
            url = url.substring(url.indexOf(":") + 1);
            url = url.substring(url.indexOf(":") + 1);
            url = url.substring(url.indexOf("/") + 1);
            String dbName = url.substring(0, url.indexOf("?"));
            String dbUser = p.getProperty("hibernate.connection.username");
            String dbPwd = p.getProperty("hibernate.connection.password");

            fis.close();
            // 备份文件存放目录
            String dbBackupPath = request.getServletContext().getRealPath("/");
            dbBackupPath += System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack" + System.getProperty("file.separator");

            String contextPath = request.getSession().getServletContext().getRealPath("/");
            // 脚本存放目录
            String binHome = contextPath + "bin";
            createDirIfNotExist(binHome);

            String os = System.getProperty("os.name").toLowerCase();

            String file = "";

            if(os.startsWith("win")) {
                file = binHome + System.getProperty("file.separator") + "restore.bat";
            } else {
                file = binHome + System.getProperty("file.separator") + "restore.sh";
            }

            StringBuffer strBuff = new StringBuffer();
            strBuff.append(System.getProperty("line.separator"));
            // 切换工作目录
            if(os.startsWith("win")) {
                strBuff.append("pushd " + binHome);
            } else {
                strBuff.append("cd " + binHome);
            }
            strBuff.append(System.getProperty("line.separator"));// 换行

            strBuff.append("mysql -u" + dbUser
                    + " -p" + dbPwd
                    + " " + dbName
                    + " < " + dbBackupPath + System.getProperty("file.separator") + name);

            strBuff.append(System.getProperty("line.separator"));// 换行
            strBuff.append("exit");

            PrintWriter pw = new PrintWriter(new FileOutputStream(file));

            pw.println(strBuff.toString());
            pw.flush();
            pw.close();

            if (os.startsWith("win")) {
                Process process = Runtime.getRuntime().exec("cmd /c start " + file);
                process.waitFor();
                Thread.sleep(1000);
            } else {
                String[] cmd = {"/bin/sh", "-c", "chmod +x " + file};
                Runtime.getRuntime().exec(cmd);
                Process process = Runtime.getRuntime().exec(file);
                process.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:./list";
    }

    @RequestMapping("system/mainten/clearAll")
    public String clearAll(HttpServletRequest request) {
        String dbBackupPath = request.getServletContext().getRealPath("/");
        dbBackupPath += System.getProperty("file.separator") + "WEB-INF" + System.getProperty("file.separator") + "databack" + System.getProperty("file.separator");
        delFielByPath(dbBackupPath);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:./list";
    }

    /**
     * 删除制定路径下的所有文件
     */
    public static void delFielByPath(String pathStr){
        try {
            System.out.println("delFielByPath:"+pathStr);
            File file = new File(pathStr);
            if(file.isDirectory()){
                File[] files = file.listFiles();
                System.out.println("files.length:"+files.length);
                if(files!=null && files.length>0){
                    for(int i=0;i<files.length;i++){
                        System.out.println("files[i]:"+files[i].toString());
                        if(files[i].isFile())
                            System.out.println("files[i].delete():"+files[i].delete());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     */
    public static void createDirIfNotExist(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

}
