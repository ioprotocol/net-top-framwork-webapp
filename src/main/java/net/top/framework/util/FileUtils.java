package net.top.framework.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.util
 * @Description:
 * @author: xsy
 * @date： 2016/7/25
 * @version： V1.0
 */
public class FileUtils {
    /**
     * TODO 根据路径和文件类型得到该路径下文件名集合
     * @param path
     * @param fileType
     * @return
     */
    public static List<File> getFilesList(String path, String fileType) {
        List<File> filesList = new ArrayList<File>();
        File f = new File(path);
        if (f.exists()) {
            if (f.listFiles() != null && f.listFiles().length > 0) {
                File[] fileArray = f.listFiles();
                for (int i = f.listFiles().length - 1; i >= 0; i--) {
                    String[] tmp = fileArray[i].getName().split("\\.");
                    String ext = tmp.length >= 2 ? "." + tmp[1] : null;
                    if (ext != null && ext.equalsIgnoreCase(fileType.trim())) {
                        filesList.add(fileArray[i]);
                    }
                }
            }
        }
        return filesList;
    }
}
