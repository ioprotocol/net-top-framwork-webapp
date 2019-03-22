package net.top.framework.service;

import net.top.framework.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Project:net-top-app-web
 * @Package net.top.app.web.service
 * @Description:
 * @author: xsy
 * @date： 2016/8/30
 * @version： V1.0
 */
public class BaseServiceImpl implements BaseService{

    @Autowired
    private BaseDao baseDao;

    @Override
    public <T> void batchInsert(T... entities) throws Exception {
        baseDao.batchInsert(entities);
    }

    @Override
    public <T> void batchSaveOrUpdate(T... entities) throws Exception {
        baseDao.batchSaveOrUpdate(entities);
    }

    @Override
    public Serializable save(Object entity) {
        return baseDao.save(entity);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        baseDao.saveOrUpdate(entity);
    }

    @Override
    public void update(Object entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(Object entity) {
        baseDao.delete(entity);
    }

    @Override
    public <T> T load(Class<T> entityClass, Serializable id) {
        return baseDao.load(entityClass, id);
    }

    @Override
    public <T> List<T> loadAll(Class<T> entityClass) {
        return baseDao.loadAll(entityClass);
    }

    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public void deleteAll(Collection<?> entities) {
        baseDao.deleteAll(entities);
    }

    @Override
    public <T> T merge(T entity) {
        return baseDao.merge(entity);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria criteria) {
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
        return baseDao.findByCriteria(criteria, firstResult, maxResults);
    }

    @Override
    public List<?> findByNamedParam(String queryString, String paramName, Object value) {
        return baseDao.findByNamedParam(queryString, paramName, value);
    }

    @Override
    public List<?> findByNamedParam(String queryString, String[] paramName, Object[] value) {
        return baseDao.findByNamedParam(queryString, paramName, value);
    }

    @Override
    public List<?> find(String query, Object... objects) {
        return baseDao.find(query, objects);
    }

    @Override
    public List<?> find(String queryString, Object[] values, int startNo, int pageSize) {
        return baseDao.find(queryString, values, startNo, pageSize);
    }

    @Override
    public long count(String queryString, Object[] values) {
        return baseDao.count(queryString, values);
    }

    @Override
    public int bulkUpdate(String queryString, Object[] values) {
        return baseDao.bulkUpdate(queryString, values);
    }

    @Override
    public Object singleResult(String queryString, Object[] values) {
        return baseDao.singleResult(queryString, values);
    }

    @Override
    public void executeUpdate(String sql) {
        baseDao.executeUpdate(sql);
    }

    @Override
    public void executeSelect(String sql) {
        baseDao.executeSelect(sql);
    }
}
