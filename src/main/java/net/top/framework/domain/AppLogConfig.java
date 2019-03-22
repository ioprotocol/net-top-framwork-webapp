package net.top.framework.domain;

public class AppLogConfig {
    // 简易log计数周期---时间单位为秒
    private Integer simpleLogCountCircle;
    // 在计数周期内允许上传的次数
    private Integer simpleLogPermitNum;
    // 上传间隔限制-- 默认0,不限制上传间隔时间,单位为秒
    private Integer simpleLogLimitPeriod;
    // 全量log文件存储位置
    private String allLogPath;
    // 全量log文件最大上传大小5x1024kb
    private Integer allLogPermittedMaxSize;
    // 全量log文件计数周期-- 时间单位为秒,默认1天只允许传输一次
    private Integer allLogCountCircle;
    // 全量log在一个计数周期内允许上传的最大次数
    private Integer allLogPermitNum;
    // 全量log每次上传的时间间隔限制
    private Integer allLogLimitPeriod;

    public Integer getSimpleLogCountCircle() {
        return simpleLogCountCircle;
    }

    public void setSimpleLogCountCircle(Integer simpleLogCountCircle) {
        this.simpleLogCountCircle = simpleLogCountCircle;
    }

    public Integer getSimpleLogPermitNum() {
        return simpleLogPermitNum;
    }

    public void setSimpleLogPermitNum(Integer simpleLogPermitNum) {
        this.simpleLogPermitNum = simpleLogPermitNum;
    }

    public Integer getSimpleLogLimitPeriod() {
        return simpleLogLimitPeriod;
    }

    public void setSimpleLogLimitPeriod(Integer simpleLogLimitPeriod) {
        this.simpleLogLimitPeriod = simpleLogLimitPeriod;
    }

    public String getAllLogPath() {
        return allLogPath;
    }

    public void setAllLogPath(String allLogPath) {
        this.allLogPath = allLogPath;
    }

    public Integer getAllLogPermittedMaxSize() {
        return allLogPermittedMaxSize;
    }

    public void setAllLogPermittedMaxSize(Integer allLogPermittedMaxSize) {
        this.allLogPermittedMaxSize = allLogPermittedMaxSize;
    }

    public Integer getAllLogCountCircle() {
        return allLogCountCircle;
    }

    public void setAllLogCountCircle(Integer allLogCountCircle) {
        this.allLogCountCircle = allLogCountCircle;
    }

    public Integer getAllLogPermitNum() {
        return allLogPermitNum;
    }

    public void setAllLogPermitNum(Integer allLogPermitNum) {
        this.allLogPermitNum = allLogPermitNum;
    }

    public Integer getAllLogLimitPeriod() {
        return allLogLimitPeriod;
    }

    public void setAllLogLimitPeriod(Integer allLogLimitPeriod) {
        this.allLogLimitPeriod = allLogLimitPeriod;
    }

    @Override
    public String toString() {
        return "AppLogConfig{" +
                "simpleLogCountCircle=" + simpleLogCountCircle +
                ", simpleLogPermitNum=" + simpleLogPermitNum +
                ", simpleLogLimitPeriod=" + simpleLogLimitPeriod +
                ", allLogPath='" + allLogPath + '\'' +
                ", allLogPermittedMaxSize=" + allLogPermittedMaxSize +
                ", allLogCountCircle=" + allLogCountCircle +
                ", allLogPermitNum=" + allLogPermitNum +
                ", allLogLimitPeriod=" + allLogLimitPeriod +
                '}';
    }
}
