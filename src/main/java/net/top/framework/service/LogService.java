package net.top.framework.service;

import net.top.framework.domain.OpLog;

import java.util.Date;
import java.util.List;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/7/15
 * @version： V1.0
 */
public interface LogService {
    /**
     *
     * @param opLog
     */
    void add(OpLog opLog);

    /**
     *
     * @param startTime
     * @param endTime
     * @param pageStartNo
     * @param pageSize
     * @return
     */
    List<OpLog> list(Date startTime, Date endTime, Integer pageStartNo, Integer pageSize);

    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    long count(Date startTime, Date endTime);


    /**
     * 彻底清空log表
     */
    void truncate();
}
