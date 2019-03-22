package net.top.framework.service;

import net.top.framework.dao.BaseDao;
import net.top.framework.domain.OpLog;
import net.top.framework.util.MyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

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
public class LogServiceImpl implements LogService{

    @Autowired
    private BaseDao baseDao;

    @Override
    public void add(OpLog opLog) {
        baseDao.save(opLog);
    }

    @Override
    public List<OpLog> list(Date startTime, Date endTime, Integer pageStartNo, Integer pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(OpLog.class);

        if(!MyUtils.isBlank(startTime)) {
            criteria.add(Restrictions.ge("opTime", startTime));
        }

        if(!MyUtils.isBlank(endTime)) {
            criteria.add(Restrictions.le("opTime", endTime));
        }

        criteria.addOrder(Order.desc("id"));

        return (List<OpLog>) this.baseDao.findByCriteria(criteria, pageStartNo, pageSize);
    }

    @Override
    public long count(Date startTime, Date endTime) {
        String queryStr = "select count(r.id) from OpLog r where 1=1 ";
        if(!MyUtils.isBlank(startTime)) {
            queryStr += " and r.opTime>=?";
        }
        if(!MyUtils.isBlank(endTime)) {
            queryStr += " and r.opTime<=?";
        }

        return baseDao.count(queryStr, new Object[]{startTime, endTime});
    }

    @Override
    public void truncate() {
        baseDao.executeUpdate("truncate table sys_op_log");
    }
}
