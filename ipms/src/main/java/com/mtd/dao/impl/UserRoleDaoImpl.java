package com.mtd.dao.impl;

import com.mtd.dao.UserRoleDao;
import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_UserRole;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/18.
 */
public class UserRoleDaoImpl implements UserRoleDao {

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
     * 删除用户角色
     *
     * @param userId
     * @param roleId
     */
    public void deleteUR(Integer userId, Integer roleId) {
        String hql = "delete Sys_UserRole ur where ur.user.id=:userId and ur.role.id=:roleId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("roleId", roleId);
        query.executeUpdate();
    }

    /**
     * 查询用户拥有的角色id集合
     *
     * @param userId
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Integer> findRoleIds(Integer userId) {
        String hql = "select ur.role.id from Sys_UserRole ur where ur.user.id=:userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }

    public List<Sys_UserRole> findRoles(Integer userId){
        String hql = "from Sys_UserRole ur where ur.user.id=:userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }

    public boolean hasRole(Integer roleId){
        String hql = "select count(*) from Sys_UserRole ur where ur.role.id=:roleId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("roleId", roleId);
        return ((Long)query.uniqueResult())>0;
    }

}
