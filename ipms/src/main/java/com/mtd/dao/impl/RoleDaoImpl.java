package com.mtd.dao.impl;

import com.mtd.dao.RoleDao;
import com.mtd.entity.Sys_Role;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/21.
 */
public class RoleDaoImpl implements RoleDao{
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Object object){
        sessionFactory.getCurrentSession().save(object);
    }

    public void delete(Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    public void update(Object object) {
        sessionFactory.getCurrentSession().update(object);
    }

    public Object findByID(Class<?> clazz, Integer id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    public List<Object> FindAll(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List listByHQL(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public Sys_Role getEntity(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        try{
            return (Sys_Role)query.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Object> findByPage(String hql, int pagesize, int offset) {
        List<Object> list = sessionFactory.getCurrentSession().createQuery(hql)
                .setFirstResult((offset-1)*pagesize).setMaxResults(pagesize).list();
        return list;
    }

}
