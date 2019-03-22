package net.top.framework.dao.hibernate;

import net.top.framework.dao.BaseDao;
import net.top.framework.util.MyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

    @Override
    public <T> void batchInsert(T... entities) throws Exception {
        HibernateTransactionManager manager = new HibernateTransactionManager(getSessionFactory());
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("batchInsertObject");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = manager.getTransaction(def);
        try {
            // execute your business logic here
            for(Object o : entities) {
                getSessionFactory().getCurrentSession().save(o);
            }
        }
        catch (Exception ex) {
            manager.rollback(status);
            throw ex;
        }
        manager.commit(status);
    }

    @Override
    public <T> void batchSaveOrUpdate(T... entities) throws Exception {
        HibernateTransactionManager manager = new HibernateTransactionManager(getSessionFactory());
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("batchSaveOrUpdateObject");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = manager.getTransaction(def);
        try {
            // execute your business logic here
            for(Object o : entities) {
                getSessionFactory().getCurrentSession().saveOrUpdate(o);
            }
        }
        catch (Exception ex) {
            manager.rollback(status);
            throw ex;
        }
        manager.commit(status);
    }

    @Override
    public Serializable save(Object entity) {
        return getHibernateTemplate().save(entity);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void update(Object entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void delete(Object entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public <T> T load(Class<T> entityClass, Serializable id) {
        return getHibernateTemplate().load(entityClass, id);
    }

    @Override
    public <T> List<T> loadAll(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public void deleteAll(Collection<?> entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    @Override
    public <T> T merge(T entity) {
        return getHibernateTemplate().merge(entity);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
        return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
    }

    @Override
    public List<?> find(String queryString, Object... objects) {
        return getHibernateTemplate().find(queryString, objects);
    }

    @Override
    public List<?> findByNamedParam(String queryString, String paramName, Object value) {
        return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
    }

    @Override
    public List<?> findByNamedParam(String queryString, String[] paramName, Object[] value) {
        return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
    }

    @Override
    public List<?> find(String queryString, Object[] values, int startNo, int pageSize) {
        Query query = currentSession().createQuery(queryString);
        if(!MyUtils.isBlank(values)) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.setFirstResult(startNo);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public long count(String queryString, Object[] values) {
        Query query = currentSession().createQuery(queryString);
        if(!MyUtils.isBlank(values)) {
            int j = 0;
            for (int i = 0; i < values.length; i++) {
                if(values[i] != null)
                    query.setParameter(j++, values[i]);
            }
        }
        return ((Long)query.uniqueResult()).longValue();
    }

    @Override
    public int bulkUpdate(String queryString, Object[] values) {
        return getHibernateTemplate().bulkUpdate(queryString, values);
    }

    @Override
    public Object singleResult(String queryString, Object[] values) {
        Query query = currentSession().createQuery(queryString);
        if(!MyUtils.isBlank(values)) {
            int j = 0;
            for (int i = 0; i < values.length; i++) {
                if(values[i] != null)
                    query.setParameter(j++, values[i]);
            }
        }
        return query.uniqueResult();
    }

    @Override
    public void executeUpdate(final String sql) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery(sql).executeUpdate();
                return null;
            }
        });
    }

    @Override
    public <T> List<T> executeSelect(final String sql) {
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }
}
