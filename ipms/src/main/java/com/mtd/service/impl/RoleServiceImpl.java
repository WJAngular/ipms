package com.mtd.service.impl;

import com.mtd.dao.RoleDao;
import com.mtd.entity.Sys_Role;
import com.mtd.service.RoleService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/21.
 */
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;
    protected SessionFactory sessionFactory;

    @Override
    public RoleDao getEntityDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao dao) {
        this.roleDao = dao;
    }

    public Sys_Role get(Integer id){ return (Sys_Role)roleDao.findByID(Sys_Role.class, id); }

    public void save(Object object){
        roleDao.save(object);
    }

    public void delete(Object object) {
        roleDao.delete(object);
    }

    public void update(Object object) {
        roleDao.update(object);
    }

    public List<Object> FindAll(String HQL) {
        return roleDao.FindAll(HQL);
    }

    public List<Object> findByPage(String hql, int pagesize, int offset) {
        return roleDao.findByPage(hql, pagesize, offset);
    }

    @SuppressWarnings("rawtypes")
    public List listByHQL(String hql) {
        return roleDao.listByHQL(hql);
    }

}
