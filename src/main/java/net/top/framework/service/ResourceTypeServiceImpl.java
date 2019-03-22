package net.top.framework.service;

import net.top.framework.dao.BaseDao;
import net.top.framework.domain.ResourceType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/13
 * @version： V1.0
 */
public class ResourceTypeServiceImpl implements ResourceTypeService {
    @Autowired
    private BaseDao baseDao;

    @Override
    public List<ResourceType> listAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(ResourceType.class);
        criteria.addOrder(Order.asc("order"));
        return (List<ResourceType>) baseDao.findByCriteria(criteria);
    }

    @Override
    public ResourceType load(Integer id) {
        return baseDao.load(ResourceType.class, id);
    }

    @Override
    public Serializable save(ResourceType resourceType) {
        return baseDao.save(resourceType);
    }

    @Override
    public void update(ResourceType resourceType) {
        baseDao.update(resourceType);
    }

    @Override
    public void del(Integer id) {
        baseDao.delete(baseDao.load(ResourceType.class, id));
    }
}
