package com.mtd.service;

import com.mtd.dao.RoleDao;
import com.mtd.entity.Sys_Role;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/21.
 */
public interface RoleService {
    public RoleDao getEntityDao();

    public Sys_Role get(Integer id);

    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    public List<Object> FindAll(String HQL) ;

    public List<Object> findByPage(String hql,int pagesize,int offset);

    public List listByHQL(String hql) ;
}
