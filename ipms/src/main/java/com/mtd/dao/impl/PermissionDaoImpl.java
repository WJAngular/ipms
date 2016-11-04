package com.mtd.dao.impl;

import com.mtd.dao.PermissionDao;
import com.mtd.entity.Sys_Permission;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/12.
 */
public class PermissionDaoImpl implements PermissionDao {

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

    public List<Sys_Permission> findAll(){
        StringBuffer sb=new StringBuffer();
        sb.append("select p.ID id,p.PID pid,p.NAME name,p.URL url,p.ICON icon,p.SORT sort,p.DESCRIPTION description from Sys_Permission p ");
        sb.append("where p.TYPE='O' order by p.sort");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
        sqlQuery.addScalar("id", StandardBasicTypes.INTEGER );
        sqlQuery.addScalar("pid", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("name",StandardBasicTypes.STRING);
        sqlQuery.addScalar("url",StandardBasicTypes.STRING);
        sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
        sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("description",StandardBasicTypes.STRING);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(Sys_Permission.class));
        //sqlQuery.setCacheable(true);
        return sqlQuery.list();
    }

    /**
     * 查询用户拥有的权限
     * @param userId 用户id
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findPermissions(Integer userId){
        StringBuffer sb=new StringBuffer();
        sb.append("select p.* from Sys_Permission p ");
        sb.append("INNER JOIN role_Sys_Permission rp on p.ID=rp.Sys_Permission_ID ");
        sb.append("INNER JOIN role r ON  r.id=rp.ROLE_ID ");
        sb.append("INNER JOIN user_role  ur ON ur.ROLE_ID =rp.ROLE_ID ");
        sb.append("INNER JOIN user u ON u.id = ur.USER_ID ");
        sb.append("where u.id=?0 order by p.sort");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, userId);
        sqlQuery.addEntity(Sys_Permission.class);
        //sqlQuery.setCacheable(true);
        return sqlQuery.list();
    }

    /**
     * 查询所有的菜单
     * @return 菜单集合
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findMenus(){
        StringBuffer sb=new StringBuffer();
        sb.append("select p.ID id,p.PID pid,p.NAME name,p.URL url,p.ICON icon,p.SORT sort,p.DESCRIPTION description from Sys_Permission p ");
        sb.append("where p.TYPE='F' order by p.sort");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
        sqlQuery.addScalar("id", StandardBasicTypes.INTEGER );
        sqlQuery.addScalar("pid", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("name",StandardBasicTypes.STRING);
        sqlQuery.addScalar("url",StandardBasicTypes.STRING);
        sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
        sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("description",StandardBasicTypes.STRING);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(Sys_Permission.class));
        //sqlQuery.setCacheable(true);
        return sqlQuery.list();
    }

    public List<Sys_Permission> findMenus(int pagesize, int offset){
        StringBuffer sb=new StringBuffer();
        sb.append("select p.ID id,p.PID pid,p.NAME name,p.URL url,p.ICON icon,p.SORT sort,p.DESCRIPTION description from Sys_Permission p ");
        sb.append("where p.TYPE='F' order by p.sort");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
        sqlQuery.addScalar("id", StandardBasicTypes.INTEGER );
        sqlQuery.addScalar("pid", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("name",StandardBasicTypes.STRING);
        sqlQuery.addScalar("url",StandardBasicTypes.STRING);
        sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
        sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("description",StandardBasicTypes.STRING);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(Sys_Permission.class));
        //sqlQuery.setCacheable(true);
        return sqlQuery.setFirstResult((offset-1)*pagesize).setMaxResults(pagesize).list();
    }

    /**
     * 查询用户拥有的菜单权限
     * @param userId
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findMenus(Integer userId){
        StringBuffer sb=new StringBuffer();
        sb.append("select p.ID id,p.PID pid,p.NAME name,p.URL url,p.ICON icon,p.SORT sort,p.DESCRIPTION description from Sys_Permission p ");
        sb.append("INNER JOIN role_Sys_Permission rp on p.ID=rp.Sys_Permission_ID ");
        sb.append("INNER JOIN role r ON r.id=rp.ROLE_ID ");
        sb.append("INNER JOIN user_role ur ON ur.ROLE_ID =rp.ROLE_ID ");
        sb.append("INNER JOIN user u ON u.id = ur.USER_ID ");
        sb.append("where p.TYPE='F' and u.id=?0 order by p.sort");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, userId);
        sqlQuery.addScalar("id",StandardBasicTypes.INTEGER );
        sqlQuery.addScalar("pid", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("name",StandardBasicTypes.STRING);
        sqlQuery.addScalar("url",StandardBasicTypes.STRING);
        sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
        sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("description",StandardBasicTypes.STRING);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(Sys_Permission.class));
        //sqlQuery.setCacheable(true);
        return sqlQuery.list();
    }

    /**
     * 查询菜单下的操作权限
     * @param pid
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findMenuOperation(Integer pid){
        StringBuffer sb=new StringBuffer();
        sb.append("select p.ID id,p.NAME name,p.URL url,p.PERM_CODE permCode,p.DESCRIPTION description from Sys_Permission p ");
        sb.append("where p.TYPE='O' and p.PID=?0 order by p.SORT");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, pid);
        sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("name",StandardBasicTypes.STRING);
        sqlQuery.addScalar("url",StandardBasicTypes.STRING);
        sqlQuery.addScalar("permCode",StandardBasicTypes.STRING);
        sqlQuery.addScalar("description",StandardBasicTypes.STRING);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(Sys_Permission.class));
        //sqlQuery.setCacheable(true);
        return sqlQuery.list();
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
    public Sys_Permission getEntity(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        try{
            return (Sys_Permission)query.uniqueResult();
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
