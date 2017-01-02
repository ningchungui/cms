package org.yxm.cms.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.yxm.cms.model.Pager;
import org.yxm.cms.model.SystemContext;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yxm on 2016.12.10.
 */
public class BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Class<?> clz;

    public Class<?> getClz() {
        if (clz == null) {
            clz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }

        return clz;
    }

    // 增删改公有方法
    public T load(Integer id) {
        return (T) this.getSession().load(getClz(), id);
    }

    public T get(Integer id) {
        return (T) this.getSession().get(getClz(), id);
    }

    public T add(T t) {
        getSession().save(t);
        return t;
    }

    public void delete(Integer id) {
        getSession().delete(this.load(id));
    }

    public void update(T t) {
        getSession().update(t);
    }

    public List<T> listAll() {
        return this.list("from " + getClz().getSimpleName());
    }

    public Pager<T> findAll() {
        return this.find("from " + getClz().getSimpleName());
    }

    // list hql 不带分页
    public List<T> list(String hql) {
        return list(hql, null, null);
    }

    public List<T> list(String hql, Object arg) {
        return list(hql, new Object[]{arg}, null);
    }

    public List<T> list(String hql, Object[] args) {
        return list(hql, args, null);
    }

    public List<T> list(String hql, Map<String, Object> alias) {
        return list(hql, null, alias);
    }

    public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql);
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.list();
    }

    // find hql带分页
    public Pager<T> find(String hql) {
        return find(hql, null, null);
    }

    public Pager<T> find(String hql, Object arg) {
        return find(hql, new Object[]{arg}, null);
    }

    public Pager<T> find(String hql, Object[] args) {
        return find(hql, args, null);
    }

    public Pager<T> find(String hql, Map<String, Object> alias) {
        return find(hql, null, alias);
    }


    public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql);

        String cq = getCountHql(hql, true);
        Query cquery = getSession().createQuery(cq);
        Query query = getSession().createQuery(hql);

        setAliasParameter(query, alias);
        setAliasParameter(cquery, alias);

        setParameter(query, args);
        setParameter(cquery, args);

        Pager<T> pages = new Pager<T>();
        setPagers(query, pages);
        List<T> datas = query.list();
        pages.setDatas(datas);
        long total = (long) cquery.uniqueResult();
        pages.setTotal(total);
        return pages;
    }

    // list sql 不带分页
    public <N extends Object> List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, null, clz, hasEntity);
    }

    public <N extends Object> List<N> listBySql(String sql, Object[] args, Class<?> clz,
                                                boolean hasEntity) {
        return this.listBySql(sql, args, null, clz, hasEntity);
    }

    public <N extends Object> List<N> listBySql(String sql, Object arg, Class<?> clz,
                                                boolean hasEntity) {
        return this.listBySql(sql, new Object[]{arg}, clz, hasEntity);
    }

    public <N extends Object> List<N> listBySql(String sql, Map<String, Object> alias,
                                                Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, alias, clz, hasEntity);
    }

    public <N extends Object> List<N> listBySql(String sql, Object[] args,
                                                Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        sql = initSort(sql);
        SQLQuery sq = getSession().createSQLQuery(sql);
        setAliasParameter(sq, alias);
        setParameter(sq, args);
        if (hasEntity) {
            sq.addEntity(clz);
        } else
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        return sq.list();
    }

    // find sql 带分页
    public <N extends Object> Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, null, null, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Class<?> clz,
                                                 boolean hasEntity) {
        return this.findBySql(sql, args, null, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findBySql(String sql, Object arg, Class<?> clz,
                                                 boolean hasEntity) {
        return this.findBySql(sql, new Object[]{arg}, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findBySql(String sql, Map<String, Object> alias,
                                                 Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, null, alias, clz, hasEntity);
    }


    public <N extends Object> Pager<N> findBySql(String sql, Object[] args,
                                                 Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        sql = initSort(sql);
        String cq = getCountHql(sql, false);
        SQLQuery sq = getSession().createSQLQuery(sql);
        SQLQuery cquery = getSession().createSQLQuery(cq);
        setAliasParameter(sq, alias);
        setAliasParameter(cquery, alias);
        setParameter(sq, args);
        setParameter(cquery, args);
        Pager<N> pages = new Pager<N>();
        setPagers(sq, (Pager<T>) pages);
        if (hasEntity) {
            sq.addEntity(clz);
        } else {
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        }
        List<N> datas = sq.list();
        pages.setDatas(datas);
        long total = ((BigInteger) cquery.uniqueResult()).longValue();
        pages.setTotal(total);
        return pages;
    }

    // 查询单个对象
    public Object queryObject(String hql) {
        return this.queryObject(hql, null, null);
    }

    public Object queryObject(String hql, Object[] args) {
        return this.queryObject(hql, args, null);
    }

    public Object queryObject(String hql, Object arg) {
        return this.queryObject(hql, new Object[]{arg}, null);
    }

    public Object queryObject(String hql, Map<String, Object> alias) {
        return this.queryObject(hql, null, alias);
    }

    public Object queryObject(String hql, Object[] args,
                              Map<String, Object> alias) {
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.uniqueResult();
    }

    // 构建查询语句公共方法
    private String initSort(String hql) {
        String order = SystemContext.getOrder();
        String sort = SystemContext.getSort();
        if (sort != null && !"".equals(sort.trim())) {
            hql += " order by " + sort;
            if (!"desc".equals(order)) hql += " asc";
            else hql += "desc";
        }
        return hql;
    }

    private void setAliasParameter(Query query, Map<String, Object> alias) {
        if (alias != null) {
            Set<String> keys = alias.keySet();
            for (String key : keys) {
                Object val = alias.get(key);
                if (val instanceof Collection) {
                    query.setParameterList(key, (Collection) val);
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    private void setParameter(Query query, Object[] args) {
        if (args != null && args.length > 0) {
            int index = 0;
            for (Object arg : args) {
                query.setParameter(index++, arg);
            }
        }
    }

    private void setPagers(Query query, Pager<T> pages) {
        Integer pageSize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();
        if (pageOffset == null || pageOffset < 0) pageOffset = 0;
        if (pageSize == null || pageSize < 0) pageSize = Pager.DEFAULT_PAGE_SIZE;
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        query.setFirstResult(pageOffset).setMaxResults(pageSize);
    }

    private String getCountHql(String hql, boolean isHql) {
        String e = hql.substring(hql.indexOf("from"));
        String c = " select count(*) " + e;
        if (isHql) {
            c = c.replaceAll("fetch", "");
        }
        return c;
    }

}
