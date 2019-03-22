package net.top.framework.service;

import net.top.framework.dao.BaseDao;
import net.top.framework.domain.Resource;
import net.top.framework.util.MyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/26
 * @version： V1.0
 */
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private BaseDao baseDao;

    @Override
    public List<Resource> listByTypeId(Integer id, Integer pageStartNo, Integer pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
        if(!MyUtils.isBlank(id)) {
            criteria.add(Restrictions.eq("resourceTypeId", id));
        } else {
            criteria.addOrder(Order.asc("resourceTypeId"));
        }
        criteria.addOrder(Order.asc("order"));
        return (List<Resource>) baseDao.findByCriteria(criteria, pageStartNo, pageSize);
    }

    @Override
    public long count(Integer typeId) {
        String query = "select count(r.id) from Resource r ";
        if(!MyUtils.isBlank(typeId)) {
            query += " where r.resourceTypeId=" + typeId;
        }
        return baseDao.count(query, null);
    }

    @Override
    public Resource load(String number) {
        return baseDao.load(Resource.class, number);
    }

    @Override
    public void saveOrUpdate(Resource resource) {
        baseDao.saveOrUpdate(resource);
    }

    @Override
    public void del(String number) {
        String query = " delete from Resource where number=?";
        baseDao.bulkUpdate(query, new String[]{number});
        query = "delete from RolePopedom where resourceNumber=?";
        baseDao.bulkUpdate(query, new String[]{number});
    }
}
