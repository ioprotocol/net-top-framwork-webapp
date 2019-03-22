package net.top.framework.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.dao.hibernate
 * @Description:
 * @author: xsy
 * @date： 2016/5/27
 * @version： V1.0
 */
public interface BaseDao {

    /**
     * 批量事务插入
     * @param entities
     */
    <T> void batchInsert(T... entities) throws Exception;

    /**
     *
     * @param entities
     * @param <T>
     */
    <T> void batchSaveOrUpdate(T... entities) throws Exception;

    /**
     * 保存
     * @param entity
     * @return
     */
    Serializable save(Object entity);

    /**
     *
     * @param entity
     */
    void saveOrUpdate(Object entity);

    /**
     *
     * @param entity
     */
    void update(Object entity);

    /**
     *
     * @param entity
     */
    void delete(Object entity);

    /**
     * 延迟加载
     * @param entityClass
     * @param id
     * @return
     */
    <T> T load(Class<T> entityClass, Serializable id);

    /**
     *
     * @param entityClass
     * @return
     */
    <T> List<T> loadAll(Class<T> entityClass);

    /**
     *
     * @param entityClass
     * @param id
     * @return
     */
    <T> T get(Class<T> entityClass, Serializable id);

    /**
     *
     * @param entities
     */
    void deleteAll(Collection<?> entities);

    /**
     *
     * @param entity
     */
    <T> T merge(T entity);

    /**
     *
     * @param criteria
     * @return
     */
    List<?> findByCriteria(DetachedCriteria criteria);

    /**
     *
     * @param criteria
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<?> findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults);

    /**
     *
     * @param queryString
     * @param paramName
     * @param value
     * @return
     */
    List<?> findByNamedParam(String queryString, String paramName, Object value);

    /**
     *
     * @param queryString
     * @param paramName
     * @param value
     * @return
     */
    List<?> findByNamedParam(String queryString, String[] paramName, Object[] value);


    /**
     *
     * @param query
     * @param objects
     * @return
     */
    List<?> find(String query, Object... objects);
    /**
     *
     * @param queryString
     * @param values
     * @param startNo
     * @param pageSize
     * @return
     */
    List<?> find(String queryString, Object[] values, int startNo, int pageSize);

    /**
     * 统计记录总数
     * @param queryString
     * @param values
     * @return
     */
    long count(String queryString, Object[] values);

    /**
     * 批量操作
     * @param queryString
     * @param values
     */
    int bulkUpdate(String queryString, Object[] values);

    /**
     * 查找某一个对象
     * @param queryString
     * @param values
     * @return
     */
    Object singleResult(String queryString, Object[] values);

    /**
     * 非select类 sql操作
     * @param sql
     */
    void executeUpdate(String sql);

    /**
     * 执行select类 sql操作
     * @param sql
     */
    <T> List<T> executeSelect(String sql);
}
