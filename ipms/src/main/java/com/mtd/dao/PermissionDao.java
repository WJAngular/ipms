package com.mtd.dao;

/**
 * Created by Wooce Yang on 2015/12/12.
 */
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mtd.entity.Sys_Permission;


/**
 * 权限DAO
 */
@Repository
public interface PermissionDao{

    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    public Object findByID(Class<?> clazz, Integer id) ;

    public List<Sys_Permission> findAll();

    /**
     * 查询用户拥有的权限
     * @param userId 用户id
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findPermissions(Integer userId);

    /**
     * 查询所有的菜单
     * @param
     * @return 菜单集合
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findMenus();

    public List<Sys_Permission> findMenus(int pagesize, int offset);

    /**
     * 查询用户拥有的菜单权限
     * @param userId
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findMenus(Integer userId);

    /**
     * 查询菜单下的操作权限
     * @param pid
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Sys_Permission> findMenuOperation(Integer pid);

    public List<Object> FindAll(String HQL) ;
    public List listByHQL(String hql);
    public Sys_Permission getEntity(String hql);
    public List<Object> findByPage(String hql,int pagesize,int offset);

}
