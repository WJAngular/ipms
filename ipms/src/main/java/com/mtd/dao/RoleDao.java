package com.mtd.dao;

import com.mtd.entity.Sys_Role;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/21.
 */
public interface RoleDao {
    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    public Object findByID(Class<?> clazz, Integer id);

    public List<Object> FindAll(String HQL) ;
    public List listByHQL(String hql);
    public Sys_Role getEntity(String hql);
    public List<Object> findByPage(String hql,int pagesize,int offset);
}
