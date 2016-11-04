package com.mtd.dao.impl;

import com.mtd.dao.RolePermissionDao;
import com.mtd.entity.Sys_Permission;
import com.mtd.entity.Sys_Role;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/18.
 */
public class RolePermissionDaoImpl implements RolePermissionDao{

    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Object object) {
        sessionFactory.getCurrentSession().save(object);
    }

    public void delete(Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    public void update(Object object) {
        sessionFactory.getCurrentSession().update(object);
    }

    /**
     * 查询角色拥有的权限id
     * @param roleId
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Integer> findPermissionIds(Integer roleId){
        String hql="select rp.permission.id from Sys_RolePermission rp where rp.role.id=:roleId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("roleId", roleId);
        return query.list();
    }

    public List<Sys_Permission> findPermissions(Integer roleId){
        String hql="from Sys_RolePermission rp where rp.role.id=:roleId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("roleId", roleId);
        return query.list();
    }

    public List<Sys_Role> findRolesWithPermission(Integer permissionId){
        String hql="from Sys_RolePermission rp where rp.permission.id=:permissionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("permissionId", permissionId);
        return query.list();
    }

    /**
     * 删除角色权限
     * @param roleId
     * @param permissionId
     */
    public void deleteRP(Integer roleId,Integer permissionId){
        String hql="delete Sys_RolePermission rp where rp.role.id=:roleId and rp.permission.id=:permissionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("roleId", roleId);
        query.setParameter("permissionId", permissionId);
        query.executeUpdate();
    }

}
